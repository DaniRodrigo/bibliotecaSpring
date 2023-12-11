package com.drr.biblioteca.exception;

//Generamos esta clase para diferenciar los errores que tengamos de la lógica negocio
//con los propios del sistema.
public class MyException extends Exception{

    public MyException(String msg) {
        super(msg);
    }
}
