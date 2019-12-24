package io.github.stefancostin.gradeguard.controllers;

import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.models.SubjectDTO;
import io.github.stefancostin.gradeguard.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @RequestMapping(method = RequestMethod.GET)
    public List<SubjectDTO> getSubjects() {
        return subjectService.getSubjects();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SubjectDTO getSubjectById(@PathVariable("id") int id) {
        return subjectService.getSubjectById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SubjectDTO insertSubject(@RequestBody SubjectDTO subject) {
        return subjectService.insertSubject(subject);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SubjectDTO updateSubjectById(@PathVariable("id") int id, @RequestBody SubjectDTO subject) {
        return subjectService.updateSubjectById(id, subject);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeSubjectById(@PathVariable("id") int id) {
        subjectService.removeSubjectById(id);
    }

}
