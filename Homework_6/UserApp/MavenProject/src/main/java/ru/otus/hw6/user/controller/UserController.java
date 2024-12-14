package ru.otus.hw6.user.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.hw6.user.dao.Dao;
import ru.otus.hw6.user.domain.UserProfile;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    @Qualifier("NoDb")
    Dao userRepository;

//    @GetMapping("/user")
//    @Counted(value = "getallusers.cnt")
//    @Timed(value = "getallusers.time", description = "Time taken to return users",
//            percentiles = {0.5, 0.95, 0.99})
//    public ResponseEntity<List<User>> getAllUsers() {
//        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
//    }

//    @PostMapping("/user")
//    @Counted(value = "createuser.cnt")
//    @Timed(value = "createuser.time", description = "Time taken to create user",
//            percentiles = {0.5, 0.95, 0.99})
//    public ResponseEntity<String> createUser(@RequestBody User user) {
//        try {
//            userRepository.save(user);
//            return new ResponseEntity<>("User was created successfully.", HttpStatus.CREATED);
//        }
//         catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/user/{id}")
//    @Counted(value = "getparticularuser.cnt")
//    @Timed(value = "getparticularuser.time", description = "Time taken to return particular user",
//            percentiles = {0.5, 0.95, 0.99})
//    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
//        try {
//            userRepository.findById(id);
//            return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }

    @PutMapping("/user/{id}")
    @Counted(value = "updateuser.time")
    @Timed(value = "updateuser.time", description = "Time taken to update user",
            percentiles = {0.5, 0.95, 0.99})
    public ResponseEntity<String> updateUserProfile(@RequestBody UserProfile userProfile) {
        userRepository.update(userProfile);
        return new ResponseEntity<>("User was updated successfully.", HttpStatus.OK);
    }

//    @DeleteMapping("/user/{id}")
//    @Counted(value = "deleteuser.cnt")
//    @Timed(value = "deleteuser.time", description = "Time taken to delete user",
//            percentiles = {0.5, 0.95, 0.99})
//    public void deleteUser(@PathVariable("id") long id) {
//        throw new RuntimeException("Not supported yet");
//    }

    @GetMapping("/user/me")
    @Counted(value = "getselfuser.cnt")
    @Timed(value = "getselfuser.time", description = "Time taken to return self user",
            percentiles = {0.5, 0.95, 0.99})
    public ResponseEntity<UserProfile> getSelfUser(@RequestHeader("X-AuthCode") long authCode, @RequestHeader("X-UserId") long userId) {
        if (authCode == 0){
            throw new RuntimeException("Not authorized");
        }
        try {
            userRepository.findById(userId);
            return new ResponseEntity<>(userRepository.findById(userId), HttpStatus.OK);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
