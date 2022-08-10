/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.util;

/**
 *
 * @author andre
 */
public class Caracteres {

    public String caracteresEspeciales(String nombre) {
        String resultado;
        resultado = nombre.toLowerCase().replace("ü", "u");
        resultado = resultado.replace("ñ", "n");
        resultado = resultado.replace("á", "a");
        resultado = resultado.replace("é", "e");
        resultado = resultado.replace("í", "i");
        resultado = resultado.replace("ó", "o");
        resultado = resultado.replace("ú", "u");
        resultado = resultado.replace("ä", "a");
        resultado = resultado.replace("ë", "e");
        resultado = resultado.replace("ï", "i");
        resultado = resultado.replace("ö", "o");
        resultado = resultado.replace("à", "a");
        resultado = resultado.replace("è", "e");
        resultado = resultado.replace("ì", "i");
        resultado = resultado.replace("ò", "o");
        resultado = resultado.replace("ù", "u");
        return resultado;
    }

}
