package io.github.stefancostin.gradeguard.models;

import io.github.stefancostin.gradeguard.utils.GradeType;

public class GradeDTO {
    private int id;
    private int subjectId;
    private int studentId;
    private int professorId;
    private int grade;
    private GradeType gradeType;

    public GradeDTO(int id, int subjectId, int studentId, int professorId, int grade, GradeType gradeType) {
        this.id = id;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.professorId = professorId;
        this.grade = grade;
        this.gradeType = gradeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public GradeType getGradeType() {
        return gradeType;
    }

    public void setGradeType(GradeType gradeType) {
        this.gradeType = gradeType;
    }

}
