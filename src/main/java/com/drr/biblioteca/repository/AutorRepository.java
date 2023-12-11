package com.drr.biblioteca.repository;

import com.drr.biblioteca.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Hacen el crud y buscan objetos de nuestro dominicio. Acceden a los datos

@Repository  //Creamos una interfaz que extiende de JPA, que manejará Book cuya PK será String
public interface AutorRepository extends JpaRepository<Autor,String> {
}
