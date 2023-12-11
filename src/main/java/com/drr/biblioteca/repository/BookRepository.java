package com.drr.biblioteca.repository;

import com.drr.biblioteca.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//Hacen el crud y buscan objetos de nuestro dominicio. Acceden a los datos

@Repository  //Creamos una interfaz que extiende de JPA, que manejará Book cuya PK será Long
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT l FROM Book l WHERE l.titulo = :titulo")
    public Book buscarPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT l FROM Book l WHERE l.autor.nombre = :nombre")
    public List<Book> buscarPorAutor(@Param("nombre")String nombre);
}
