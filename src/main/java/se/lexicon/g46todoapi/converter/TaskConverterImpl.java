package se.lexicon.g46todoapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.g46todoapi.domain.dto.TaskDTOView;
import se.lexicon.g46todoapi.domain.entity.Task;

@Component
public class TaskConverterImpl implements TaskConverter {
    @Autowired
    private PersonConverter personConverter;
    @Override
    public TaskDTOView toTaskDTOView(Task task) {
        return TaskDTOView.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .done(task.isDone())
                .person(personConverter.toPersonDTOView(task.getPerson()))
                .build();
    }

    @Override
    public Task toTaskEntity(TaskDTOView view) {
        return Task.builder()
                .id(view.getId())
                .title(view.getTitle())
                .description(view.getDescription())
                .deadline(view.getDeadline())
                .done(view.isDone())
                .person(personConverter.toPersonEntity(view.getPerson()))
                .build();
    }
}
