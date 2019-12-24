package io.github.stefancostin.gradeguard.entities;

import io.github.stefancostin.gradeguard.utils.GradeType;

import javax.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private int subjectId;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private int studentId;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private int professorId;
    @Column(name = "grade", nullable = false)
    private int grade;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "grade_type", nullable = true)
    private GradeType gradeType;

    public Grade(int subjectId, int studentId, int professorId, int grade, GradeType gradeType) {
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.professorId = professorId;
        this.grade = grade;
        this.gradeType = gradeType;
    }

    public Grade() { }

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
