package com.example.spring_crudrefugioapi.controllers;

import com.example.spring_crudrefugioapi.entities.Animal;
import com.example.spring_crudrefugioapi.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("animales")
public class AnimalController {
    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    //PathVariable /buscar/2 (GET)
    //RequestParamen buscar?id=2 (GET)
    //RequestParamen (POST)

    @GetMapping("animales")
    public List<Animal> getAnimales(){
        return animalService.getAnimales();
    }

    @GetMapping("animales/nombre/{nombre}")
    public List<Animal> getAnimalesByNombreContaining(@PathVariable String nombre){
        return animalService.getAnimalesByNombreContaining(nombre);
    }

    @GetMapping("animales/color/{color}")
    public List<Animal> getAnimalesByColorEquals(@PathVariable String color){
        return  animalService.getAnimalesByColorEquals(color);
    }

    @GetMapping("animales/edad/{edadDesde}/{edadHasta}")
    public List<Animal> getAnimalesByEdadBetween(@PathVariable String edadDesde, @PathVariable String edadHasta){
        return animalService.getAnimalesByEdadBetween(edadDesde,edadHasta);
    }

    @GetMapping("animales/id/{id}")
    public List<Animal> getAnimalById(@PathVariable String id){
        return  List.of(animalService.getAnimalById(id));
    }


    @GetMapping("animales/buscar")
    public List<Animal> buscar(@RequestParam Optional<String> nombreORid, @RequestParam Optional<String> color, @RequestParam Optional<Integer> edad){
        if(color.isPresent() && !color.get().equals(("0")))
            return getAnimalesByColorEquals(color.get());
        if(edad.isPresent() && edad.get()>0)
            return getAnimalesByEdadBetween("0",edad.get().toString());
        if(nombreORid.isPresent() && !nombreORid.get().isEmpty())
            try {
                Integer id = Integer.parseInt(nombreORid.get());
                return getAnimalById(id.toString());
            }catch (NumberFormatException e){

                return getAnimalesByNombreContaining(nombreORid.get());
            }
        return getAnimales();
    }

    @PostMapping("animales/guardar")
    public Animal guardarAnimal(@RequestBody Animal animal){
        animalService.guardarAnimal(animal);
        return animal;
    }

    @DeleteMapping("animales/borrar/{id}")
    public void eliminarAnimal(@PathVariable String id){
        animalService.eliminarAnimalById(id);
    }
}


