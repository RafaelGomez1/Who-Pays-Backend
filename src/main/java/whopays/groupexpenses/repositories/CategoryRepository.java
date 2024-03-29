package whopays.groupexpenses.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import whopays.groupexpenses.models.GroupExpenses.Category;

@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {

}
