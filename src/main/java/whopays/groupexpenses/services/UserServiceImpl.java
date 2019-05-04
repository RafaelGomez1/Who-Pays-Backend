package whopays.groupexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.commands.UserCommand;
import whopays.groupexpenses.converters.UserCommandToUser;
import whopays.groupexpenses.converters.UserToUserCommand;
import whopays.groupexpenses.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserToUserCommand userToUserCommand;
    @Autowired
    private final UserCommandToUser userCommandToUser;

    public UserServiceImpl(UserRepository userRepository, UserToUserCommand userToUserCommand, UserCommandToUser userCommandToUser) {
        this.userRepository = userRepository;
        this.userToUserCommand = userToUserCommand;
        this.userCommandToUser = userCommandToUser;
    }

    @Override
    public Mono<UserCommand> createUser(UserCommand command) {
      return userRepository.save(userCommandToUser.convert(command))
              .map(userToUserCommand::convert);
    }

    @Override
    public Mono<UserCommand> findById(String id) {
        return userRepository.findById(id)
                .map(userToUserCommand::convert);
    }

    @Override
    public Flux<UserCommand> findAll() {
        return userRepository.findAll()
                .map(userToUserCommand::convert);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id).block();
    }
}
