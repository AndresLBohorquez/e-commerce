/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.*;
import edu.app.facade.*;
import edu.app.util.Crypto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Dark Jack
 */
@Named(value = "administracionView")
@ViewScoped
public class AdministracionView implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    @EJB
    RolFacadeLocal rolFacadeLocal;

    @EJB
    EstadoUsuarioFacadeLocal estadoUsuarioFacadeLocal;

    @EJB
    MessageFacadeLocal messageFacadeLocal;

    @EJB
    PqrsFacadeLocal pqrsFacadeLocal;

    @EJB
    OrdenFacadeLocal ordenFacadeLocal;

    @Inject
    UserSession userSession;

    /*
    VARIABLES
     */
    private String correoIn;
    private String email;
    private String clave;
    private String tipoMensaje = "";
    private int idusuario;
    private int rol_idrol;
    private int estado_usuario_idestado_usuario;
    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String barrio;
    private String telefono1;
    private String telefono2;
    String mensajes;

    /*
    ARRAYS
     */
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private ArrayList<Rol> listUserRol = new ArrayList<>();
    private ArrayList<EstadoUsuario> userStateList = new ArrayList<>();
    private ArrayList<Message> listMessages = new ArrayList<>();
    private ArrayList<Pqrs> pqrsList = new ArrayList<>();

    /*
      OBJ instancias
     */
    private Usuario usuarioSelect = new Usuario();
    Crypto crypto = new Crypto();

    @PostConstruct
    public void cargaInicial() {
        listaUsuarios.addAll(usuarioFacadeLocal.findAll());
        listUserRol.addAll(rolFacadeLocal.findAll());
        userStateList.addAll(estadoUsuarioFacadeLocal.findAll());
        listMessages.addAll(messageFacadeLocal.findAll());
        pqrsList.addAll(pqrsFacadeLocal.findAll());
    }

    public void remover(Usuario usuR, int idUsuario) {
        try {
            boolean removerMensajes = messageFacadeLocal.eliminarMensajes(idUsuario);
            boolean removerPqrs = pqrsFacadeLocal.eliminarPqrs(idUsuario);
            boolean removerOrdenes = ordenFacadeLocal.eliminarOrdenesUsuario(idUsuario);
            usuarioFacadeLocal.remove(usuR);
            listaUsuarios.remove(usuR);
            mensajes = "swal('Bien Hecho!', 'El usuario se ha eliminado correctamente!', 'success')";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.AdministracionView.remover()" + e.getMessage());
            mensajes = "swal('Error!', 'El usuario no se ha podido eliminar!', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void actualizarUsuario(int idUser) {
        try {

            if (email.equals("")) {
                email = userSession.getPerfilUsuario().getEmail();
            }
            if (dni.equals("")) {
                dni = userSession.getPerfilUsuario().getDni();
            }
            if (clave.equals("")) {
                clave = crypto.desencriptar("Somos los mejores", userSession.getPerfilUsuario().getClave());
            }
            if (nombre.equals("")) {
                nombre = userSession.getPerfilUsuario().getNombre();
            }
            if (apellido.equals("")) {
                apellido = userSession.getPerfilUsuario().getApellido();
            }
            clave = userSession.encode(userSession.secretKey, clave);
            boolean respuesta = usuarioFacadeLocal.actualizarUsuario(email, dni, clave, nombre, apellido, rol_idrol, estado_usuario_idestado_usuario, idUser);
            listaUsuarios.addAll(usuarioFacadeLocal.findAll());
            email = "";
            clave = "";
            idusuario = 0;
            rol_idrol = 0;
            estado_usuario_idestado_usuario = 0;
            dni = "";
            nombre = "";
            apellido = "";

            if (respuesta) {
                mensajes = "swal('Bien Hecho!', 'La información se ha actualizado correctamente', 'success')";
            } else {
                mensajes = "swal('Error!', 'No se ha podido actualizar la informacion', 'error')";
            }
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.actualizarUsuario()" + e.getMessage());
            mensajes = "swal('Error!', 'No se ha podido actualizar la informacion', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public List<Usuario> listaUsuariosUltimate() {
        return usuarioFacadeLocal.listarUsuarios();
    }

    /*
    
    GET
    
    SET
    
     */
    public ArrayList<Message> getListMessages() {
        return listMessages;
    }

    public void setListMessages(ArrayList<Message> listMessages) {
        this.listMessages = listMessages;
    }

    public ArrayList<Pqrs> getPqrsList() {
        return pqrsList;
    }

    public void setPqrsList(ArrayList<Pqrs> pqrsList) {
        this.pqrsList = pqrsList;
    }

    public AdministracionView() {
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuarioSelect() {
        return usuarioSelect;
    }

    public void setUsuarioSelect(Usuario usuarioSelect) {
        this.usuarioSelect = usuarioSelect;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public ArrayList<Rol> getListUserRol() {
        return listUserRol;
    }

    public void setListUserRol(ArrayList<Rol> listUserRol) {
        this.listUserRol = listUserRol;
    }

    public ArrayList<EstadoUsuario> getUserStateList() {
        return userStateList;
    }

    public void setUserStateList(ArrayList<EstadoUsuario> userStateList) {
        this.userStateList = userStateList;
    }

}
