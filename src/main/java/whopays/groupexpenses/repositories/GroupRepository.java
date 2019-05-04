package whopays.groupexpenses.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import whopays.groupexpenses.models.Group;

public interface GroupRepository extends ReactiveMongoRepository<Group, String> {
}
