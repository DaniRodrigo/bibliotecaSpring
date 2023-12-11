package com.drr.biblioteca.controller;


import com.drr.biblioteca.entity.Autor;
import com.drr.biblioteca.entity.Editorial;
import com.drr.biblioteca.exception.MyException;
import com.drr.biblioteca.service.AutorService;
import com.drr.biblioteca.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/editorial") //localhost:8080/editorial
public class EditorialControler {

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/registrar") //localhost:8080/editorial/registrar
    public String registrar(){
        return "editorial_form.html";
    }

    @PostMapping("/registro")  //El parámetro que será introducido en editorial_form será registrado
    public String registro(@RequestParam String nombre, ModelMap modelo){ //Se indica el parámetro requerido

        try {
            editorialService.crearEditorial(nombre);  //El nombre será persistido en la base de datos

            modelo.put("exito", "Editorial creada con éxito");
        } catch (MyException e) {
            modelo.put("error", e.getMessage());
            return "editorial_form.html";
        }

        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo){

        List<Editorial> editoriales = editorialService.listarEditoriales();

        modelo.addAttribute("editoriales", editoriales);

        return "editorial_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Integer id, ModelMap modelo){
        Editorial editorial = editorialService.getOne(id);
        modelo.put("editorial", editorial);


        return "editorial_modificar.html";

    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable Integer id, String nombre, ModelMap modelo){
        try {
            editorialService.modificarEditorial(id, nombre);

            return "redirect:../lista";
        } catch (MyException e) {
            modelo.put("error", e.getMessage());
            return "editorial_modificar.html";

        }
    }
}
