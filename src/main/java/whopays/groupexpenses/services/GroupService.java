package whopays.groupexpenses.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.Group;

public interface GroupService {

    Mono<Group> createGroup(Group command);
    Mono<Group> findById(String id);
    Flux<Group> findAll();
    void deleteById(String id);
}
