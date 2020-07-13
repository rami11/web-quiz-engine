package com.rsn.webquizengine.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    //todo: added for testing: remove it when you're done
    @GetMapping(path = "/users")
    List<User> getAllUsers() {
        return repository.findAll();
    }

    @PostMapping(path = "/register", consumes = "application/json")
    User register(@Valid @RequestBody User user) {
        Optional<User> userOptional = repository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new EmailAlreadyTakenException();
        }
        String password = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordHash = encoder.encode(password);
        user.setPassword(passwordHash);

        repository.save(user);
        return user;
    }
}
