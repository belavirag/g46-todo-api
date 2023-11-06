package se.lexicon.g46todoapi.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.g46todoapi.converter.PersonConverter;
import se.lexicon.g46todoapi.domain.dto.TaskDTOForm;
import se.lexicon.g46todoapi.domain.dto.TaskDTOView;
import se.lexicon.g46todoapi.domain.entity.Person;
import se.lexicon.g46todoapi.domain.entity.User;
import se.lexicon.g46todoapi.repository.PersonRepository;
import se.lexicon.g46todoapi.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskServiceTest {
    @Autowired
    private TaskService testObject;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonConverter personConverter;

    @BeforeAll
    void setUp() {
        User u = userRepository.save(User.builder()
                        .email("tester@test.com")
                        .password("TestPW")
                        .roles(new HashSet<>())
                        .expired(false)
                .build());
        Person p = personRepository.save(Person.builder()
                        .name("A Person")
                        .user(u)
                        .tasks(new ArrayList<>())
                .build());
        testObject.createOrUpdate(TaskDTOForm.builder()
                        .title("Test Task")
                        .description("A task")
                        .person(personConverter.toPersonDTOForm(p))
                        .deadline(LocalDate.now().plusDays(1))
                        .done(false)
                .build());
    }

    @Test
    void getAllTest() {
        assertEquals(testObject.getAll().size(), 1);
    }

    @Test
    void findByIdTest() {
        TaskDTOView view = testObject.getAll().get(0);
        TaskDTOView foundById = testObject.findById(view.getId()).orElseThrow();

        assertEquals(view.getDescription(), foundById.getDescription());
    }
}
