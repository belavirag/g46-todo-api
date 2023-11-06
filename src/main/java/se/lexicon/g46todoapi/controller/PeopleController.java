package se.lexicon.g46todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g46todoapi.domain.dto.PersonDTOForm;
import se.lexicon.g46todoapi.domain.dto.PersonDTOView;
import se.lexicon.g46todoapi.service.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {
    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTOView>> getAll() {
        return ResponseEntity.ok().body(personService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PersonDTOView>> findById(@PathVariable long id) {
        return ResponseEntity.ok().body(personService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Optional<PersonDTOView>> createOrUpdate(@RequestBody PersonDTOForm form) {
        return ResponseEntity.ok().body(personService.createOrUpdate(form));
    }
}
