package io.github.stefancostin.gradeguard.controllers;

import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User insertUser(@RequestBody User user) {
        return this.userService.insertUser(user);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Student updateUserById(@PathVariable("id") int id, @RequestBody Student student) {
//        return studentService.updateStudentById(id, student);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public Student removeUserById(@PathVariable("id") int id) {
//        return studentService.removeStudentById(id);
//    }

}
