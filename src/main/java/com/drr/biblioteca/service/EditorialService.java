package com.drr.biblioteca.service;

import com.drr.biblioteca.entity.Autor;
import com.drr.biblioteca.entity.Book;
import com.drr.biblioteca.entity.Editorial;
import com.drr.biblioteca.exception.MyException;
import com.drr.biblioteca.repository.EditorialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EditorialService {

    @Autowired
    EditorialRepository editorialRepository;

    //Indica una transacción permanante (si no hay fallos) en la base de datos
    @Transactional
    public void crearEditorial(String nombre) throws MyException{

        //Llamamos al método validador
        validar(nombre);

        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepository.save(editorial);
    }

    //Método que nos va a listar todas las editoriales que tenemos almacenadas.
    public List<Editorial> listarEditoriales(){

        List<Editorial> editorials = new ArrayList<>();

        editorials = editorialRepository.findAll();

        return editorials;
    }
    @Transactional
    public void modificarEditorial(Integer id, String nombre) throws MyException{

        //Llamamos al método validador
        validar(nombre);

        Optional<Editorial> respuesta = editorialRepository.findById(id);

        if(respuesta.isPresent()){

            Editorial editorial = respuesta.get();

            editorial.setNombre(nombre);

            editorialRepository.save(editorial);
        }
    }

    private void validar(String nombre) throws MyException {

        if(nombre.isEmpty() || nombre == null){
            throw new MyException("El nombre no puede ser nulo o estar vacío");
        }
        /*if(id.isEmpty() || id == null){
            throw new MyException("El id no puede ser nulo o estar vacío");
        }*/
    }

    public Editorial getOne(Integer id){
        return editorialRepository.getReferenceById(id);
    }
}
