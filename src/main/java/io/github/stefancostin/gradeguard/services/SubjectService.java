package io.github.stefancostin.gradeguard.services;

import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.repositories.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SubjectService {

    @Autowired
    ISubjectRepository subjectRepository;

    public Collection<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(int id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public Subject insertSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

}
