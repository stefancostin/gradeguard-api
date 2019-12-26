package io.github.stefancostin.gradeguard.repositories;

import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.utils.Role;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT s from User s where s.role =:role ")
    List<User> findAllStudents(@Param("role") Role role);

    @Query("SELECT p from User p where p.role =:role ")
    List<User> findAllProfessors(@Param("role") Role role);

    List<User> findByYearOfStudyAndStudentGradesSubjectId(YearOfStudy yearOfStudy, int subjectId);

}
