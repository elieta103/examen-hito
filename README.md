# examen-hito
Descripción del ejercicio
Crear una aplicación web de gestión de tareas (to-do list) utilizando Spring Boot. La aplicación debe permitir a los usuarios realizar las siguientes acciones:

Agregar una nueva tarea con un título y una descripción.
Ver la lista de tareas existentes.
Eliminar una tarea seleccionada.
Pasos para completar el ejercicio

Paso 1: Configuración del proyecto
Crea un nuevo proyecto Spring Boot utilizando Spring Initializr o tu IDE favorito.
Agrega las dependencias necesarias, como Spring Web, Spring Data JPA y una base de datos embebida (por ejemplo, H2).

Paso 2: Modelo de datos
Crea una clase Task que represente una tarea con propiedades como id, title, description, y completed.
Y una lista de materiales por cada tarea (nueva clase), su costo, suma y la suman de los materiales que superen 100 pesos.
Configura una entidad JPA para la clase Task, material y crea un repositorio JPA para manejar las operaciones de base de datos.
