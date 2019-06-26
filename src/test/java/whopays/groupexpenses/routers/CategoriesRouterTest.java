package whopays.groupexpenses.routers;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import whopays.groupexpenses.models.GroupExpenses.Category;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
public class CategoriesRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @Before
    public void setup() {}

    @Test
    public void verify_routFindAll_onCorrectRequest() {
        webTestClient.get()
                .uri("/categories")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Category.class)
                .hasSize(3);
    }

    @Test
    public void verify_routFindById_onCorrectRequest() {
        webTestClient.get()
                .uri("/categories/{categoryID}", "5ccef4511c9d440000cce9d8")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Category.class)
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void verify_routFindById_checkingTheContent_onCorrectRequest() {
        webTestClient.get()
                .uri("/categories/{categoryID}", "5ccef4511c9d440000cce9d8")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.name").isEqualTo("RESTAURANTES");
    }
}
