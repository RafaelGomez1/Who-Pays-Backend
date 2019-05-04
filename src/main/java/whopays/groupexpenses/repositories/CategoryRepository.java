package whopays.groupexpenses.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import whopays.groupexpenses.models.Category;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
