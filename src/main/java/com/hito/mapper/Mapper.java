package com.hito.mapper;

import com.hito.dto.MaterialDTO;
import com.hito.dto.TaskDTO;
import com.hito.model.Material;
import com.hito.model.Task;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Mapper {

    public static TaskDTO mapEntityToDto(Task task) {
        Set<MaterialDTO> materialSet = task.getMateriales()
                .stream().map(item -> {
                    MaterialDTO dto = MaterialDTO.builder()
                            .id(item.getId())
                            .nombre(item.getNombre())
                            .costo(item.getCosto())
                            .build();
                    return dto;
                }).collect(Collectors.toSet());

        TaskDTO taskDTO = TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.getCompleted())
                .materialesDTO(materialSet)
                .build();
        return taskDTO;
    }

    public static Task mapDtoToEntityTask(TaskDTO taskDto) {
        Set<Material> materialSet = taskDto.getMaterialesDTO()
                .stream().map(item -> {
                    Material material = Material.builder()
                            .nombre(item.getNombre())
                            .costo(item.getCosto())
                            .build();
                    return material;
                }).collect(Collectors.toSet());

        Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .completed(taskDto.getCompleted())
                .materiales(materialSet)
                .build();
        return task;
    }


}
