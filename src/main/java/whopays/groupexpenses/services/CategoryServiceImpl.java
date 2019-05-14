package whopays.groupexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.GroupExpenses.Category;
import whopays.groupexpenses.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl (CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
