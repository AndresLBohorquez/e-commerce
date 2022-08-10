/*
 * To change this license header choose License Headers in Project Properties.
 * To change this template file choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.*;
import edu.app.facade.*;
import edu.app.util.FechaHora;
import java.util.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author david
 */
@Named(value = "pqrsView")
@ViewScoped
public class PqrsView implements Serializable {

    @EJB
    PqrsFacadeLocal pqrsFacadeLocal;

    @EJB
    EstadoPqrsFacadeLocal estadoPqrsFacadeLocal;

    @EJB
    TipoPqrsFacadeLocal tipoPqrsFacadeLocal;

    @EJB
    PrioridadFacadeLocal prioridadFacadeLocal;

    @EJB
    MessageFacadeLocal messageFacadeLocal;

    @Inject
    UserSession userSession;

    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private ArrayList<TipoPqrs> ListaTipoPqrs = new ArrayList<>();
    private ArrayList<EstadoPqrs> listaEstadoPqrs = new ArrayList<>();
    private ArrayList<Prioridad> listaPrioridadPqrs = new ArrayList<>();
    private ArrayList<Pqrs> listaPqrs = new ArrayList<>();
    private ArrayList<Message> listMessages = new ArrayList<>();

    /*
        Cargando Listas
        Constructores
     */
    @PostConstruct
    public void cargaInicial() {
        listaEstadoPqrs.addAll(estadoPqrsFacadeLocal.findAll());
        ListaTipoPqrs.addAll(tipoPqrsFacadeLocal.findAll());
        listaPrioridadPqrs.addAll(prioridadFacadeLocal.findAll());
        listaPqrs.addAll(pqrsFacadeLocal.findAll());
        listMessages.addAll(messageFacadeLocal.findAll());
    }

    public PqrsView() {
    }

    /*
    VARIABLE's
     */
    private String message_text;
    private String fecha_registro_pqrs;
    private String fecha_actualizacion_pqrs;
    private String descripcion;
    private int usuario_idusuario;
    private int estado_pqrs_idestado_pqrs;
    private int tipo_pqrs_idtipo_pqrs;
    private int prioridad_idprioridad;
    private Pqrs pqrsObj = new Pqrs();
    FechaHora fh = new FechaHora();

    /*
    Metodos GET y SET
     */
    public String getFecha_registro_pqrs() {
        return fecha_registro_pqrs;
    }

    public void setFecha_registro_pqrs(String fecha_registro_pqrs) {
        this.fecha_registro_pqrs = fecha_registro_pqrs;
    }

    public String getFecha_actualizacion_pqrs() {
        return fecha_actualizacion_pqrs;
    }

    public void setFecha_actualizacion_pqrs(String fecha_actualizacion_pqrs) {
        this.fecha_actualizacion_pqrs = fecha_actualizacion_pqrs;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getUsuario_idusuario() {
        return usuario_idusuario;
    }

    public void setUsuario_idusuario(int usuario_idusuario) {
        this.usuario_idusuario = usuario_idusuario;
    }

    public int getEstado_pqrs_idestado_pqrs() {
        return estado_pqrs_idestado_pqrs;
    }

    public void setEstado_pqrs_idestado_pqrs(int estado_pqrs_idestado_pqrs) {
        this.estado_pqrs_idestado_pqrs = estado_pqrs_idestado_pqrs;
    }

    public int getTipo_pqrs_idtipo_pqrs() {
        return tipo_pqrs_idtipo_pqrs;
    }

    public void setTipo_pqrs_idtipo_pqrs(int tipo_pqrs_idtipo_pqrs) {
        this.tipo_pqrs_idtipo_pqrs = tipo_pqrs_idtipo_pqrs;
    }

    public int getPrioridad_idprioridad() {
        return prioridad_idprioridad;
    }

    public void setPrioridad_idprioridad(int prioridad_idprioridad) {
        this.prioridad_idprioridad = prioridad_idprioridad;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<TipoPqrs> getListaTipoPqrs() {
        return ListaTipoPqrs;
    }

    public void setListaTipoPqrs(ArrayList<TipoPqrs> ListaTipoPqrs) {
        this.ListaTipoPqrs = ListaTipoPqrs;
    }

    public ArrayList<EstadoPqrs> getListaEstadoPqrs() {
        return listaEstadoPqrs;
    }

    public void setListaEstadoPqrs(ArrayList<EstadoPqrs> listaEstadoPqrs) {
        this.listaEstadoPqrs = listaEstadoPqrs;
    }

    public ArrayList<Prioridad> getListaPrioridadPqrs() {
        return listaPrioridadPqrs;
    }

    public void setListaPrioridadPqrs(ArrayList<Prioridad> listaPrioridadPqrs) {
        this.listaPrioridadPqrs = listaPrioridadPqrs;
    }

    public ArrayList<Pqrs> getListaPqrs() {
        return listaPqrs;
    }

    public void setListaPqrs(ArrayList<Pqrs> listaPqrs) {
        this.listaPqrs = listaPqrs;
    }

    public ArrayList<Message> getListMessages() {
        return listMessages;
    }

    public void setListMessages(ArrayList<Message> listMessages) {
        this.listMessages = listMessages;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public Pqrs getPqrsObj() {
        return pqrsObj;
    }

    public void setPqrsObj(Pqrs pqrsObj) {
        this.pqrsObj = pqrsObj;
    }

    /*
    METHOD's
     */
    public void registrarPqrs() {
        try {
            boolean respuesta = pqrsFacadeLocal.registrarPqrs(fh.fecha(), fh.fecha(), descripcion, userSession.getUsuarioLogin().getIdusuario(), 1, tipo_pqrs_idtipo_pqrs, prioridad_idprioridad);
            System.out.println("************ " + respuesta + " *********************");
        } catch (Exception e) {
            System.out.println("edu.app.controlador.PqrsView.registrarPqrs()" + e.getMessage());
        }
    }

    public void registerMessage(int id_pqrs) {
        try {
            Pqrs p = pqrsFacadeLocal.find(id_pqrs);
            EstadoPqrs ep = new EstadoPqrs();
            if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Administrador")) {
                ep.setIdestadoPqrs(2);
                p.setEstadoPqrsIdestadoPqrs(ep);
            } else if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Cliente")) {
                List<Message> cantidadMensajes = new ArrayList<>();
                cantidadMensajes.addAll(messageFacadeLocal.listarPqrs(id_pqrs));
                if (cantidadMensajes.size() > 0) {
                    ep.setIdestadoPqrs(3);
                    p.setEstadoPqrsIdestadoPqrs(ep);
                }
            }
            p.setFechaActualizacionPqrs(fh.fecha());
            pqrsFacadeLocal.edit(p);
            boolean respuesta = messageFacadeLocal.registerMessage(message_text, userSession.getUsuarioLogin().getIdusuario(), id_pqrs);
            System.out.println("************* Respuesta ***************** " + respuesta);
            message_text = "";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.PqrsView.registerMessage()" + e.getMessage());
        }
    }

    public void eliminarPqrs(Pqrs pqrsRemov, int idUsuario) {
        try {
            boolean eliminarMensajes = messageFacadeLocal.eliminarMensajes(idUsuario);
            pqrsFacadeLocal.remove(pqrsRemov);
            listaPqrs.remove(pqrsRemov);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.PqrsView.eliminarPqrs()" + e.getMessage());
        }
    }

    public List<Pqrs> listarPqrsNuevas() {
        return pqrsFacadeLocal.listarPqrs(1);
    }

    public List<Pqrs> listarPqrsCerradas() {
        return pqrsFacadeLocal.listarPqrs(2);
    }

    public List<Pqrs> listarPqrsReiteradas() {
        return pqrsFacadeLocal.listarPqrs(3);
    }

}
