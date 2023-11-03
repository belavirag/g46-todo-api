package se.lexicon.g46todoapi.service;

import se.lexicon.g46todoapi.domain.dto.TaskDTOForm;
import se.lexicon.g46todoapi.domain.dto.TaskDTOView;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<TaskDTOView> getAll();
    Optional<TaskDTOView> findById(Long id);
    Optional<TaskDTOView> create(TaskDTOForm form);
    Optional<TaskDTOView> update(TaskDTOForm form);
}
