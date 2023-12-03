package com.hito.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hito.dto.MaterialDTO;
import com.hito.dto.TaskDTO;

import com.hito.exception.ErrorDetails;
import com.hito.exception.ResourceNotFoundException;
import com.hito.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenTaskObject_whenCreateTask_thenReturnSavedTask() throws Exception{
        // given - precondition or setup
        TaskDTO tarea1 = getTask();

        given(taskService.addTask(any(TaskDTO.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarea1)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.title",
                        is(tarea1.getTitle())))
                .andExpect(jsonPath("$.description",
                        is(tarea1.getDescription())))
                .andExpect(jsonPath("$.completed",
                        is(tarea1.getCompleted())));

    }

    @Test
    public void givenListOfTasks_whenGetAllTasks_thenReturnTasksList() throws Exception{
        // given - precondition or setup
        List<TaskDTO> listOfTasks = getListTasks();

        given(taskService.listTasks()).willReturn(listOfTasks);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/tasks"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfTasks.size())));

    }

    @Test
    public void givenTaskId_whenGetTaskById_thenReturnTaskObject() throws Exception{
        // given - precondition or setup
        long taskId = 1L;
        TaskDTO tarea1 = getTask();

        given(taskService.getTaskById(taskId)).willReturn(tarea1);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/tasks/{id}", taskId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.title", is(tarea1.getTitle())))
                .andExpect(jsonPath("$.description", is(tarea1.getDescription())))
                .andExpect(jsonPath("$.completed", is(tarea1.getCompleted())));

    }

    @Test
    public void givenInvalidTaskId_whenGetTaskById_thenReturnEmpty() throws Exception{
        // given - precondition or setup
        long taskId = 1L;
        TaskDTO tarea1 = getTask();

        given(taskService.getTaskById(taskId)).willThrow(ResourceNotFoundException.class);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/tasks/{id}", taskId));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    public void givenTaskId_whenDeleteTask_thenReturn200() throws Exception{
        // given - precondition or setup
        long taskId = 1L;
        willDoNothing().given(taskService).deleteTask(taskId);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/api/tasks/{id}", taskId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }


    private TaskDTO getTask(){
        Set<MaterialDTO> materialSet = new HashSet<>();
        MaterialDTO material_1 = MaterialDTO.builder()
                .nombre("Pintura")
                .costo(230.50)
                .build();
        materialSet.add(material_1);
        TaskDTO tarea1 = TaskDTO.builder()
                .title("Pintar")
                .description("Pintar habitacion de los niños")
                .completed(false)
                .materialesDTO(materialSet)
                .build();
        return  tarea1;
    }

    private List<TaskDTO> getListTasks(){
        List<TaskDTO> listOfTasks = new ArrayList<>();

        Set<MaterialDTO> materialSet1 = new HashSet<>();
        MaterialDTO material1 = MaterialDTO.builder()
                .nombre("Pintura")
                .costo(230.50)
                .build();
        materialSet1.add(material1);
        TaskDTO tarea1 = TaskDTO.builder()
                .title("Pintar")
                .description("Pintar habitacion de los niños")
                .completed(false)
                .materialesDTO(materialSet1)
                .build();

        Set<MaterialDTO> materialSet2 = new HashSet<>();
        MaterialDTO material2 = MaterialDTO.builder()
                .nombre("Pintura")
                .costo(230.50)
                .build();
        materialSet2.add(material2);
        TaskDTO tarea2 = TaskDTO.builder()
                .title("Pintar")
                .description("Pintar habitacion de los niños")
                .completed(false)
                .materialesDTO(materialSet2)
                .build();

        listOfTasks.add(tarea1);
        listOfTasks.add(tarea2);

        return  listOfTasks;
    }
}




