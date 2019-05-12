package whopays.groupexpenses.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.User;
import whopays.groupexpenses.services.UserService;

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




}
