/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.Carrito;
import edu.app.entity.Orden;
import edu.app.entity.Producto;
import edu.app.entity.TopProductos;
import edu.app.facade.CarritoFacadeLocal;
import edu.app.facade.OrdenFacadeLocal;
import edu.app.facade.ProductoFacadeLocal;
import edu.app.util.Graficas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.charts.line.LineChartModel;

/**
 *
 * @author Tatiana Rodriguez
 */
@Named(value = "ordenView")
@ViewScoped
public class OrdenView implements Serializable {

    @EJB
    OrdenFacadeLocal ordenFacadeLocal;

    @EJB
    CarritoFacadeLocal carritoFacadeLocal;

    @Inject
    UserSession userSession;

    @EJB
    ProductoFacadeLocal productoFacadeLocal;

    Carrito objCarrito = new Carrito();
    Producto objProducto = new Producto();
    Graficas gr = new Graficas();

    private LineChartModel cartesian;
    private StringBuilder nombresP = new StringBuilder();
    private StringBuilder cantidadesP = new StringBuilder();
    private String nombresProd;
    private String cantidadesProd;
    private double total;
    private double totalEnvio;
    private String cantidadMayor;
    private String step;

    /*
    ARRAYs
     */
    private ArrayList<Orden> listaOrdenes = new ArrayList<>();
    private ArrayList<Carrito> productosVendidos = new ArrayList<>();
    private ArrayList<TopProductos> estadisticasProductos = new ArrayList<>();

    /**
     * Creates a new instance of OrdenView
     */
    public OrdenView() {
    }

    @PostConstruct
    public void cargaInicial() {
        productosVendidos.addAll(carritoFacadeLocal.listarTodos());
        for (int i = 0; i < productosVendidos.size(); i++) {
            TopProductosVendidos(productosVendidos.get(i).getProductoIdproducto().getIdproducto(), productosVendidos.get(i).getProductoIdproducto().getNombreProducto(), productosVendidos.get(i).getCantidad());
        }
        obtenerStringValoresProductos();
        listaOrdenes.addAll(ordenFacadeLocal.listarTodasOrdenes());
        for (Orden listaOrd : listaOrdenes) {
            total += listaOrd.getTotal();
            totalEnvio += listaOrd.getValorEnvio();
        }

    }

    public void removerOrden(Orden ordenRemov) {
        try {
            ordenFacadeLocal.remove(ordenRemov);
            listaOrdenes.remove(ordenRemov);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.OrdenView.removerOrden()" + e.getMessage());
        }
    }

    public List<Orden> listarOrdenUsuario() {
        return ordenFacadeLocal.listarOrdenUsuario(userSession.getUsuarioLogin().getIdusuario());
    }

    public List<TopProductos> TopProductosVendidos(int idProducto, String nombre, int cantidadProducto) {
        boolean flag = false;
        int cantidadTotal = 0;

        if (estadisticasProductos.size() > 0) {
            for (TopProductos estadisticasProducto : estadisticasProductos) {
                if (idProducto == estadisticasProducto.getIdProducto()) {
                    estadisticasProducto.setCantidad(estadisticasProducto.getCantidad() + cantidadProducto);
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) {
            estadisticasProductos.add(new TopProductos(idProducto, cantidadProducto, nombre));
        }

        return estadisticasProductos;
    }

    public void obtenerStringValoresProductos() {
        for (int i = 0; i < estadisticasProductos.size(); i++) {
            nombresP.append("\"" + estadisticasProductos.get(i).getNombre() + "\", ");
            cantidadesP.append(estadisticasProductos.get(i).getCantidad() + ",");
        }
        cantidadMayor = String.valueOf(gr.numeroMayor(estadisticasProductos) + 10);
        if (gr.numeroMayor(estadisticasProductos) > 60) {
            step = "20";
        }
        else if (gr.numeroMayor(estadisticasProductos) > 100){
            step = "50";
        }
        else{
            step = "10";
        }
        nombresP.toString();
        cantidadesP.toString();
        nombresProd = nombresP.substring(0, nombresP.length() - 2);
        cantidadesProd = cantidadesP.substring(0, cantidadesP.length() - 1);

    }

    /*
    GET y SET
     */
    public ArrayList<Orden> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaOrdenes(ArrayList<Orden> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }

    public LineChartModel getCartesian() {
        return cartesian;
    }

    public void setCartesian(LineChartModel cartesian) {
        this.cartesian = cartesian;
    }

    public ArrayList<Carrito> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(ArrayList<Carrito> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    public ArrayList<TopProductos> getEstadisticasProductos() {
        return estadisticasProductos;
    }

    public void setEstadisticasProductos(ArrayList<TopProductos> estadisticasProductos) {
        this.estadisticasProductos = estadisticasProductos;
    }

    public StringBuilder getNombresP() {
        return nombresP;
    }

    public void setNombresP(StringBuilder nombresP) {
        this.nombresP = nombresP;
    }

    public StringBuilder getCantidadesP() {
        return cantidadesP;
    }

    public void setCantidadesP(StringBuilder cantidadesP) {
        this.cantidadesP = cantidadesP;
    }

    public String getNombresProd() {
        return nombresProd;
    }

    public void setNombresProd(String nombresProd) {
        this.nombresProd = nombresProd;
    }

    public String getCantidadesProd() {
        return cantidadesProd;
    }

    public void setCantidadesProd(String cantidadesProd) {
        this.cantidadesProd = cantidadesProd;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalEnvio() {
        return totalEnvio;
    }

    public void setTotalEnvio(double totalEnvio) {
        this.totalEnvio = totalEnvio;
    }

    public String getCantidadMayor() {
        return cantidadMayor;
    }

    public void setCantidadMayor(String cantidadMayor) {
        this.cantidadMayor = cantidadMayor;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

}
