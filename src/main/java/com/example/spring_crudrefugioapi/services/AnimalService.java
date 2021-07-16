package com.example.spring_crudrefugioapi.services;

import com.example.spring_crudrefugioapi.entities.Animal;
import com.example.spring_crudrefugioapi.repositories.AnimalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAnimales(){
        return animalRepository.findAll();
    }

    public List<Animal> getAnimalesByNombreContaining(String nombre){
        return animalRepository.findAnimalesByNombreContaining(nombre);
    }

    public List<Animal>  getAnimalesByColorEquals(String color){
        return animalRepository.findAnimalesByColorEquals(color);
    }

    public List<Animal> getAnimalesByEdadBetween(String edadDesde, String edadHasta){
        Integer edadDesdeInt = 0;
        Integer edadHastaInt = 0;
        try{
            if(edadDesde!=null) edadDesdeInt = Integer.valueOf(edadDesde);
            if(edadHasta!=null) edadHastaInt = Integer.valueOf(edadHasta);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        return animalRepository.findAnimalesByEdadBetween(edadDesdeInt,edadHastaInt);
    }

    public Animal getAnimalById(String id){
        Integer unId = 0;
        try{
            if(id!=null) unId = Integer.valueOf(id);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        return animalRepository.findById(unId).orElse(null);
    }

    public void guardarAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public void eliminarAnimalById(String id) {
        Integer unId = 0;
        try{
            if(id!=null) unId = Integer.valueOf(id);
            if (!animalRepository.existsById(unId))
                throw new IllegalStateException("El dato NO existe");
            animalRepository.deleteById(unId);
        }catch(NumberFormatException e){
            throw new IllegalStateException("El dato NO es numérico");
        }
    }

    public List<String> getColores(){
        return animalRepository.findDistinctByColor();
    }
}
