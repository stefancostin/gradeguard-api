package io.github.stefancostin.gradeguard.controllers;

import io.github.stefancostin.gradeguard.models.*;
import io.github.stefancostin.gradeguard.services.SubjectService;
import io.github.stefancostin.gradeguard.services.UserService;
import io.github.stefancostin.gradeguard.utils.Semester;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SubjectService subjectService;

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

    /** Admin View -- Adds Professor */
    @RequestMapping(value = "admin/professors", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO insertProfessor(@RequestBody ProfessorSubjectsDTO professor) {
        return this.userService.insertProfessor(professor);
    }

    /** Admin View -- Updates Professor */
    @RequestMapping(value = "admin/professors/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO insertProfessor(@PathVariable("id") int id, @RequestBody ProfessorSubjectsDTO professor) {
        return this.userService.updateProfessor(id, professor);
    }

    /** Admin View -- Populates Students Table */
    @RequestMapping(value = "/admin/students/{yearOfStudy}")
    public List<UserDTO> getStudentsByYearOfStudy(@PathVariable("yearOfStudy") YearOfStudy yearOfStudy) {
        return userService.getStudentsByYearOfStudy(yearOfStudy);
    }

    /** Student View -- On Init */
    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.GET)
    public UserDTO getStudentData(@PathVariable("studentId") int studentId) {
        return userService.getStudentData(studentId);
    }

    /** Student View -- Populates Table */
    @RequestMapping(value = "/students/{studentId}/grades", method = RequestMethod.GET)
    public List<SubjectGradesDTO> getStudentGrades(@PathVariable("studentId") int studentId,
                                                   @RequestParam(name = "year") YearOfStudy yearOfStudy,
                                                   @RequestParam(name = "semester") Semester semester) {
        return subjectService.getStudentGrades(studentId, yearOfStudy, semester);
    }

    /** Professor View -- On Init */
    @RequestMapping(value = "/professors/{professorId}/subjects", method = RequestMethod.GET)
    public List<SubjectDTO> getProfessorData(@PathVariable("professorId") int professorId) {
        return userService.getSubjectsTaughtByProfessor(professorId);
    }

    /** Professor View -- Populates Table */
    @RequestMapping(value = "/professors/subjects/{subjectId}/grades", method = RequestMethod.GET)
    public List<StudentGradesDTO> getStudentGradesBySubject(@PathVariable("subjectId") int subjectId) {
        return userService.getStudentGradesBySubject(subjectId);
    }

    /** Professor View -- CRUD OPS Students Dropdown */
    @RequestMapping(value = "/professors/subjects/{subjectId}/students", method = RequestMethod.GET)
    public List<UserDTO> getStudentsBySubject(@PathVariable("subjectId") int subjectId) {
        return userService.getStudentsBySubject(subjectId);
    }

}
