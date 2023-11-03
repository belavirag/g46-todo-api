package se.lexicon.g46todoapi.converter;

import se.lexicon.g46todoapi.domain.dto.TaskDTOView;
import se.lexicon.g46todoapi.domain.entity.Task;

public interface TaskConverter {
    TaskDTOView toTaskDTOView(Task task);
    Task toTaskEntity(TaskDTOView view);
}
