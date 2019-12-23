package io.github.stefancostin.gradeguard.repositories;

import io.github.stefancostin.gradeguard.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubjectRepository extends JpaRepository<Subject, Integer> {
}
