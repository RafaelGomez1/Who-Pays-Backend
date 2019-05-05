package whopays.groupexpenses.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import whopays.groupexpenses.models.Group;
import whopays.groupexpenses.models.User;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
