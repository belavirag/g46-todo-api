package se.lexicon.g46todoapi.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.g46todoapi.domain.entity.Person;
import se.lexicon.g46todoapi.domain.entity.Task;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository testObject;
    @Autowired
    private TaskRepository taskRepository;

    @BeforeAll
    void setUp() {
        final Task task = Task.builder()
                .title("Test task")
                .description("Test")
                .deadline(LocalDate.now().plusDays(1))
                .done(false)
                .build();
        testObject.save(Person.builder().name("Idle Test Person").build());
        Person savedPerson = testObject.save(Person.builder()
                .name("Non-idle Test Person")
                .tasks(new ArrayList<>())
                .build().addTask(task));
        task.setPerson(savedPerson);
        taskRepository.save(task);
    }

    @Test
    void findIdlePeopleTest() {
        List<Person> people = testObject.findIdlePeople();

        assertEquals(people.size(), 1);
        Person p = people.get(0);
        assertNotNull(p);
        assertEquals(p.getName(), "Idle Test Person");
    }
}
