package se.lexicon.g46todoapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository testObject;

    @Test
    void selectTasksBetweenDatesTest() {

    }

    @Test
    void selectUnFinishedTasksTest() {

    }

    @Test
    void selectUnFinishedAndOverdueTasksTest() {

    }
}
