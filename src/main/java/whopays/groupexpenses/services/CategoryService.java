package whopays.groupexpenses.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.commands.CategoryCommand;


public interface CategoryService {

    Flux<CategoryCommand> findAllCategories();
    Mono<CategoryCommand> findById(String id);
}
