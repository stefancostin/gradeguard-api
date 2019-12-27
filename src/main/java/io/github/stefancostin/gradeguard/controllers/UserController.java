package io.github.stefancostin.gradeguard.controllers;

import io.github.stefancostin.gradeguard.entities.Grade;
import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.models.SubjectDTO;
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

    @RequestMapping(value = "admin/students", method = RequestMethod.GET)
    public List<UserDTO> getStudents() {
        return userService.getStudents();
    }

    /** Admin View -- Populates Professors Table */
    @RequestMapping(value = "admin/professors", method = RequestMethod.GET)
    public List<UserDTO> getProfessors() {
        return userService.getProfessors();
    }

    /** Admin View -- Populates Students Table */
    @RequestMapping(value = "/admin/students/{yearOfStudy}")
    public List<UserDTO> getStudentsByYearOfStudy(@PathVariable("yearOfStudy") int yearOfStudy) {
        return userService.getStudentsByYearOfStudy(yearOfStudy);
    }

    /** Student View -- On Init*/
    @RequestMapping(value = "/student-data/{studentId}", method = RequestMethod.GET)
    public UserDTO getStudentData(@PathVariable("studentId") int studentId) {
        return userService.getStudentData(studentId);
    }

    /** Professor View -- On Init */
    @RequestMapping(value = "/professor-data/{professorId}", method = RequestMethod.GET)
    public List<SubjectDTO> getProfessorData(@PathVariable("professorId") int professorId) {
        return userService.getSubjectsTaughtByProfessor(professorId);
    }

    /** Professor View -- Populates Table */
    @RequestMapping(value = "/students-by-subject/{subjectId}", method = RequestMethod.GET)
    public List<UserDTO> getStudentsBySubject(@PathVariable("subjectId") int subjectId) {
        return userService.getStudentsBySubject(subjectId);
    }

}
