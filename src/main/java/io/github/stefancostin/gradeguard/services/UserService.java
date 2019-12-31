package io.github.stefancostin.gradeguard.services;

import io.github.stefancostin.gradeguard.entities.Grade;
import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.models.StudentGradesDTO;
import io.github.stefancostin.gradeguard.models.SubjectDTO;
import io.github.stefancostin.gradeguard.models.UserDTO;
import io.github.stefancostin.gradeguard.repositories.ISubjectRepository;
import io.github.stefancostin.gradeguard.repositories.IUserRepository;
import io.github.stefancostin.gradeguard.utils.Role;
import io.github.stefancostin.gradeguard.utils.Semester;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;
    @Autowired
    ISubjectRepository subjectRepository;

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    public List<UserDTO> getStudents() {
        return userRepository.findAllStudents(Role.STUDENT).stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    public List<UserDTO> getProfessors() {
        return userRepository.findAllProfessors(Role.PROFESSOR).stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    public UserDTO getUserById(int id) {
        User userModel = userRepository.findById(id).orElse(null);
        return new UserDTO(userModel);
    }

    public UserDTO insertUser(UserDTO user) {
        User userModel = new User(user);
        User insertedUser = userRepository.save(userModel);
        return new UserDTO(insertedUser);
    }

    public UserDTO updateUserById(int id, UserDTO user) {
        User updatedUser = userRepository.findById(id).orElse(null);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setRole(user.getRole());
        updatedUser.setYearOfStudy(user.getYearOfStudy());
        User result = userRepository.save(updatedUser);
        return new UserDTO(result);
    }

    public void removeUserById(int id) {
        userRepository.deleteById(id);
    }

    public UserDTO getStudentData(int studentId) {
        User student = userRepository.findById(studentId).orElse(null);
        return new UserDTO(student);
    }

    public List<SubjectDTO> getSubjectsTaughtByProfessor(int professorId) {
        User professor = userRepository.findById(professorId).orElse(null);
        return professor.getSubjectsTaught()
                .stream().map(subject -> new SubjectDTO(subject)).collect(Collectors.toList());
    }

    public List<StudentGradesDTO> getStudentsBySubject(int subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        YearOfStudy yearOfStudy = subject.getYearOfStudy();

        return userRepository.findByYearOfStudyAndStudentGradesSubjectId(yearOfStudy, subjectId)
                .stream().map(user -> {

                    Set<Grade> filteredGrades = user.getStudentGrades().stream().filter((grade -> {
                        return grade.getSubject().getId() == subjectId;
                    })).collect(Collectors.toSet());

                    user.setStudentGrades(filteredGrades);
                    return new StudentGradesDTO((user));

                }).collect(Collectors.toList());
    }

    public List<UserDTO> getStudentsByYearOfStudy(YearOfStudy yearOfStudy) {
        Role studentRole = Role.STUDENT;
        return userRepository.findByYearOfStudyAndRole(yearOfStudy, studentRole)
                .stream().map(student -> new UserDTO(student)).collect(Collectors.toList());
    }

}
