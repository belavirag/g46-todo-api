package se.lexicon.g46todoapi.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.g46todoapi.domain.entity.Task;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository testObject;

    @BeforeAll
    void setUp() {
        testObject.save(Task.builder()
                .title("A title")
                .description("Test task")
                .deadline(LocalDate.now().plusDays(1))
                .done(false)
                .build());

        testObject.save(Task.builder()
                .title("Another Task")
                .description("Just a test task")
                .deadline(LocalDate.now().plusDays(3))
                .done(true)
                .build());

        testObject.save(Task.builder()
                .title("Yet Another Task")
                .description("One more test task")
                .deadline(LocalDate.now().minusDays(1)) // overdue
                .done(false) // and not done
                .build());
    }

    @Test
    void selectTasksBetweenDatesTest() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        List<Task> tasks = testObject.selectTasksBetweenDates(today, tomorrow);
        assertEquals(tasks.size(), 1);
        Task task = tasks.get(0);
        assertNotNull(task);
        assertEquals(task.getDescription(), "Test task");
    }

    @Test
    void selectUnFinishedTasksTest() {
        List<Task> unfinishedTasks = testObject.selectUnFinishedTasks();
        assertEquals(unfinishedTasks.size(), 2);
        assertTrue(unfinishedTasks.stream().noneMatch(Task::isDone));
    }

    @Test
    void selectUnFinishedAndOverdueTasksTest() {
        List<Task> unfinishedAndOverdueTasks = testObject.selectUnFinishedAndOverdueTasks();
        assertEquals(unfinishedAndOverdueTasks.size(), 1);
        Task task = unfinishedAndOverdueTasks.get(0);
        assertNotNull(task);
        assertFalse(task.isDone());
        assertTrue(LocalDate.now().isAfter(task.getDeadline()));
    }
}
