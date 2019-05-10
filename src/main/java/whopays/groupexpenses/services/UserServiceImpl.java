package whopays.groupexpenses.services;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.commands.UserCommand;
import whopays.groupexpenses.converters.UserCommandToUser;
import whopays.groupexpenses.converters.UserToUserCommand;
import whopays.groupexpenses.models.User;
import whopays.groupexpenses.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<Void> createUser(User user) {
        return userRepository.insert(user).then();
    }

    @Override
    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id).block();
    }
}
