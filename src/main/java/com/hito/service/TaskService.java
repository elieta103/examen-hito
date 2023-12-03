package com.hito.service;

import com.hito.dto.TaskDTO;
import java.util.List;

public interface TaskService {
    void addTask(TaskDTO taskDTO);
    TaskDTO getTaskById(Long taskId);
    List<TaskDTO> listTasks ();
    void deleteTask(Long idTask);

}
