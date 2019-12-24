package io.github.stefancostin.gradeguard.services;

import io.github.stefancostin.gradeguard.entities.Grade;
import io.github.stefancostin.gradeguard.models.GradeDTO;
import io.github.stefancostin.gradeguard.models.SubjectDTO;
import io.github.stefancostin.gradeguard.repositories.IGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    @Autowired
    IGradeRepository gradeRepository;

    public List<Grade> getGradesBySubject(int subjectId) {
        List<Grade> grades = gradeRepository.findBySubjectId(subjectId);
        return grades;
    }

    public List<Grade> getGradesByStudent(int studentId) {
        List<Grade> grades = gradeRepository.findByStudentId(studentId);
        return grades;
    }

}
