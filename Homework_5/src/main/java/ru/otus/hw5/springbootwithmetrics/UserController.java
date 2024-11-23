package ru.otus.hw5.springbootwithmetrics;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    Dao userRepository;

    @GetMapping("/user")
    @Counted(value = "getallusers.cnt")
    @Timed(value = "getallusers.time", description = "Time taken to return users",
            percentiles = {0.5, 0.95, 0.99})
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/user")
    @Counted(value = "createuser.cnt")
    @Timed(value = "createuser.time", description = "Time taken to create user",
            percentiles = {0.5, 0.95, 0.99})
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            userRepository.save(user);
            return new ResponseEntity<>("User was created successfully.", HttpStatus.CREATED);
        }
         catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    @Counted(value = "getparticularuser.cnt")
    @Timed(value = "getparticularuser.time", description = "Time taken to return particular user",
            percentiles = {0.5, 0.95, 0.99})
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        try {
            userRepository.findById(id);
            return new ResponseEntity<User>(userRepository.findById(id), HttpStatus.OK);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/user/{id}")
    @Counted(value = "updateuser.time")
    @Timed(value = "updateuser.time", description = "Time taken to update user",
            percentiles = {0.5, 0.95, 0.99})
    public ResponseEntity<String> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User _user = userRepository.findById(id);

        if (_user != null) {
            _user.setId(id);
            _user.setUsername(user.getUsername());
            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
            _user.setPhone(user.getPhone());
            _user.setEmail(user.getEmail());

            userRepository.update(_user);
            return new ResponseEntity<>("User was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find user with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    @Counted(value = "deleteuser.cnt")
    @Timed(value = "deleteuser.time", description = "Time taken to delete user",
            percentiles = {0.5, 0.95, 0.99})
    public void deleteUser(@PathVariable("id") long id) {
        throw new RuntimeException("Not supported yet");
    }
}
