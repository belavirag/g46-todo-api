package se.lexicon.g46todoapi.service;

import se.lexicon.g46todoapi.domain.dto.PersonDTOForm;
import se.lexicon.g46todoapi.domain.dto.PersonDTOView;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonDTOView> getAll();
    Optional<PersonDTOView> findById(Long id);
    Optional<PersonDTOView> createOrUpdate(PersonDTOForm form);}
