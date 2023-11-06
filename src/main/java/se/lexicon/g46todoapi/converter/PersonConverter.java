package se.lexicon.g46todoapi.converter;

import se.lexicon.g46todoapi.domain.dto.PersonDTOForm;
import se.lexicon.g46todoapi.domain.dto.PersonDTOView;
import se.lexicon.g46todoapi.domain.entity.Person;

public interface PersonConverter {
    //PersonDTOForm toPersonDTOForm();
    PersonDTOView toPersonDTOView(Person person);
    PersonDTOForm toPersonDTOForm(Person person);

    Person toPersonEntity(PersonDTOView view);
    Person toPersonEntity(PersonDTOForm form);
}
