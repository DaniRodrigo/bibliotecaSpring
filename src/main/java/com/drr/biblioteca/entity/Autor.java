package com.drr.biblioteca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Es un objeto del cual queremos persistir sus datos en algún repositorio de almacenamiento

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  //Da un valor autogenerado
    private Integer id;
    private String nombre;

    public Autor() {
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
