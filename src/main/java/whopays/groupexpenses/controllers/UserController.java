package whopays.groupexpenses.controllers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import whopays.groupexpenses.models.User;
import whopays.groupexpenses.repositories.UserRepository;
import whopays.groupexpenses.services.UserService;

@Slf4j
@RestController
@EnableAutoConfiguration
@Component
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("user/create")
    public Mono<Void> saveOrUpdate(@RequestBody User command) {
      return userService.createUser(command);
    }

    @GetMapping("user")
    public Flux<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("user/{userId}")
    public Mono<User> getUser(@PathVariable("userId") String userId) {
        return userService.findById(userId);
    }

    @PutMapping("user/update/{userId}")
    public Mono<ResponseEntity<User>> updateUser (@PathVariable("userId") String userId, @RequestBody User user) {
        return userRepository.findById(userId)
                .flatMap( existingUser -> {
                    return userRepository.save(updateUser(user, existingUser));
                })
                .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("user/delete/{userId}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("userId") String userId) {
        return userRepository.findById(userId)
                .flatMap(user ->
                    userRepository.deleteById(user.getId())
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                    ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/
}
