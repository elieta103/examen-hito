package com.hito.repository;

import com.hito.model.Material;
import com.hito.model.Task;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashSet;
import java.util.Set;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository  taskRepository;

    @Test
    @Order(1)
    void saveTaskTest(){
        Set<Material> materialSet = new HashSet<>();

        Material material_1 = Material.builder()
                .nombre("Pintura")
                .costo(230.50)
                .build();

        Material material_2 = Material.builder()
                .nombre("Brochas")
                .costo(20.50)
                .build();


        materialSet.add(material_1);
        materialSet.add(material_2);


        Task tarea1 = Task.builder()
                .title("Pintar")
                .description("Pintar habitacion de los niños")
                .completed(false)
                .build();


        tarea1.setMateriales(materialSet);

        taskRepository.save(tarea1);

        findByIdTaskTest();
    }

    @Test
    @Order(2)
    void findByIdTaskTest() {
        System.out.println("**************  Inicio findById ***************************");
        Task saved =  taskRepository.findById(1L).orElseThrow( ()-> new EntityNotFoundException("Id no encontrado"));
        System.out.println("Task Saved : " + saved.toString());
        System.out.println("**************  Fin findById   **************************");
    }

    @Test
    @Order(3)
    void findAllTest() {
        System.out.println("**************  Inicio findAll ***************************");
        taskRepository.findAll().stream().forEach( item -> System.out.println(item.toString()));
        System.out.println("**************  Fin findAll    **************************");
    }

}
