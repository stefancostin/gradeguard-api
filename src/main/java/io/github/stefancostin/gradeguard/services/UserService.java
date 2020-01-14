package io.github.stefancostin.gradeguard.services;

import io.github.stefancostin.gradeguard.entities.Grade;
import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.models.ProfessorSubjectsDTO;
import io.github.stefancostin.gradeguard.models.StudentGradesDTO;
import io.github.stefancostin.gradeguard.models.SubjectDTO;
import io.github.stefancostin.gradeguard.models.UserDTO;
import io.github.stefancostin.gradeguard.repositories.ISubjectRepository;
import io.github.stefancostin.gradeguard.repositories.IUserRepository;
import io.github.stefancostin.gradeguard.utils.Role;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ISubjectRepository subjectRepository;
    @Resource
    private BasicTextEncryptor encryptor;

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

        String encryptedPassword = encryptor.encrypt(user.getPassword());
        userModel.setPassword(encryptedPassword);

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

        if (user.getPassword() != null) {
            String encryptedPassword = encryptor.encrypt(user.getPassword());
            updatedUser.setPassword(encryptedPassword);
        }

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

    public List<StudentGradesDTO> getStudentGradesBySubject(int subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        YearOfStudy yearOfStudy = subject.getYearOfStudy();

        Map<Integer, User> uniqueStudentsMap = new HashMap<>();
        return userRepository.findByYearOfStudyAndStudentGradesSubjectId(yearOfStudy, subjectId)
                .stream()
                .filter(user -> !uniqueStudentsMap.containsKey(user.getId()))
                .peek(user -> {
                    // store user ids in a hash map in order to filter out duplicate students
                    if (!uniqueStudentsMap.containsKey(user.getId())) {
                        uniqueStudentsMap.put(user.getId(), user);
                    }
                })
                .map(user -> {

                    Set<Grade> filteredGrades = user.getStudentGrades().stream().filter((grade -> {
                        return grade.getSubject().getId() == subjectId;
                    })).collect(Collectors.toSet());

                    user.setStudentGrades(filteredGrades);
                    return new StudentGradesDTO((user));

                }).collect(Collectors.toList());
    }

    public List<UserDTO> getStudentsBySubject(int subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        YearOfStudy yearOfStudy = subject.getYearOfStudy();

        return userRepository.findByYearOfStudyAndRole(yearOfStudy, Role.STUDENT)
                .stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    public List<UserDTO> getStudentsByYearOfStudy(YearOfStudy yearOfStudy) {
        Role studentRole = Role.STUDENT;
        return userRepository.findByYearOfStudyAndRole(yearOfStudy, studentRole)
                .stream().map(student -> new UserDTO(student)).collect(Collectors.toList());
    }

    public UserDTO insertProfessor(ProfessorSubjectsDTO professor) {
        User professorModel = new User();
        professorModel.setRole(Role.PROFESSOR);
        professorModel.setFirstName(professor.getFirstName());
        professorModel.setLastName(professor.getLastName());
        professorModel.setEmail(professor.getEmail());

        String encryptedPassword = encryptor.encrypt(professor.getPassword());
        professorModel.setPassword(encryptedPassword);

        if (professor.getSubjectsIdList() != null) {
            for (Integer subjectId : professor.getSubjectsIdList()) {
                Subject subject = subjectRepository.findById(subjectId).orElse(null);
                professorModel.addSubjectTaught(subject);
            }
        }

        User professorAdded = this.userRepository.save(professorModel);
        return new UserDTO(professorAdded);
    }

    public UserDTO updateProfessor(int professorId, ProfessorSubjectsDTO professor) {
        User professorModel = userRepository.findById(professorId).orElse(null);
        professorModel.setFirstName(professor.getFirstName());
        professorModel.setLastName(professor.getLastName());
        professorModel.setEmail(professor.getEmail());

        if (professor.getPassword() != null) {
            String encryptedPassword = encryptor.encrypt(professor.getPassword());
            professorModel.setPassword(encryptedPassword);
        }

        professorModel.getSubjectsTaught().clear();
        for (Integer subjectId : professor.getSubjectsIdList()) {
            Subject subject = subjectRepository.findById(subjectId).orElse(null);
            professorModel.addSubjectTaught(subject);
        }

        User professorAdded = this.userRepository.save(professorModel);
        return new UserDTO(professorAdded);
    }

}
