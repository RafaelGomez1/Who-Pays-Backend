package whopays.groupexpenses.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.GroupExpenses.Category;


public interface CategoryService {
    Flux<Category> findAllCategories();
    Mono<Category> findById(String id);
}
