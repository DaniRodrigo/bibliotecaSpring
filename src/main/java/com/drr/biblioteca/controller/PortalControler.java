package com.drr.biblioteca.controller;

import com.drr.biblioteca.entity.Usuario;
import com.drr.biblioteca.exception.MyException;
import com.drr.biblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller //Configura la url que va a escuchar a este controlador. localhost:8080/
public class PortalControler {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")  //Se ejecutará este cuerpo html cuando se acceda a "/"
    public String index(){

        return "index.html";  //Devolverá ésta vista
    }

    @GetMapping("/registrar")
    public String registrar(Model modelo){

        modelo.addAttribute("usuario", new Usuario());
        return "registro.html";
    }

    @PostMapping("/registrar")
    public String registro(@ModelAttribute("usuario") @Valid Usuario usuario, ModelMap modelo, BindingResult bindingResult){


        try {
            if(bindingResult.hasErrors()){
                modelo.addAttribute("error", true);
                return "registro.html";
            }
            usuarioService.registrar(usuario.getNombre(), usuario.getEmail(),passwordEncoder.encode(usuario.getPassword()));

            modelo.put("exito", "Usuario registrado correctamente");

            return "index.html";

        } catch (MyException e) {

            modelo.put("error", e.getMessage());

            return "registro.html";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
}
