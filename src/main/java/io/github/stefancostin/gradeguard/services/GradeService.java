package io.github.stefancostin.gradeguard.services;

import io.github.stefancostin.gradeguard.entities.Grade;
import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.models.GradeDTO;
import io.github.stefancostin.gradeguard.models.SubjectDTO;
import io.github.stefancostin.gradeguard.repositories.IGradeRepository;
import io.github.stefancostin.gradeguard.repositories.ISubjectRepository;
import io.github.stefancostin.gradeguard.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    @Autowired
    IGradeRepository gradeRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    ISubjectRepository subjectRepository;

    public List<Grade> getGradesBySubject(int subjectId) {
        List<Grade> grades = gradeRepository.findBySubjectId(subjectId);
        return grades;
    }

    public List<Grade> getGradesByStudent(int studentId) {
        List<Grade> grades = gradeRepository.findByStudentId(studentId);
        return grades;
    }

    public Grade insertGrade(GradeDTO grade) {
        Subject subject = subjectRepository.findById(grade.getSubjectId()).orElse(null);
        User student = userRepository.findById(grade.getStudentId()).orElse(null);
        User professor = userRepository.findById(grade.getProfessorId()).orElse(null);

        Grade gradeModel = new Grade();
        gradeModel.setGrade(grade.getGrade());
        gradeModel.setGradeType(grade.getGradeType());
        gradeModel.setSubject(subject);
        gradeModel.setStudent(student);
        gradeModel.setProfessor(professor);

        return gradeRepository.save(gradeModel);
    }

    public Grade updateGradeById(int id, GradeDTO grade) {
        Subject subject = subjectRepository.findById(grade.getSubjectId()).orElse(null);
        User student = userRepository.findById(grade.getStudentId()).orElse(null);
        User professor = userRepository.findById(grade.getProfessorId()).orElse(null);

        Grade updatedGrade = gradeRepository.findById(id).orElse(null);
        updatedGrade.setGrade(grade.getGrade());
        updatedGrade.setGradeType(grade.getGradeType());

        updatedGrade.setSubject(subject);
        updatedGrade.setStudent(student);
        updatedGrade.setProfessor(professor);
        return gradeRepository.save(updatedGrade);
    }

}
