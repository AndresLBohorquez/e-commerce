/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author david
 */
@Named(value = "carritoSession")
@SessionScoped
public class CarritoSession implements Serializable {

    /**
     * Creates a new instance of CarritoSession
     */
    public CarritoSession() {
    }
    
}
