/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.Usuario;
import edu.app.facade.UsuarioFacadeLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author GAES 6 - 20205329
 */
@Named(value = "registroView")
@ViewScoped
public class RegistroView implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    private String tipoMensaje = "";
    private String correoIn = "";
    private String claveIn = "";

    private Usuario objUser = new Usuario();

    public RegistroView() {
    }

    public void registrarUsuario() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-YYYY");
        String dateString = sdf.format(date);

        try {
            objUser.setFechaRegistro(dateString);
            usuarioFacadeLocal.create(objUser);
            tipoMensaje = "ok_R";
            objUser = new Usuario();

        } catch (Exception e) {
            tipoMensaje = "no_R";
            System.out.println("edu.app.controlador.RegistroView.registrarUsuario()" + e.getMessage());
        }
    }

    public Usuario getObjUser() {
        return objUser;
    }

    public void setObjUser(Usuario objUser) {
        this.objUser = objUser;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

    public String getClaveIn() {
        return claveIn;
    }

    public void setClaveIn(String claveIn) {
        this.claveIn = claveIn;
    }

}
