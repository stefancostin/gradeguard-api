package io.github.stefancostin.gradeguard.controllers;

import io.github.stefancostin.gradeguard.entities.Grade;
import io.github.stefancostin.gradeguard.models.SubjectDTO;
import io.github.stefancostin.gradeguard.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    GradeService gradeService;

    @RequestMapping(value = "/subject/{subjectId}", method = RequestMethod.GET)
    public List<Grade> getSubjectGrades(@PathVariable("subjectId") int subjectId) {
        return gradeService.getGradesBySubject(subjectId);
    }

    @RequestMapping(value = "/student/{studentId}", method = RequestMethod.GET)
    public List<Grade> getStudentGrades(@PathVariable("studentId") int studentId) {
        return gradeService.getGradesByStudent(studentId);
    }

}
