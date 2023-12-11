package com.drr.biblioteca.service;

import com.drr.biblioteca.entity.Usuario;
import com.drr.biblioteca.enumeraciones.Rol;
import com.drr.biblioteca.exception.MyException;
import com.drr.biblioteca.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void registrar(String nombre, String email, String password) throws MyException{


        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setEmail(email);

        usuario.setPassword(password);

        usuario.setRol(Rol.USER);

        usuarioRepository.save(usuario);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {  //Cuando alguien se logee, Spring Security vendrá aqui

        Usuario usuario = usuarioRepository.buscarPorEmail(email);

        if(usuario != null){

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString());

            permisos.add(p);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        }else{
            return null;
        }
    }
}
