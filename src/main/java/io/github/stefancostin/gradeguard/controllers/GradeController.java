package io.github.stefancostin.gradeguard.controllers;

import io.github.stefancostin.gradeguard.entities.Grade;
import io.github.stefancostin.gradeguard.models.GradeDTO;
import io.github.stefancostin.gradeguard.models.GradePersistenceDTO;
import io.github.stefancostin.gradeguard.models.SubjectDTO;
import io.github.stefancostin.gradeguard.services.GradeService;
import io.github.stefancostin.gradeguard.utils.Semester;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/student/{studentId}/{yearOfStudy}/{semester}", method = RequestMethod.GET)
    public List<Grade> getStudentGradesByYearAndSemester(@PathVariable("studentId") int studentId,
                                        @PathVariable("yearOfStudy") int yearOfStudy,
                                        @PathVariable("semester") int semester) {
        return gradeService.getStudentGradesByYearAndSemester(studentId, yearOfStudy, semester);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Grade insertGrade(@RequestBody GradeDTO grade) {
        return gradeService.insertGrade(grade);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Grade updateGradeById(@PathVariable("id") int id, @RequestBody GradeDTO grade) {
        return gradeService.updateGradeById(id, grade);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeGradeById(@PathVariable("id") int id) {
        gradeService.removeGradeById(id);
    }

    /** Professor View -- Persist Grades */
    @RequestMapping(value = "/submit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void persistGrades(@RequestBody GradePersistenceDTO grades) {
        gradeService.persistGrades(grades);
    }

}
