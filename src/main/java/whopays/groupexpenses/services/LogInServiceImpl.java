package whopays.groupexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.LogInObject;
import whopays.groupexpenses.repositories.UserRepository;

@Service
public class LogInServiceImpl implements LogInService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public LogInServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<Boolean> checkCredentials(LogInObject userCredentials) {
        return userRepository.findByUsername(userCredentials.getUsername())
                .flatMap(user1 -> {
                    return Mono.just(passwordEncoder.matches(userCredentials.getPassword(), user1.getPassword()));
                });
    }
}
