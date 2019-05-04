package whopays.groupexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.commands.CategoryCommand;
import whopays.groupexpenses.converters.CategoryToCategoryCommand;
import whopays.groupexpenses.models.Category;
import whopays.groupexpenses.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final CategoryToCategoryCommand categoryCommand;

    public CategoryServiceImpl (CategoryRepository categoryRepository, CategoryToCategoryCommand categoryCommand) {
        this.categoryRepository = categoryRepository;
        this.categoryCommand = categoryCommand;
    }

    @Override
    public Flux<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> findById(String id) {
        return categoryRepository.findById(id);
    }
}
