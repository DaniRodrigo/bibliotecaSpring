package com.drr.biblioteca.service;

import com.drr.biblioteca.entity.Autor;
import com.drr.biblioteca.entity.Book;
import com.drr.biblioteca.exception.MyException;
import com.drr.biblioteca.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    //Indica una transacción permanante (si no hay fallos) en la base de datos
    @Transactional
    public void crearAutor(String nombre) throws MyException{

        //Llamamos al método validador
        validar(nombre);

        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepository.save(autor);
    }

    //Método que nos va a listar todos los autores que tenemos almacenados.
    public List<Autor> listarAutores(){

        List<Autor> autors = new ArrayList<>();

        autors = autorRepository.findAll();

        return autors;
    }

    public void modificarAutor(String nombre, String id) throws MyException{

        //Llamamos al método validador
        validar(nombre);

        Optional<Autor> respuesta = autorRepository.findById(id);

        if(respuesta.isPresent()){
            Autor autor = respuesta.get();

            autor.setNombre(nombre);

            autorRepository.save(autor);
        }
    }

    public Autor getOne(String id){
        return autorRepository.getReferenceById(id);
    }

    private void validar(String nombre) throws MyException {

        if(nombre.isEmpty() || nombre == null){
            throw new MyException("El nombre no puede ser nulo o estar vacío");
        }
        /*if(id.isEmpty() || id == null){
            throw new MyException("El id no puede ser nulo o estar vacío");
        }*/
    }

}

