package whopays.groupexpenses.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import whopays.groupexpenses.models.Group;
import whopays.groupexpenses.models.User;

@Repository
public interface GroupRepository extends ReactiveMongoRepository<Group, String>, GroupCustomRepository {

    Flux<User> findAllUsersById(String id);

//    @Query("{ 'members': { 'username' : 'pepito'}}")
//    Flux<User> findAllMembersId();
}
