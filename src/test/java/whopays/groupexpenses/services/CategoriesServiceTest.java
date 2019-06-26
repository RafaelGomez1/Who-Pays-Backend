package whopays.groupexpenses.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import whopays.groupexpenses.models.GroupExpenses.Category;
import whopays.groupexpenses.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
public class CategoriesServiceTest {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    private List<Category> categories;

    @Before
    public void setup() {

        this.categories = new ArrayList<>();
        this.categories.add(new Category("5ccef4511c9d440000cce9d8", "RESTAURANTES"));
        this.categories.add(new Category("5ccef4621c9d440000cce9d9", "OCIO"));
        this.categories.add(new Category("5ccef46a1c9d440000cce9da", "SALUD"));

        Mockito.when(this.categoryRepository.findAll())
                .thenReturn(Flux.fromIterable(categories));

        Mockito.when(this.categoryRepository.findById(Mockito.anyString()))
                .thenReturn(Mono.just(categories.get(0)));
    }

    @Test
    public void verify_findAllCategories_returns3Elements_onRequest() {
       Flux<Category> categoriesFlux = this.categoryService.findAllCategories();

        StepVerifier.create(categoriesFlux)
                .expectNext(categories.get(0))
                .expectNext(categories.get(1))
                .expectNext(categories.get(2))
                .verifyComplete();
    }

    @Test
    public void verify_findById_returnsFirstElement_onRequest() {
        Mono<Category> categoriesMono = this.categoryService.findById("5ccef4511c9d440000cce9d8");

        StepVerifier.create(categoriesMono)
                .expectNext(categories.get(0))
                .verifyComplete();
    }
}
