package com.hito.controller;

import com.hito.dto.TaskDTO;
import com.hito.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RequestMapping("api/tasks")
@RestController
public class TaskController {

    private TaskService taskService;

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody @Valid TaskDTO taskDTO){
        taskService.addTask(taskDTO);
        return new ResponseEntity<>("Task y sus materiales, guardados exitosamente. "+LocalDateTime.now(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> listAllTasks(){
        return new ResponseEntity<>(taskService.listTasks(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("id") Long taskId){
        return new ResponseEntity<>(taskService.getTaskById(taskId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long taskId){
        taskService.deleteTask(taskId);
        return new ResponseEntity<>("Tasks y sus materiales, fueron borrados exitosamente !!", HttpStatus.OK);
    }
}
