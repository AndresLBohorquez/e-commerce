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
public class GeneradorCodigos {

    public String generarCodigo(String tipoCodigo) {

        char[] mayusculas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] minusculas = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] numeros = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

        StringBuilder caracteres = new StringBuilder();
        caracteres.append(mayusculas);
        caracteres.append(minusculas);
        caracteres.append(numeros);

        StringBuilder codigo = new StringBuilder();
        codigo.append(tipoCodigo);
        for (int i = 0; i <= 6; i++) {
            int cantidadCaracteres = caracteres.length();
            int numeroRandom = (int) (Math.random() * cantidadCaracteres);

            codigo.append((caracteres.toString()).charAt(numeroRandom));
        }
        return codigo.toString();
    }
}

