package whopays.groupexpenses.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.commands.UserCommand;


public interface UserService {

    Mono<UserCommand> createUser(UserCommand user);

    Mono<UserCommand> findById(String id);

    Flux<UserCommand> findAll();

    void deleteById(String id);

}
