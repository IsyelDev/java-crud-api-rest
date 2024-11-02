package org.isyel.bibliotecav1.Excepciones;

public class ValidationException extends Exception{
    public ValidationException(String mensaje){
        super(mensaje);
    }
}
