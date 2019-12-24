package io.github.stefancostin.gradeguard.entities;

import io.github.stefancostin.gradeguard.utils.GradeType;

import javax.persistence.*;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "grade", nullable = false)
    private int grade;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "grade_type", nullable = true)
    private GradeType gradeType;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private User professor;

    public Grade(int id, int grade, GradeType gradeType, Subject subject, User student, User professor) {
        this.id = id;
        this.grade = grade;
        this.gradeType = gradeType;
        this.subject = subject;
        this.student = student;
        this.professor = professor;
    }

    public Grade() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }
}
