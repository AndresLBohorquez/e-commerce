/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.util;

import org.primefaces.PrimeFaces;

/**
 *
 * @author andre
 */
public class Alerts {

    String mensajes;
    public void alertasOK(String info) {
               
        mensajes = "swal('Bien Hecho!', '" + info + "', 'success')";
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void alertasKO(String info){
        
        mensajes = "swal('Error!', '" + info + "', 'error')";
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
        
    }
}
