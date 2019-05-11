package whopays.groupexpenses.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import whopays.groupexpenses.handlers.UserHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> userRoute(UserHandler userHandler) {
        return RouterFunctions
                .route(POST("/user/create").and(accept(MediaType.APPLICATION_JSON)),
                        userHandler::createUser)
                .andRoute(GET("/user").and(accept(MediaType.APPLICATION_JSON)),
                        userHandler::getAllUsers)
                .andRoute(GET("/user/{userId}").and(accept(MediaType.APPLICATION_JSON)),
                        userHandler::getUserById)
                .andRoute(POST("/user/update").and(accept(MediaType.APPLICATION_JSON)),
                        userHandler::updateUser)
                .andRoute(DELETE("/user/delete/{userId}").and(accept(MediaType.APPLICATION_JSON)),
                        userHandler::deleteUser);
    }
}
