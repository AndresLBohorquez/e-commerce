/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.Rol;
import edu.app.entity.Usuario;
import edu.app.facade.DireccionFacadeLocal;
import edu.app.facade.RolFacadeLocal;
import edu.app.facade.TelefonoFacadeLocal;
import edu.app.facade.UsuarioFacadeLocal;
import edu.app.util.Crypto;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author david
 */
@Named(value = "registroRequest")
@RequestScoped
public class RegistroRequest implements Serializable {

    /**
     * Creates a new instance of RegistroRequest
     */
    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    @EJB
    RolFacadeLocal rolFacadeLocal;

    @EJB
    DireccionFacadeLocal direccionFacadeLocal;

    @EJB
    TelefonoFacadeLocal telefonoFacadeLocal;

    private ArrayList<Rol> listRol = new ArrayList<>();

    private String email;
    private String dni;
    private String clave;
    private String nombre;
    private String apellido;
    private String direccion;
    private String barrio;
    private String telefono1;
    private String telefono2;
    private int rol_idrol;
    private int estado_usuario_idestado_usuario;
    private String fecha_registro;
    private String fecha_login;
    private Usuario regUsuario = new Usuario();
    Crypto crypto = new Crypto();

    private String tipoMensaje = "";

    public RegistroRequest() {
    }

    @PostConstruct
    public void cargaInicial() {
        listRol.addAll(rolFacadeLocal.findAll());
    }

    public ArrayList<Rol> getListRol() {
        return listRol;
    }

    public void setListRol(ArrayList<Rol> listRol) {
        this.listRol = listRol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public int getRol_idrol() {
        return rol_idrol;
    }

    public void setRol_idrol(int rol_idrol) {
        this.rol_idrol = rol_idrol;
    }

    public int getEstado_usuario_idestado_usuario() {
        return estado_usuario_idestado_usuario;
    }

    public void setEstado_usuario_idestado_usuario(int estado_usuario_idestado_usuario) {
        this.estado_usuario_idestado_usuario = estado_usuario_idestado_usuario;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getFecha_login() {
        return fecha_login;
    }

    public void setFecha_login(String fecha_login) {
        this.fecha_login = fecha_login;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public void registrarUsuarioAdmin() {
        String mensajes = "";
        try {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);
            String contra = crypto.encriptar("Somos los mejores", clave);
            boolean respuesta = usuarioFacadeLocal.registrarUsuario(email, dni, contra, nombre, apellido, rol_idrol, 1, strDate, strDate);
            regUsuario = usuarioFacadeLocal.loginUsuario(email, contra);
            boolean respuestaDir = direccionFacadeLocal.registrarDireccion(direccion, barrio, regUsuario.getIdusuario(), "principal");
            boolean respuestaTel = telefonoFacadeLocal.registrarTelefono(telefono1, regUsuario.getIdusuario(), "principal");

            email = "";
            dni = "";
            clave = "";
            nombre = "";
            apellido = "";
            telefono1 = "";
            direccion = "";
            barrio = "";

            if (respuesta && respuestaDir && respuestaTel) {
                mensajes = "swal('Bien Hecho!', 'El usuario se ha registrado correctamente!', 'success')";
            } else {
                mensajes = "swal('Error!', 'No se ha podido registrar al usuario!', 'error')";
            }
        } catch (Exception e) {
            System.out.println("edu.app.controlador.RegistroRequest.registrarUsuario()" + e.getMessage());
            mensajes = "swal('Error!', 'No se ha podido registrar al usuario!', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public Usuario getRegUsuario() {
        return regUsuario;
    }

    public void setRegUsuario(Usuario regUsuario) {
        this.regUsuario = regUsuario;
    }

}
