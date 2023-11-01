package se.lexicon.g46todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.lexicon.g46todoapi.domain.entity.Task;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitle(String title);
    List<Task> findByPersonId(long id);

    List<Task> findByDone(boolean done);

    List<Task> findByDeadlineBetween(LocalDate start, LocalDate end);
    List<Task> findByDeadline(LocalDate deadLine);
    @Query("select s from Task s where s.person is null")
    List<Task> findAllUnassigned();
    @Query("select s from Task s where not s.done")
    List<Task> findAllUnfinished();

  // todo: select tasks by title - done
  // todo: select tasks by person id - done
  // todo: select tasks by status - done
  // todo: select tasks by date between start and end - done
  // todo: select tasks by deadline - done
  // todo: select all un-assigned tasks - done
  // todo: select all un-finished tasks - done
  // add more as needed...

}
