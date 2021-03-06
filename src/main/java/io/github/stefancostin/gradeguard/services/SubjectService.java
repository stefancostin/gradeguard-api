package io.github.stefancostin.gradeguard.services;

import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.models.SubjectGradesDTO;
import io.github.stefancostin.gradeguard.models.SubjectDTO;
import io.github.stefancostin.gradeguard.repositories.ISubjectRepository;
import io.github.stefancostin.gradeguard.utils.Semester;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private ISubjectRepository subjectRepository;

    public List<SubjectDTO> getSubjects() {
        return subjectRepository.findAll().stream().map(subject -> new SubjectDTO(subject)).collect(Collectors.toList());
    }

    public SubjectDTO getSubjectById(int id) {
        Subject subjectModel = subjectRepository.findById(id).orElse(null);
        return new SubjectDTO(subjectModel);
    }

    public List<SubjectDTO> getSubjectsByYearAndSemester(YearOfStudy yearOfStudy, Semester semester) {
        return subjectRepository.findByYearOfStudyAndSemester(yearOfStudy, semester)
                .stream().map(subject -> new SubjectDTO(subject)).collect(Collectors.toList());
    }

    public List<SubjectGradesDTO> getStudentGrades(int studentId, YearOfStudy yearOfStudy, Semester semester) {
        Map<Integer, Subject> uniqueSubjectsMap = new HashMap<>();
        return subjectRepository.findByYearOfStudyAndSemesterAndGradesStudentId(yearOfStudy, semester, studentId)
                .stream()
                .filter(subject -> !uniqueSubjectsMap.containsKey(subject.getId()))
                .peek(subject -> {
                    // store subject ids in a hash map in order to filter out duplicate subjects
                    if (!uniqueSubjectsMap.containsKey(subject.getId())) {
                        uniqueSubjectsMap.put(subject.getId(), subject);
                    }
                })
                .map(subject -> new SubjectGradesDTO(subject)).collect(Collectors.toList());
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
