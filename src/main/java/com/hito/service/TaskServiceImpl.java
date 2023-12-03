package com.hito.service;

import com.hito.dto.TaskDTO;
import com.hito.exception.ResourceNotFoundException;
import com.hito.mapper.Mapper;
import com.hito.model.Task;
import com.hito.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class TaskServiceImpl implements TaskService{

    private TaskRepository taskRepository;

    @Override
    public TaskDTO addTask(TaskDTO taskDTO) {
        Task task = Mapper.mapDtoToEntityTask(taskDTO);
        Task taskSaved = taskRepository.save(task);
        return Mapper.mapEntityToDto(taskSaved);
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()-> new ResourceNotFoundException("Task", "id", taskId));
        return Mapper.mapEntityToDto(task);
    }

    @Override
    public List<TaskDTO> listTasks() {
        List<TaskDTO> taskDTOList = taskRepository.findAll()
                .stream()
                .map(Mapper::mapEntityToDto)
                .collect(Collectors.toList());
        taskDTOList.stream().forEach(System.out::println);
        return taskDTOList;
    }

    @Override
    public void deleteTask(Long idTask) {
        Task task = taskRepository.findById(idTask)
                .orElseThrow(()-> new ResourceNotFoundException("Task", "id", idTask));
        taskRepository.deleteById(idTask);
    }
}
