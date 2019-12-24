package io.github.stefancostin.gradeguard.repositories;

import io.github.stefancostin.gradeguard.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findBySubjectId(int subjectId);

    List<Grade> findByStudentId(int studentId);

}
