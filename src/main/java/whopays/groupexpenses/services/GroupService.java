package whopays.groupexpenses.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.Group;
import whopays.groupexpenses.models.User;

public interface GroupService {

    Mono<Void> createGroup(Group command);
    Mono<Group> findById(String id);
    Flux<Group> findAll();
    void deleteById(String id);
    Mono<Void>  addUserToGroup(String userId, String groupId);
    Flux<User> getAllGroupMembers(String groupdId);
}
