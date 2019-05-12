package whopays.groupexpenses.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.LogInObject;
import whopays.groupexpenses.services.LogInService;

@Component
public class LogInHandler {

    private LogInService logInService;

    private static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    @Autowired
    public LogInHandler(LogInService logInService) {
        this.logInService = logInService;
    }

    public Mono<ServerResponse> logIn(ServerRequest serverRequest) {
        Mono<LogInObject> credentials = serverRequest.bodyToMono(LogInObject.class);
        return credentials.flatMap(userCredentials -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(logInService.checkCredentials(userCredentials), Boolean.class));
    }



}
