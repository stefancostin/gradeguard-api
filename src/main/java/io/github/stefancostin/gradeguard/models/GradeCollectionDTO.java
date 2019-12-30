package io.github.stefancostin.gradeguard.models;

import io.github.stefancostin.gradeguard.entities.Grade;
import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.utils.GradeType;

import java.util.Set;

public class GradeCollectionDTO {
    private int gradeExam;
    private int gradeFinal;
    private int gradeLaboratory;
    private int gradeProject;

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

    protected void setGrades(Set<Grade> grades) {
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
