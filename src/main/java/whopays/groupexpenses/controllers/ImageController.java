package whopays.groupexpenses.controllers;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.UploadImage.CloseCondition;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ImageController {

    @RequestMapping(value = "/user/image/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> uploadHandler(@RequestBody Flux<Part> parts) {
        return parts
                .filter(part -> part instanceof FilePart) // only retain file parts
                .ofType(FilePart.class) // convert the flux to FilePart
                .flatMap(this::saveFile); // save each file and flatmap it to a flux of results
    }

    private Mono<String> saveFile(FilePart filePart) {

        // if a file with the same name already exists in a repository, delete and recreate it
        final String filename = filePart.filename();
        File file = new File(filename);
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            return Mono.error(e); // if creating a new file fails return an error
        }

        try {
            // create an async file channel to store the file on disk
            final AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(file.toPath(), StandardOpenOption.WRITE);

            final CloseCondition closeCondition = new CloseCondition();

            // pointer to the end of file offset
            AtomicInteger fileWriteOffset = new AtomicInteger(0);
            // error signal
            AtomicBoolean errorFlag = new AtomicBoolean(false);

            // FilePart.content produces a flux of data buffers, each need to be written to the file
            return filePart.content().doOnEach(dataBufferSignal -> {
                if (dataBufferSignal.hasValue() && !errorFlag.get()) {
                    // read data from the incoming data buffer into a file array
                    DataBuffer dataBuffer = dataBufferSignal.get();
                    int count = dataBuffer.readableByteCount();
                    byte[] bytes = new byte[count];
                    dataBuffer.read(bytes);

                    // create a file channel compatible byte buffer
                    final ByteBuffer byteBuffer = ByteBuffer.allocate(count);
                    byteBuffer.put(bytes);
                    byteBuffer.flip();

                    // get the current write offset and increment by the buffer size
                    final int filePartOffset = fileWriteOffset.getAndAdd(count);
                    // write the buffer to disk
                    closeCondition.onTaskSubmitted();
                    fileChannel.write(byteBuffer, filePartOffset, null, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            // file part successfuly written to disk, clean up
                            byteBuffer.clear();

                            if (closeCondition.onTaskCompleted())
                                try {
                                    fileChannel.close();
                                } catch (IOException ignored) {
                                    ignored.printStackTrace();
                                }
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            // there as an error while writing to disk, set an error flag
                            errorFlag.set(true);
                        }
                    });
                }
            }).doOnComplete(() -> {
                // all done, close the file channel
                if (closeCondition.canCloseOnComplete())
                    try {
                        fileChannel.close();
                    } catch (IOException ignored) {
                    }

            }).doOnError(t -> {
                // ooops there was an error
                try {
                    fileChannel.close();
                } catch (IOException ignored) {
                }
                // take last, map to a status string
            }).last().map(dataBuffer -> filePart.filename() + " " + (errorFlag.get() ? "error" : "uploaded"));
        } catch (IOException e) {
            return Mono.error(e);
        }
    }
}
