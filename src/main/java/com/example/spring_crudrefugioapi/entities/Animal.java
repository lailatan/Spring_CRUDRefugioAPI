package com.example.spring_crudrefugioapi.entities;

import javax.persistence.*;

@Entity
@Table(name="animales")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_animal;
    private String nombre;
    private Integer edad;
    private String color;

    public Animal() {
    }

    public Animal(Integer id_animal, String nombre, Integer edad, String color) {
        this.id_animal = id_animal;
        this.nombre = nombre;
        this.edad = edad;
        this.color = color;
    }

    public Animal(String nombre, Integer edad, String color) {
        this.nombre = nombre;
        this.edad = edad;
        this.color = color;
    }

    public Integer getId_animal() {
        return id_animal;
    }

    public void setId_animal(Integer id_animal) {
        this.id_animal = id_animal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id_animal=" + id_animal +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", color='" + color + '\'' +
                '}';
    }
}
