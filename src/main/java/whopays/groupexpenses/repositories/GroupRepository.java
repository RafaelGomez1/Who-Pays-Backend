package whopays.groupexpenses.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import whopays.groupexpenses.models.GroupExpenses.Group;

@Repository
public interface GroupRepository extends ReactiveMongoRepository<Group, String> {

}
