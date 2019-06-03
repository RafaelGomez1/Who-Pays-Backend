package whopays.groupexpenses.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import whopays.groupexpenses.handlers.LogInHandler;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class LogInRouter {

    @Bean
    public RouterFunction<ServerResponse> logInRoute(LogInHandler logInHandler) {
        return RouterFunctions
                .route(PUT("/login").and(accept(MediaType.APPLICATION_JSON)),
                        logInHandler::logIn);
    }
}
