package whopays.groupexpenses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.Category;
import whopays.groupexpenses.services.CategoryService;

@RestController
@EnableAutoConfiguration
@Component
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("categories/{categoryID}")
    public Mono<Category> getCategory(@PathVariable String categoryID) {
        Mono<Category> command = this.categoryService.findById(categoryID);
        return command;
    }

    @GetMapping("categories")
    public Flux<Category> getAllCategories() {
        Flux<Category> command = this.categoryService.findAllCategories();
        return command;
    }
}
