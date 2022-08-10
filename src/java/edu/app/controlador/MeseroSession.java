/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.Carrito;
import edu.app.entity.Mesa;
import edu.app.entity.Orden;
import edu.app.facade.CarritoFacadeLocal;
import edu.app.facade.MesaFacadeLocal;
import edu.app.facade.OrdenFacadeLocal;
import edu.app.util.FechaHora;
import edu.app.util.Redireccion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Dark Jack
 */
@Named(value = "meseroSession")
@SessionScoped
public class MeseroSession implements Serializable {

    @EJB
    MesaFacadeLocal mesaFacadeLocal;

    @EJB
    OrdenFacadeLocal ordenFacadeLocal;
    
    @EJB
    CarritoFacadeLocal carritoFacadeLocal;

    @Inject
    VentasSession ventasSession;

    private String numeroMesa = "";
    String mensajes = "";
    private String fechaHoy;
    

    FechaHora fh = new FechaHora();
    private Mesa objMesa = new Mesa();
    Redireccion redir = new Redireccion();
    private Mesa selectorMesa = new Mesa();
    private Orden ordenMesa = new Orden();

    private ArrayList<Mesa> listaMesas = new ArrayList<>();
    private List<Orden> ordenPendienteMesa = new ArrayList<>();

    public MeseroSession() {
    }

    @PostConstruct
    public void cargaInicial() {
        fechaHoy = fh.fecha();
    }

    public List<Mesa> listarTodasMesas() {
        return mesaFacadeLocal.listarTodas();
    }

    public List<Mesa> listarDisponibles() {
        return mesaFacadeLocal.listarDisponibilidad("disponible");
    }

    public List<Mesa> listarOcupadas() {
        return mesaFacadeLocal.listarDisponibilidad("ocupada");
    }

    public List<Orden> listarOrdenesMesa() {
        return ordenFacadeLocal.listarOrdenesMesa(fechaHoy, selectorMesa.getIdmesa());
    }
    
    public List<Carrito> detallesOrden() {
        return carritoFacadeLocal.productosOrden(ordenMesa.getIdorden());
    }

    public void crearMesa() {
        try {
            listarTodasMesas();
            if (numeroMesa.equals("")) {
                mensajes = "swal('Error!', 'Por favor ingrese un número de mesa', 'error')";
            } else {
                listaMesas.addAll(mesaFacadeLocal.listarTodas());
                boolean flag = false;
                for (int i = 0; i < listaMesas.size(); i++) {
                    String mesa = listaMesas.get(i).getNombreMesa();
                    if (mesa.substring(5, mesa.length()).equals(numeroMesa)) {
                        flag = true;
                        mensajes = "swal('Error!', 'La mesa ya existe', 'error')";
                        break;
                    }
                }
                if (!flag) {
                    objMesa.setNombreMesa("Mesa " + numeroMesa);
                    objMesa.setEstadoMesa("disponible");
                    mesaFacadeLocal.create(objMesa);
                    mensajes = "swal('Bien Hecho!', 'La mesa se ha creado correctamente', 'success')";
                }
            }

        } catch (Exception e) {
            mensajes = "swal('Error!', 'No se ha podido crear la mesa', 'error')";
            System.out.println("edu.app.controlador.MeseroSession.crearMesa()" + e.getMessage());
        }

        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void eliminarMesa(Mesa m) {
        mesaFacadeLocal.remove(m);
    }

    public void seleccionarMesa(int idMesa) {
        selectorMesa = mesaFacadeLocal.find(idMesa);
        listarOrdenesMesa();
        ordenPendienteMesa = ordenFacadeLocal.buscarOrdenMesa(obtenerNumeroMesa(selectorMesa.getNombreMesa()));
        
        if (ordenPendienteMesa.size() > 0) {
            ordenMesa = ordenPendienteMesa.get(0);
            redir.redireccionar("/admin-ultimate/mesero/content-mesero-orden-detail.xhtml");
        } else {
            redir.redireccionar("/admin-ultimate/mesero/agregar-productos.xhtml");
        }

    }

    public int obtenerNumeroMesa(String nombreMesa) {
        return Integer.parseInt(nombreMesa.substring(5, nombreMesa.length())) ;
    }


    /*GET
    *
    *
    SET*/
    public ArrayList<Mesa> getListaMesas() {
        return listaMesas;
    }

    public void setListaMesas(ArrayList<Mesa> listaMesas) {
        this.listaMesas = listaMesas;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Mesa getObjMesa() {
        return objMesa;
    }

    public void setObjMesa(Mesa objMesa) {
        this.objMesa = objMesa;
    }

    public Mesa getSelectorMesa() {
        return selectorMesa;
    }

    public void setSelectorMesa(Mesa selectorMesa) {
        this.selectorMesa = selectorMesa;
    }

    public String getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(String fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public List<Orden> getOrdenPendienteMesa() {
        return ordenPendienteMesa;
    }

    public void setOrdenPendienteMesa(List<Orden> ordenPendienteMesa) {
        this.ordenPendienteMesa = ordenPendienteMesa;
    }

    public Orden getOrdenMesa() {
        return ordenMesa;
    }

    public void setOrdenMesa(Orden ordenMesa) {
        this.ordenMesa = ordenMesa;
    }

}
