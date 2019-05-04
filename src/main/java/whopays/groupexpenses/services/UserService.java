package whopays.groupexpenses.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.User;

public interface UserService {

    Mono<Void> createUser(User user);

    Mono<User> findById(String id);

    Flux<User> findAll();

    void deleteById(String id);

}
