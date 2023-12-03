package com.hito.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
public class MaterialDTO {
    private Long id;
    @NotEmpty(message = "Nombre, no debe ser null o vacío :(")
    private String nombre;
    @NotEmpty(message = "Costo, no debe ser null o vacío :(")
    private double costo;
}
