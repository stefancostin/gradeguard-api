package io.github.stefancostin.gradeguard.repositories;

import io.github.stefancostin.gradeguard.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISubjectRepository extends JpaRepository<Subject, Integer> {

    public List<Subject> findByGradesStudentId(int studentId);

}
