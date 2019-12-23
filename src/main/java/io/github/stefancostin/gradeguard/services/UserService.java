package io.github.stefancostin.gradeguard.services;

import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User insertUser(User user) {
        return userRepository.save(user);
    }

    public User updateUserById(int id, User user) {
        User updatedUser = userRepository.findById(id).orElse(null);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setRole(user.getRole());
        updatedUser.setYearOfStudy(user.getYearOfStudy());
        return userRepository.save(updatedUser);
    }

    public void removeUserById(int id) {
        userRepository.deleteById(id);
    }

}
