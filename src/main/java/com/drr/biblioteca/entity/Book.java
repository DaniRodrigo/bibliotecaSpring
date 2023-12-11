package com.drr.biblioteca.entity;

import jakarta.persistence.*;

import java.util.Date;

//Es un objeto del cual queremos persistir sus datos en algún repositorio de almacenamiento

@Entity
public class Book {

    @Id  //Hacemos que se genere de manera automática
    private Long isbn;
    private String titulo;
    private Integer ejemplares;

    @Temporal(TemporalType.DATE)
    private Date alta;

    @ManyToOne  //Estas clases se van a relacionar de muchos a uno (Muchos libros van a tener un autor)
    private Autor autor;

    @ManyToOne  //Estas clases se van a relacionar de muchos a uno (Muchos libros van a tener una editorial)
    private Editorial editorial;

    public Book() {
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
}
