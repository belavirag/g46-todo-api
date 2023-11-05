package se.lexicon.g46todoapi.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g46todoapi.converter.PersonConverter;
import se.lexicon.g46todoapi.converter.TaskConverter;
import se.lexicon.g46todoapi.converter.UserConverter;
import se.lexicon.g46todoapi.converter.UserConverterImpl;
import se.lexicon.g46todoapi.domain.dto.TaskDTOForm;
import se.lexicon.g46todoapi.domain.dto.TaskDTOView;
import se.lexicon.g46todoapi.domain.entity.Task;
import se.lexicon.g46todoapi.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskConverter taskConverter;
    @Autowired
    private PersonConverter personConverter;
    @Autowired
    private UserConverter userConverter;
    @Override
    public List<TaskDTOView> getAll() {
        return taskRepository.findAll().stream().map(taskConverter::toTaskDTOView).collect(Collectors.toList());
    }

    @Override
    public Optional<TaskDTOView> findById(Long id) {
        return taskRepository.findById(id).map(taskConverter::toTaskDTOView);
    }

    @Override
    public Optional<TaskDTOView> createOrUpdate(@NonNull TaskDTOForm form) {
        Task task = taskRepository.save(taskConverter.toTaskEntity(form));
        return Optional.of(taskConverter.toTaskDTOView(task));
    }
}
