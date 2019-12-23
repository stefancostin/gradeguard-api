package io.github.stefancostin.gradeguard.controllers;

import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User insertUser(@RequestBody User user) {
        return this.userService.insertUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUserById(@PathVariable("id") int id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeUserById(@PathVariable("id") int id) {
        userService.removeUserById(id);
    }

}
