/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.*;
import edu.app.facade.OrdenFacadeLocal;
import edu.app.facade.PqrsFacadeLocal;
import edu.app.facade.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author David Salamanca
 */
@Named(value = "administracionSession")
@Dependent
public class AdministracionSession implements Serializable{
    
    @EJB
    PqrsFacadeLocal pqrsFacadeLocal;
    
    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;
    
    @EJB
    OrdenFacadeLocal ordenFacadeLocal;
    
    /**
     * Creates a new instance of AdministracionSession
     */
    public AdministracionSession() {
    }
    
    Usuario usuarioSelect = new Usuario();
    
    public void seleccionarUsuario(int idUsuario){
        usuarioSelect = usuarioFacadeLocal.find(idUsuario);
    }
    
    public List <Pqrs> listarPqrsUsuario(){
        return pqrsFacadeLocal.listarPorUsuario(usuarioSelect.getIdusuario());
    }
    
    public List <Orden> listarOrdenUsuario(){
        return ordenFacadeLocal.listarOrdenUsuario(usuarioSelect.getIdusuario());
    }
    
    public List <Usuario> listarDatosUsuario(){
        return usuarioFacadeLocal.listarDatosUsuario(usuarioSelect.getIdusuario());
    }
            
    /*
    GET y SET
    */

    public Usuario getUsuarioSelect() {
        return usuarioSelect;
    }

    public void setUsuarioSelect(Usuario usuarioSelect) {
        this.usuarioSelect = usuarioSelect;
    }
    
    
    
    
}
