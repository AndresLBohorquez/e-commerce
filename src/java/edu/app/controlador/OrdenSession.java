/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.*;
import edu.app.facade.OrdenFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author david
 */
@Named(value = "ordenSession")
@SessionScoped
public class OrdenSession implements Serializable {

    @EJB
    OrdenFacadeLocal ordenFacadeLocal;

    @Inject
    UserSession userSession;
    
    private ArrayList<Producto> carrito = new ArrayList<>();

    private Orden ordenObj = new Orden();
    
    private ArrayList<Orden> listaOrdenesPendientesUsuario = new ArrayList<>();
    
    public OrdenSession() {
    }

   
    public List<Orden> listarOrdenPendientesUsuario() {
        return ordenFacadeLocal.listarOrdenesPendientesUsuario(userSession.getUsuarioLogin().getIdusuario());
    }

    
    
    /*
    GET y SET
     */
    public ArrayList<Producto> getCarrito() {
        return carrito;
    }

    public void setCarrito(ArrayList<Producto> carrito) {
        this.carrito = carrito;
    }

    public Orden getOrdenObj() {
        return ordenObj;
    }

    public void setOrdenObj(Orden ordenObj) {
        this.ordenObj = ordenObj;
    }

    public ArrayList<Orden> getListaOrdenesPendientesUsuario() {
        return listaOrdenesPendientesUsuario;
    }

    public void setListaOrdenesPendientesUsuario(ArrayList<Orden> listaOrdenesPendientesUsuario) {
        this.listaOrdenesPendientesUsuario = listaOrdenesPendientesUsuario;
    }

}
