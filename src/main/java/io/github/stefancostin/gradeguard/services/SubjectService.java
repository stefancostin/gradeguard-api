package io.github.stefancostin.gradeguard.services;

import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.models.SubjectDTO;
import io.github.stefancostin.gradeguard.repositories.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    ISubjectRepository subjectRepository;

    public List<SubjectDTO> getSubjects() {
        return subjectRepository.findAll().stream().map(subject -> new SubjectDTO(subject)).collect(Collectors.toList());
    }

    public SubjectDTO getSubjectById(int id) {
        Subject subjectModel = subjectRepository.findById(id).orElse(null);
        return new SubjectDTO(subjectModel);
    }

    public List<SubjectDTO> getSubjectsAndGradesByStudentId(int studentId) {
        return subjectRepository.findByGradesStudentId(studentId)
                .stream().map(subject -> new SubjectDTO(subject)).collect(Collectors.toList());
    }

    public SubjectDTO insertSubject(SubjectDTO subject) {
        Subject subjectModel = new Subject(subject);
        Subject insertedSubject = subjectRepository.save(subjectModel);
        return new SubjectDTO(insertedSubject);
    }

    public SubjectDTO updateSubjectById(int id, SubjectDTO subject) {
        Subject updatedSubject = subjectRepository.findById(id).orElse(null);
        updatedSubject.setName(subject.getName());
        updatedSubject.setAcronym(subject.getAcronym());
        updatedSubject.setSemester(subject.getSemester());
        updatedSubject.setYearOfStudy(subject.getYearOfStudy());
        Subject result = subjectRepository.save(updatedSubject);
        return new SubjectDTO(result);
    }

    public void removeSubjectById(int id) { subjectRepository.deleteById(id); }

}
