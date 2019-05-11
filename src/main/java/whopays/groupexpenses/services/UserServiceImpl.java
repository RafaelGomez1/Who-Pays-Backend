package whopays.groupexpenses.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.User;
import whopays.groupexpenses.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> createUser(User user) {
        return userRepository.insert(user);
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

    @Override
    public Mono<User> updateUser(User user) {
        return userRepository.findById(user.getId())
                .flatMap( existingUser -> {
                    return userRepository.save(updateUser(user, existingUser)).thenReturn(user);
                });
    }

    @Override
    public Mono<Void> deleteUser(String userId) {
        return userRepository.findById(userId)
                .flatMap(user ->
                        userRepository.deleteById(user.getId()));
    }

    private User updateUser(User originalUser, User modifiedUser) {
        if (originalUser.getId().equals(modifiedUser.getId())) {
            if (!originalUser.getUsername().equals(modifiedUser.getUsername())) {
                originalUser.setUsername(modifiedUser.getUsername());
            }

            if (!originalUser.getPassword().equals(modifiedUser.getPassword())) {
                originalUser.setPassword(modifiedUser.getPassword());
            }
        }
        return originalUser;
    }
}
