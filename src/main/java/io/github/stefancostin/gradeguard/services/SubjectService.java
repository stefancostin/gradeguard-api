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

    public Subject updateSubjectById(int id, Subject subject) {
        Subject updatedSubject = subjectRepository.findById(id).orElse(null);
        updatedSubject.setName(subject.getName());
        updatedSubject.setAcronym(subject.getAcronym());
        updatedSubject.setSemester(subject.getSemester());
        updatedSubject.setYearOfStudy(subject.getYearOfStudy());
        return subjectRepository.save(updatedSubject);
    }

    public void removeSubjectById(int id) { subjectRepository.deleteById(id); }

}
