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

    public SubjectDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.acronym = subject.getAcronym();
        this.yearOfStudy = subject.getYearOfStudy();
        this.semester = subject.getSemester();
        this.professors = convertFromModelToDTO(subject.getProfessors());
        if (!this.professors.isEmpty())
        System.out.println(("this: " + this.professors.get(0).getId()));
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

    private List<UserDTO> convertFromModelToDTO(Set<User> professorsModelSet) {
        List<UserDTO> professorsList = new ArrayList<>();
        for (User professorModel : professorsModelSet) {
            UserDTO professor = new UserDTO();
            professor.setId(professorModel.getId());
            professor.setFirstName(professorModel.getFirstName());
            professor.setLastName(professorModel.getLastName());
            professor.setEmail(professorModel.getEmail());
            professor.setPassword(professorModel.getPassword());
            professor.setRole(professorModel.getRole());
            professorsList.add(professor);
        }
        return professorsList;
    }
    
}
