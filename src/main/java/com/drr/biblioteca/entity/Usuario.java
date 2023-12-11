package com.drr.biblioteca.entity;

import com.drr.biblioteca.enumeraciones.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  //Da un valor autogenerado
    private Integer id;

    @NotBlank(message = "El nombre no puede ser nulo ni estar vacío")
    private String nombre;
    @NotBlank(message = "El email no puede ser nulo ni estar vacío")
    @Email(message = "El email debe contener un email real")
    private String email;

    @NotBlank(message = "La contraseña no puede ser nula ni estar vacía")
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
