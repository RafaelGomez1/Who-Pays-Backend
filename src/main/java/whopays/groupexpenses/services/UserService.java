package whopays.groupexpenses.services;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.User;

public interface UserService {

    Mono<User> createUser(User user);

    Mono<User> findById(String id);

    Flux<User> findAll();

    void deleteById(String id);

    Mono<User> updateUser(User user);

    Mono<Void> deleteUser(String userId);
}
