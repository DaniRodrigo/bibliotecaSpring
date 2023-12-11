package com.drr.biblioteca.controller;

import com.drr.biblioteca.entity.Autor;
import com.drr.biblioteca.entity.Editorial;
import com.drr.biblioteca.exception.MyException;
import com.drr.biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/autor") //localhost:8080/autor
public class AutorControler {

    @Autowired
    private AutorService autorService;

    @GetMapping("/registrar") //localhost:8080/autor/registrar
    public String registrar(){
        return "autor_form.html";
    }

    @PostMapping("/registro")  //El parámetro que será introducido en autor_form será registrado
    public String registro(@RequestParam String nombre, ModelMap modelo){ //Se indica el parámetro requerido

        try {
            autorService.crearAutor(nombre);  //El nombre será persistido en la base de datos

            modelo.put("exito","El autor fue registrado correctamente");

        } catch (MyException e) {
            modelo.put("error", e.getMessage());
            return "autor_form.html";
        }

        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo){

        List<Autor> autores = autorService.listarAutores();

        modelo.addAttribute("autores", autores);

        return "autor_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){

        Autor autor = autorService.getOne(id);
        modelo.put("autor", autor);

        return "autor_modificar.html";

    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo){
        try {
            autorService.modificarAutor(nombre, id);

            return "redirect:../lista";
        } catch (MyException e) {
            modelo.put("error", e.getMessage());
            return "autor_modificar.html";

        }
    }

}


