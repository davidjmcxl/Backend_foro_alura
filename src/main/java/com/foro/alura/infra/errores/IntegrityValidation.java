package com.foro.alura.infra.errores;

public class IntegrityValidation extends RuntimeException {
public IntegrityValidation(String s){
    super(s);
}
}
