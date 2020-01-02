package io.github.stefancostin.gradeguard.services;

import io.github.stefancostin.gradeguard.entities.Grade;
import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.models.GradeDTO;
import io.github.stefancostin.gradeguard.models.GradePersistenceDTO;
import io.github.stefancostin.gradeguard.repositories.IGradeRepository;
import io.github.stefancostin.gradeguard.repositories.ISubjectRepository;
import io.github.stefancostin.gradeguard.repositories.IUserRepository;
import io.github.stefancostin.gradeguard.utils.GradeType;
import io.github.stefancostin.gradeguard.utils.Semester;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    IGradeRepository gradeRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    ISubjectRepository subjectRepository;

    public List<Grade> getGradesBySubject(int subjectId) {
        return gradeRepository.findBySubjectId(subjectId);
    }

    public List<Grade> getStudentGradesByYearAndSemester(int studentId, int yearOfStudy, int semester) {
        YearOfStudy yearEnum = YearOfStudy.values()[yearOfStudy];
        Semester semesterEnum = Semester.values()[semester];
        return gradeRepository.findByStudentIdAndSubjectYearOfStudyAndSubjectSemester(studentId, yearEnum, semesterEnum);
    }

    public List<Grade> getGradesByStudent(int studentId) {
        return gradeRepository.findByStudentId(studentId);
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

    public void removeGradeById(int id) { gradeRepository.deleteById(id); }

    public void persistGrades(GradePersistenceDTO grades) {
        if (grades.getGradeExam() != null) {
            persistSingleGrade(grades, GradeType.EXAM, grades.getGradeExam());
        } else {
            removeSingleGrade(grades, GradeType.EXAM);
        }

        if (grades.getGradeFinal() != null) {
            persistSingleGrade(grades, GradeType.FINAL, grades.getGradeFinal());
        } else {
            removeSingleGrade(grades, GradeType.FINAL);
        }

        if (grades.getGradeLaboratory() != null) {
            persistSingleGrade(grades, GradeType.LABORATORY, grades.getGradeLaboratory());
        } else {
            removeSingleGrade(grades, GradeType.LABORATORY);
        }

        if (grades.getGradeProject() != null) {
            persistSingleGrade(grades, GradeType.PROJECT, grades.getGradeProject());
        } else {
            removeSingleGrade(grades, GradeType.PROJECT);
        }
    }

    private List<Grade> getMatchingGrades(GradePersistenceDTO grades, GradeType gradeType) {
        List<Grade> matchingGrades = gradeRepository.findByStudentIdAndSubjectIdAndGradeType(
                grades.getStudentId(),
                grades.getSubjectId(),
                gradeType);

        return matchingGrades;
    }

    private void persistSingleGrade(GradePersistenceDTO grades, GradeType gradeType, int gradeValue) {
        GradeDTO grade = new GradeDTO();
        grade.setSubjectId(grades.getSubjectId());
        grade.setStudentId(grades.getStudentId());
        grade.setProfessorId(grades.getProfessorId());
        grade.setGradeType(gradeType);
        grade.setGrade(gradeValue);

        List<Grade> matchingGrades = getMatchingGrades(grades, gradeType);

        // remove duplicate grades
        if (matchingGrades.size() > 1) {
            for (int i = 1; i < matchingGrades.size(); i++) {
                this.removeGradeById(matchingGrades.get(i).getId());
            }
        }

        if (matchingGrades.isEmpty()) {
            this.insertGrade(grade);
        } else {
            this.updateGradeById(matchingGrades.get(0).getId(), grade);
        }
    }

    private void removeSingleGrade(GradePersistenceDTO grades, GradeType gradeType) {
        List<Grade> matchingGrades = getMatchingGrades(grades, gradeType);

        // remove grade and all other duplicate grades of this type
        if (matchingGrades.size() > 0) {
            for (int i = 0; i < matchingGrades.size(); i++) {
                this.removeGradeById(matchingGrades.get(i).getId());
            }
        }
    }

}
