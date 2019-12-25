package io.github.stefancostin.gradeguard.repositories;

import io.github.stefancostin.gradeguard.entities.Grade;
import io.github.stefancostin.gradeguard.utils.Semester;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findBySubjectId(int subjectId);

    List<Grade> findByStudentId(int studentId);

    List<Grade> findByStudentIdAndSubjectYearOfStudyAndSubjectSemester(int studentId, YearOfStudy yearOfStudy, Semester semester);

}
