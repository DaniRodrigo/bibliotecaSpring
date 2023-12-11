package com.drr.biblioteca.controller;


import com.drr.biblioteca.entity.Autor;
import com.drr.biblioteca.entity.Book;
import com.drr.biblioteca.entity.Editorial;
import com.drr.biblioteca.exception.MyException;
import com.drr.biblioteca.service.AutorService;
import com.drr.biblioteca.service.BookService;
import com.drr.biblioteca.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/libro") //localhost:8080/libro
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AutorService autorService;
    @Autowired
    private EditorialService editorialService;


    @GetMapping("registrar") //localhost:8080/libro/registrar
    public String registrar(ModelMap modelo) {

        List<Autor> autores = autorService.listarAutores();
        List<Editorial> editoriales = editorialService.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);


        return "libro_form.html";
    }

    @PostMapping("/registro")  //Recibe los datos del formulario
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
                           @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor,
                           @RequestParam Integer idEditorial, ModelMap modelo) {  //En el modelo insertaremos los erores del servicio al usuario por pantalla

        try {
            bookService.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);  //Será será persistido en la base de datos

            modelo.put("exito", "El libro se cargó correctamente");
        } catch (MyException e) {

            List<Autor> autores = autorService.listarAutores();
            List<Editorial> editoriales = editorialService.listarEditoriales();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);

            modelo.put("error", e.getMessage());
            return "libro_form.html";
        }

        return "index.html";
    }

    @GetMapping("lista")
    public String listar(ModelMap modelo) {
        List<Book> libros = bookService.listarLibros();
        modelo.addAttribute("libros", libros);

        return "libro_list";
    }

    @GetMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, ModelMap modelo) {

        modelo.put("libro", bookService.findById(isbn));

        List<Autor> autores = autorService.listarAutores();
        List<Editorial> editoriales = editorialService.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_modificar.html";
    }

    @PostMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, String titulo, Integer ejemplares, String idAutor, Integer idEditorial, ModelMap modelo) {
        try {
            List<Autor> autores = autorService.listarAutores();
            List<Editorial> editoriales = editorialService.listarEditoriales();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);

            bookService.modificarLibro(isbn, titulo, ejemplares, idAutor, idEditorial);


            return "redirect:../lista";

        } catch (MyException ex) {
            List<Autor> autores = autorService.listarAutores();
            List<Editorial> editoriales = editorialService.listarEditoriales();

            modelo.put("error", ex.getMessage());

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);

            return "libro_modificar.html";
        }

    }
}
