package io.github.stefancostin.gradeguard.models;

import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.utils.Semester;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SubjectDTO {
    private int id;
    private String name;
    private String acronym;
    private YearOfStudy yearOfStudy;
    private Semester semester;
    private List<UserDTO> professors;
    private List<GradeDTO> grades;

    public SubjectDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.acronym = subject.getAcronym();
        this.yearOfStudy = subject.getYearOfStudy();
        this.semester = subject.getSemester();
        this.professors = UserDTO.convertFromModelToDTO(subject.getProfessors());
        this.grades = GradeDTO.convertFromModelToDTO(subject.getGrades());
    }

    public SubjectDTO(int id, String name, String acronym, YearOfStudy yearOfStudy, Semester semester) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.yearOfStudy = yearOfStudy;
        this.semester = semester;
    }

    public SubjectDTO() { }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public YearOfStudy getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(YearOfStudy yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<UserDTO> getProfessors() {
        return professors;
    }

    public void setProfessors(List<UserDTO> professors) {
        this.professors = professors;
    }

    public List<GradeDTO> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeDTO> grades) {
        this.grades = grades;
    }

    public static List<SubjectDTO> convertFromModelToDTO(Set<Subject> subjectsModelSet) {
        List<SubjectDTO> subjectsList = new ArrayList<>();
        for (Subject subjectModel : subjectsModelSet) {
            SubjectDTO subject = new SubjectDTO();
            subject.setId(subjectModel.getId());
            subject.setName(subjectModel.getName());
            subject.setAcronym(subjectModel.getAcronym());
            subject.setYearOfStudy(subjectModel.getYearOfStudy());
            subject.setSemester(subjectModel.getSemester());
            subjectsList.add(subject);
        }
        return subjectsList;
    }
    
}
