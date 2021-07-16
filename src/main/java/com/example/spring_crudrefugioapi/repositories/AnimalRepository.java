package com.example.spring_crudrefugioapi.repositories;

import com.example.spring_crudrefugioapi.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    public List<Animal> findAnimalesByNombreContaining(String nombre);

    public List<Animal> findAnimalesByColorEquals(String color);

    public List<Animal> findAnimalesByEdadBetween(Integer edadDesde, Integer edadHasta);

    @Query("SELECT DISTINCT animal.color FROM Animal animal")
    public List<String> findDistinctByColor();

    /*
    En la anotación @Query se puede agregar parámetros de esta manera.
    @Query("SELECT * FROM Persona persona WHERE persona.nombre = :#{#cliente.nombre}")
    List<Persona> findPersonaByNombre(@Param("cliente") Cliente cliente);
    Más info:
    https://spring.io/blog/2014/07/15/spel-support-in-spring-data-jpa-query-definitions
     */
}
