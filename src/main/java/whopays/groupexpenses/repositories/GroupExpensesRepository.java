package whopays.groupexpenses.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import whopays.groupexpenses.models.GroupExpense;

public interface GroupExpensesRepository extends ReactiveMongoRepository<GroupExpense, String> {

}
