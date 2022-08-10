/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.Carrito;
import edu.app.entity.Domicilio;
import edu.app.entity.EstadoDomicilio;
import edu.app.entity.EstadoOrden;
import edu.app.entity.HoraOrden;
import edu.app.entity.Orden;
import edu.app.facade.CarritoFacadeLocal;
import edu.app.facade.DomicilioFacadeLocal;
import edu.app.facade.HoraOrdenFacadeLocal;
import edu.app.facade.OrdenFacadeLocal;
import edu.app.util.FechaHora;
import edu.app.util.Redireccion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Dark Jack
 */
@Named(value = "domicilioSession")
@SessionScoped
public class DomicilioSession implements Serializable {

    @EJB
    DomicilioFacadeLocal domicilioFacadeLocal;

    @EJB
    OrdenFacadeLocal ordenFacadeLocal;

    @EJB
    CarritoFacadeLocal carritoFacadeLocal;

    @EJB
    HoraOrdenFacadeLocal horaOrdenFacadeLocal;

    @Inject
    UserSession userSession;

    String mensajes = "";

    private List<Carrito> productosOrden = new ArrayList<>();
    private ArrayList<Orden> listaOrdenesUsuario = new ArrayList<>();

    private Orden ordenSeleccionada = new Orden();
    Redireccion redir = new Redireccion();
    FechaHora fh = new FechaHora();
    private Domicilio objDomicilio = new Domicilio();

    public DomicilioSession() {
    }

    public List<Domicilio> listarDomiciliosPedidos() {
        return domicilioFacadeLocal.listarDomicilios(1);
    }

    public List<Domicilio> listarDomiciliosPendientes() {
        return domicilioFacadeLocal.listarDomiciliosOrden(1, 5);
    }

    public List<Domicilio> listarDomiciliosEntregando() {
        return domicilioFacadeLocal.listarDomicilios(2);
    }

    public List<Domicilio> listarDomiciliosCompletados() {
        return domicilioFacadeLocal.listarDomiciliosEntregadosDia(3, fh.fecha());
    }

    public void verOrdenDomicilio(int idOrden) {
        ordenSeleccionada = ordenFacadeLocal.find(idOrden);
        productosOrden = carritoFacadeLocal.productosOrden(idOrden);
        listaOrdenesUsuario.clear();
        listaOrdenesUsuario.addAll(ordenFacadeLocal.listarOrdenUsuario(ordenSeleccionada.getUsuarioIdusuario().getIdusuario()));
        redir.redireccionar("/admin-ultimate/domiciliario/content-orden-detail.xhtml");
    }

    public void aceptarDomicilio(int idDomicilio, int idOrden) {
        try {
            ordenSeleccionada = ordenFacadeLocal.find(idOrden);
            objDomicilio = domicilioFacadeLocal.find(idDomicilio);
            objDomicilio.setNombreDomiciliario(userSession.getUsuarioLogin().getNombre() + " " + userSession.getUsuarioLogin().getApellido());
            EstadoDomicilio ed = new EstadoDomicilio();
            ed.setIdestadoDomicilio(2);
            objDomicilio.setEstadoDomicilioIdestadoDomicilio(ed);
            domicilioFacadeLocal.edit(objDomicilio);

//            CAMBIAR ESTADO ORDEN
            EstadoOrden eo = new EstadoOrden();
            eo.setIdestadoOrden(4);
            ordenSeleccionada.setEstadoOrdenIdestadoOrden(eo);
            ordenFacadeLocal.edit(ordenSeleccionada);

//            CREAR HORA DE CAMBIO DE ESTADO
            HoraOrden ho = new HoraOrden();
            ho.setOrdenIdorden(ordenSeleccionada);
            ho.setEstadoOrdenIdestadoOrden(eo);
            ho.setFecha(fh.fecha());
            ho.setHora(fh.hora());
            horaOrdenFacadeLocal.create(ho);

            mensajes = "swal('Bien Hecho!', 'Se ha aceptado el domicilio correctamente', 'success')";
            listarDomiciliosPedidos();
        } catch (Exception e) {
            mensajes = "swal('Error!', 'No se podido aceptar el domicilio', 'error')";
            System.out.println("edu.app.controlador.DomicilioSession.aceptarDomicilio()" + e.getMessage());
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void entregarDomicilio(int idDomicilio) {
        try {
            objDomicilio = domicilioFacadeLocal.find(idDomicilio);
            objDomicilio.setHoraEntrega(fh.hora());
            objDomicilio.setFechaEntrega(fh.fecha());
            EstadoDomicilio ed = new EstadoDomicilio();
            ed.setIdestadoDomicilio(3);
            objDomicilio.setEstadoDomicilioIdestadoDomicilio(ed);
            domicilioFacadeLocal.edit(objDomicilio);

//            CAMBIAR ESTADO ORDEN
            EstadoOrden eo = new EstadoOrden();
            eo.setIdestadoOrden(6);

            Orden ordenDomicilio = ordenFacadeLocal.find(objDomicilio.getOrdenIdorden().getIdorden());
            ordenDomicilio.setEstadoOrdenIdestadoOrden(eo);
            ordenFacadeLocal.edit(ordenDomicilio);

//            CREAR HORA DE CAMBIO DE ESTADO            
            HoraOrden ho = new HoraOrden();
            ho.setOrdenIdorden(objDomicilio.getOrdenIdorden());
            ho.setEstadoOrdenIdestadoOrden(eo);
            ho.setFecha(fh.fecha());
            ho.setHora(fh.hora());
            horaOrdenFacadeLocal.create(ho);

            mensajes = "swal('Bien Hecho!', 'Se ha entregado el domicilio correctamente', 'success')";
            listarDomiciliosEntregando();
        } catch (Exception e) {
            mensajes = "swal('Error!', 'No se podido entregar el domicilio', 'error')";
            System.out.println("edu.app.controlador.DomicilioSession.entregarDomicilio()" + e.getMessage());
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

//    GET
//    *
//    *
//    *
//    *
//    *
//    SET
    public Orden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setOrdenSeleccionada(Orden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public List<Carrito> getProductosOrden() {
        return productosOrden;
    }

    public void setProductosOrden(ArrayList<Carrito> productosOrden) {
        this.productosOrden = productosOrden;
    }

    public ArrayList<Orden> getListaOrdenesUsuario() {
        return listaOrdenesUsuario;
    }

    public void setListaOrdenesUsuario(ArrayList<Orden> listaOrdenesUsuario) {
        this.listaOrdenesUsuario = listaOrdenesUsuario;
    }

    public Domicilio getObjDomicilio() {
        return objDomicilio;
    }

    public void setObjDomicilio(Domicilio objDomicilio) {
        this.objDomicilio = objDomicilio;
    }
}
