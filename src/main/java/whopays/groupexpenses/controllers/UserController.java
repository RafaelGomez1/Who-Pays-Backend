package whopays.groupexpenses.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import whopays.groupexpenses.commands.UserCommand;
import whopays.groupexpenses.services.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
public class UserController {

    private static final String USER_URL = "user";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user/create")
    public String saveOrUpdate(@Valid @ModelAttribute("user") UserCommand command, BindingResult bindingResult) {
       UserCommand savedUser = userService.createUser(command).block();
       return "user";
    }
}
