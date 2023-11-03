package se.lexicon.g46todoapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.g46todoapi.domain.dto.PersonDTOForm;
import se.lexicon.g46todoapi.domain.dto.PersonDTOView;
import se.lexicon.g46todoapi.domain.entity.Person;

import java.util.stream.Collectors;

@Component
public class PersonConverterImpl implements PersonConverter {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private TaskConverter taskConverter;

    @Override
    public PersonDTOView toPersonDTOView(Person person) {
        return PersonDTOView.builder()
                .id(person.getId())
                .name(person.getName())
                .user(userConverter.toUserDTO(person.getUser()))
                .tasks(person.getTasks().stream().map(taskConverter::toTaskDTOView).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Person toPersonEntity(PersonDTOView view) {
        return Person.builder()
                .id(view.getId())
                .name(view.getName())
                .user(userConverter.toUserEntity(view.getUser()))
                .tasks(view.getTasks().stream().map(taskConverter::toTaskEntity).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Person toPersonEntity(PersonDTOForm form) {
        return Person.builder()
                .id(form.getId())
                .name(form.getName())
                .tasks(form.getTasks().stream().map(taskConverter::toTaskEntity).collect(Collectors.toList()))
                .build();
    }
}
