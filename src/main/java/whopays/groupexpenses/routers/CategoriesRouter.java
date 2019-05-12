package whopays.groupexpenses.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import whopays.groupexpenses.handlers.CategoriesHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class CategoriesRouter {

    @Bean
    public RouterFunction<ServerResponse> categoriesRoute(CategoriesHandler categoriesHandler) {
        return RouterFunctions
                .route(GET("/categories").and(accept(MediaType.APPLICATION_JSON)),
                        categoriesHandler::getAllCategories)
                .andRoute(GET("/categories/{categoryID}").and(accept(MediaType.APPLICATION_JSON)),
                        categoriesHandler::getById);
    }
}
