package com.hito.dto;

import com.hito.model.Material;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@ToString
public class TaskDTO {

    private Long id;
    @NotEmpty(message = "Title, no debe ser null o vacío :(")
    private String title;
    @NotEmpty(message = "Description, no debe ser null o vacío :(")
    private String description;
    @NotNull(message = "Completed, no debe ser null :(")
    private Boolean completed;
    @NotNull(message = "Materiales, no debe ser null :(")
    private Set<MaterialDTO> materialesDTO = new HashSet<>();
}