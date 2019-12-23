package io.github.stefancostin.gradeguard.controllers;

import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Subject> getSubjects() {
        return subjectService.getSubjects();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Subject getSubjectById(@PathVariable("id") int id) {
        return subjectService.getSubjectById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Subject insertSubject(@RequestBody Subject subject) {
        return subjectService.insertSubject(subject);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeSubjectById(@PathVariable("id") int id) {
        subjectService.removeSubjectById(id);
    }

}
