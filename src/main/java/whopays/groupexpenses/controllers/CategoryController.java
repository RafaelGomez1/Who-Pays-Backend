package whopays.groupexpenses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.commands.CategoryCommand;
import whopays.groupexpenses.services.CategoryService;

import java.awt.*;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public Mono<CategoryCommand> getCategory(@PathVariable("categoryId") String categoryID) {
        Mono<CategoryCommand> command = this.categoryService.findById(categoryID);
        return command;
    }

    @GetMapping()
    public Flux<CategoryCommand> getAllCategories() {
        Flux<CategoryCommand> command = this.categoryService.findAllCategories();
        return command;
    }
}
