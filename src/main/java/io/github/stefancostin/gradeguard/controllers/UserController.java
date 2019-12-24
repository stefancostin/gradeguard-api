package io.github.stefancostin.gradeguard.controllers;

import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.models.UserDTO;
import io.github.stefancostin.gradeguard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<UserDTO> getStudents() {
        return userService.getStudents();
    }

    @RequestMapping(value = "/professors", method = RequestMethod.GET)
    public List<UserDTO> getProfessors() {
        return userService.getProfessors();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDTO getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO insertUser(@RequestBody UserDTO user) {
        return this.userService.insertUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO updateUserById(@PathVariable("id") int id, @RequestBody UserDTO user) {
        return userService.updateUserById(id, user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeUserById(@PathVariable("id") int id) {
        userService.removeUserById(id);
    }

}
