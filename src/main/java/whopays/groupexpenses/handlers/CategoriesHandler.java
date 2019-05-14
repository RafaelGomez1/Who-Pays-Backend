package whopays.groupexpenses.handlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.GroupExpenses.Category;
import whopays.groupexpenses.services.CategoryService;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;


@Component
public class CategoriesHandler {

    private CategoryService categoryService;

    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    @Autowired
    public CategoriesHandler( CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {

        String id = serverRequest.pathVariable("categoryID");
        return categoryService.findById(id)
                .flatMap(category -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromObject(category)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> getAllCategories(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(categoryService.findAllCategories(), Category.class);
    }
}
