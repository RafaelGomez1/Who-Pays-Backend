package whopays.groupexpenses.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import whopays.groupexpenses.handlers.GroupBalanceHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class GroupBalanceRouter {

    @Bean
    public RouterFunction<ServerResponse> groupBalanceRoute(GroupBalanceHandler groupBalanceHandler) {
        return RouterFunctions
                .route(GET("/groups/{groupId}/filtered/balance").and(accept(MediaType.APPLICATION_JSON)),
                        groupBalanceHandler::getGroupBalanceFiltered);
    }

}
