package io.github.stefancostin.gradeguard.models;

public class GradePersistenceDTO {
    private Integer subjectId;
    private Integer studentId;
    private Integer professorId;
    private Integer gradeExam;
    private Integer gradeFinal;
    private Integer gradeLaboratory;
    private Integer gradeProject;

    public GradePersistenceDTO(Integer subjectId, Integer studentId,
        Integer professorId, Integer gradeExam, Integer gradeFinal, Integer gradeLaboratory, Integer gradeProject) {
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.professorId = professorId;
        this.gradeExam = gradeExam;
        this.gradeFinal = gradeFinal;
        this.gradeLaboratory = gradeLaboratory;
        this.gradeProject = gradeProject;
    }

    public GradePersistenceDTO() { }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    public Integer getGradeExam() {
        return gradeExam;
    }

    public void setGradeExam(Integer gradeExam) {
        this.gradeExam = gradeExam;
    }

    public Integer getGradeFinal() {
        return gradeFinal;
    }

    public void setGradeFinal(Integer gradeFinal) {
        this.gradeFinal = gradeFinal;
    }

    public Integer getGradeLaboratory() {
        return gradeLaboratory;
    }

    public void setGradeLaboratory(Integer gradeLaboratory) {
        this.gradeLaboratory = gradeLaboratory;
    }

    public Integer getGradeProject() {
        return gradeProject;
    }

    public void setGradeProject(Integer gradeProject) {
        this.gradeProject = gradeProject;
    }
}
