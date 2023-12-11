package com.drr.biblioteca.service;

//Van a ejecutar todas las funcionalidades necesarias para que la aplicación cumpla con todos
//los requerimientos del cliente.

import com.drr.biblioteca.entity.Autor;
import com.drr.biblioteca.entity.Book;
import com.drr.biblioteca.entity.Editorial;
import com.drr.biblioteca.exception.MyException;
import com.drr.biblioteca.repository.AutorRepository;
import com.drr.biblioteca.repository.BookRepository;
import com.drr.biblioteca.repository.EditorialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    //Indicamos al servidor de aplicaciones que esta variable va a ser inicializada por él.
    //Esto es una inyección de dependencias.
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private EditorialRepository editorialRepository;

    //Indica una transacción permanante (si no hay fallos) en la base de datos
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, Integer idEditorial) throws MyException{

        //Llamamos al método validador
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);

        Autor autor = autorRepository.findById(idAutor).get();
        Editorial editorial = editorialRepository.findById(idEditorial).get();
        Book book = new Book();

        book.setIsbn(isbn);
        book.setTitulo(titulo);
        book.setEjemplares(ejemplares);
        book.setAlta(new Date());

        book.setAutor(autor);
        book.setEditorial(editorial);

        bookRepository.save(book);   //Con todos estos datos ya podemos pedir la persistencia.
    }

    //Método que nos va a listar todos los libros que tenemos almacenados.
    public List<Book> listarLibros(){

        List<Book> books = new ArrayList<>();

        books = bookRepository.findAll();

        return books;
    }

    public void modificarLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, Integer idEditorial) throws MyException{

        //Objeto contenedor que puede o no, contener un valor no nulo.
        //Si el valor está presente nos dará true y nos retornará el valor con el método .get
        Optional<Book> respuesta = bookRepository.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepository.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepository.findById(idEditorial);

        //Llamamos al método validador
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if(respuestaAutor.isPresent()){

            autor = respuestaAutor.get();
        }

        if(respuestaEditorial.isPresent()){

            editorial = respuestaEditorial.get();
        }

        if(respuesta.isPresent()){

            Book book = respuesta.get();

            book.setTitulo(titulo);

            book.setAutor(autor);

            book.setEditorial(editorial);

            book.setEjemplares(ejemplares);

            bookRepository.save(book);
        }
    }

    //Creamos este método para validar los campos en la creación y modificación de libros.
    private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, Integer idEditorial) throws MyException{

        if(isbn == null){
            throw new MyException("El isbn no puede ser nulo");
        }
        if(titulo.isEmpty() || titulo == null){
            throw new MyException("El titulo no puede ser nulo o estar vacío");
        }
        if(ejemplares == null){
            throw new MyException("Los ejemplares no pueden ser nulos");
        }
        if(idAutor.isEmpty() || idAutor == null){
            throw new MyException("El autor no puede ser nulo o estar vacío");
        }
        if(idEditorial == null){
            throw new MyException("La editorial no puede ser nula o estar vacía");
        }
    }

    public Book findById(Long isbn) {
        return bookRepository.findById(isbn).get();
    }
}
