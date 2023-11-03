package se.lexicon.g46todoapi.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g46todoapi.converter.PersonConverter;
import se.lexicon.g46todoapi.domain.dto.PersonDTOForm;
import se.lexicon.g46todoapi.domain.dto.PersonDTOView;
import se.lexicon.g46todoapi.domain.entity.Person;
import se.lexicon.g46todoapi.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonConverter personConverter;

    @Override
    public List<PersonDTOView> getAll() {
        return personRepository.findAll().stream().map(personConverter::toPersonDTOView).collect(Collectors.toList());
    }

    @Override
    public Optional<PersonDTOView> findById(Long id) {
        return personRepository.findById(id).map(personConverter::toPersonDTOView);

    }

    @Override
    public Optional<PersonDTOView> create(@NonNull PersonDTOForm form) {
        Person p = personRepository.save(personConverter.toPersonEntity(form));
        return Optional.of(personConverter.toPersonDTOView(p));
    }

    @Override
    public Optional<PersonDTOView> update(@NonNull PersonDTOForm form) {
        Person p = personRepository.update(personConverter.toPersonEntity(form));
        return Optional.of(personConverter.toPersonDTOView(p));
    }
}
