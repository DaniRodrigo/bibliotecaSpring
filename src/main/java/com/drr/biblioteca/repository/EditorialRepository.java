package com.drr.biblioteca.repository;

//Hacen el crud y buscan objetos de nuestro dominicio. Acceden a los datos

import com.drr.biblioteca.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //Creamos una interfaz que extiende de JPA, que manejará Book cuya PK será String
public interface EditorialRepository extends JpaRepository<Editorial,Integer> {
}
