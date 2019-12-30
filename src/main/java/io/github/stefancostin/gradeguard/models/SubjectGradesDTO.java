package io.github.stefancostin.gradeguard.models;

import io.github.stefancostin.gradeguard.entities.Grade;
import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.utils.GradeType;
import io.github.stefancostin.gradeguard.utils.Semester;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;

import java.util.HashSet;
import java.util.Set;

public class StudentGradesDTO {
    private int id;
    private String name;
    private String acronym;
    private YearOfStudy yearOfStudy;
    private Semester semester;
    private int gradeExam;
    private int gradeFinal;
    private int gradeLaboratory;
    private int gradeProject;

    public StudentGradesDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.acronym = subject.getAcronym();
        this.yearOfStudy = subject.getYearOfStudy();
        this.semester = subject.getSemester();
        this.setGrades(subject);
    }

    public StudentGradesDTO() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getGradeExam() {
        return gradeExam;
    }

    public void setGradeExam(int gradeExam) {
        this.gradeExam = gradeExam;
    }

    public int getGradeFinal() {
        return gradeFinal;
    }

    public void setGradeFinal(int gradeFinal) {
        this.gradeFinal = gradeFinal;
    }

    public int getGradeLaboratory() {
        return gradeLaboratory;
    }

    public void setGradeLaboratory(int gradeLaboratory) {
        this.gradeLaboratory = gradeLaboratory;
    }

    public int getGradeProject() {
        return gradeProject;
    }

    public void setGradeProject(int gradeProject) {
        this.gradeProject = gradeProject;
    }

    private void setGrades(Subject subject) {
        Set<Grade> grades = subject.getGrades();

        for (Grade grade : grades) {
            GradeType gradeType = grade.getGradeType();

            switch (gradeType) {
                case EXAM:
                    this.gradeExam = grade.getGrade();
                    break;
                case LABORATORY:
                    this.gradeLaboratory = grade.getGrade();
                    break;
                case FINAL:
                    this.gradeFinal = grade.getGrade();
                    break;
                case PROJECT:
                    this.gradeProject = grade.getGrade();
                    break;
                default: break;
            }
        }
    }

}
