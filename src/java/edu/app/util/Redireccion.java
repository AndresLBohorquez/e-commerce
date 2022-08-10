/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author andre
 */
public class Redireccion {

    public void redireccionar(String rutaRedir) {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            String ctx = ext.getRequestContextPath();
            fc.getExternalContext().redirect(ctx + rutaRedir);
        } catch (Exception e) {
            System.out.println("edu.app.util.Redireccion.redireccionar()" + e.getMessage());
        }
    }
}
