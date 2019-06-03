package whopays.groupexpenses.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.GroupExpenses.User;
import whopays.groupexpenses.services.UserService;

import java.io.File;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class UserHandler {

    private UserService userService;

    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    @Autowired
    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("userId");
        return userService.findById(id)
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromObject(user)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.findAll(), User.class);
    }

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        Mono<User> newUser = serverRequest.bodyToMono(User.class);
        return newUser.flatMap(user -> ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(userService.createUser(user), User.class));
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        Mono<User> userToUpdate = serverRequest.bodyToMono(User.class);
        return userToUpdate.flatMap(user -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(userService.updateUser(user), User.class));
    }

    public Mono<ServerResponse> deleteUser(ServerRequest serverRequest) {
        String userId = serverRequest.pathVariable("userId");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.deleteUser(userId), Void.class);
    }

    public Mono<ServerResponse> uploadProfileImage(ServerRequest serverRequest) {
        String userId = serverRequest.pathVariable("userId");
        return serverRequest.body(BodyExtractors.toMultipartData())
                .flatMap(multipart -> {
                    multipart.toSingleValueMap().keySet()
                            .stream().forEach(c -> {
                        FilePart fp = (FilePart) multipart.toSingleValueMap().get(c);
                        fp.transferTo(new File(("/usersImages/".concat(userId))));
                    });
                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                            .body(Mono.just("/usersImages/".concat(userId)), String.class);
                });
        /*
        Mono<String> then = serverRequest.multipartData().map(it -> it.get("files"))
                .flatMapMany(Flux::fromIterable)
                .cast(FilePart.class)
                .flatMap(it -> it.transferTo(Paths.get("/tmp/" + it.filename() + userId)))
                .then(Mono.just("OK"));
        return ServerResponse.ok().body(then, String.class);

        final Flux<Void> voidFlux = serverRequest.body(BodyExtractors.toParts())
                .cast(FilePart.class)
                .flatMap(filePart -> {
                    final Path path = Paths.get("/tmp/");
                    return filePart.transferTo(path);
                });

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(voidFlux, Void.class);*/
    }

    public Mono<ServerResponse> getProfileImage(ServerRequest serverRequest) {
        return ServerResponse.ok().body(Mono.just("ssss"), String.class);
    }
}
