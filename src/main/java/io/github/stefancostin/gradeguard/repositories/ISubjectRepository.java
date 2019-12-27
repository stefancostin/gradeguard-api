package io.github.stefancostin.gradeguard.repositories;

import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.utils.Semester;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISubjectRepository extends JpaRepository<Subject, Integer> {

    public List<Subject> findByGradesStudentId(int studentId);

    public List<Subject> findByYearOfStudyAndSemester(YearOfStudy yearOfStudy, Semester semester);

    public List<Subject> findByYearOfStudyAndSemesterAndGradesStudentId(YearOfStudy yearOfStudy, Semester semester,
                                                                        int studentId);

}
