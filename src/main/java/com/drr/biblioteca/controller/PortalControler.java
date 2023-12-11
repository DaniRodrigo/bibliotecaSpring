package com.drr.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")  //Configura la url que va a escuchar a este controlador. localhost:8080/
public class PortalControler {

    @GetMapping("/")  //Se ejecutará este cuerpo html cuando se acceda a "/"
    public String index(){

        return "index.html";  //Devolverá ésta vista
    }
}
