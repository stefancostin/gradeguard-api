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

//    public Student updateUserById(int id, Student student) {
//        return userRepository.updateUserById(id, student);
//    }
//
//    public Student removeUserById(int id) {
//        return userRepository.removeUserById(id);
//    }

}
