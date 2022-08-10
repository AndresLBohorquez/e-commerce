/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.*;
import edu.app.facade.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import edu.app.util.*;
import org.primefaces.PrimeFaces;

/**
 *
 * @author andre
 */
@Named(value = "ventasSession")
@SessionScoped
public class VentasSession implements Serializable {

    @EJB
    ProductoFacadeLocal productoFacadeLocal;

    @EJB
    OrdenFacadeLocal ordenFacadeLocal;

    @EJB
    DireccionFacadeLocal direccionFacadeLocal;

    @EJB
    TelefonoFacadeLocal telefonoFacadeLocal;

    @EJB
    ValorEnvioFacadeLocal valorEnvioFacadeLocal;

    @EJB
    EstadoOrdenFacadeLocal estadoOrdenFacadeLocal;

    @EJB
    CarritoFacadeLocal carritoFacadeLocal;

    @EJB
    HoraOrdenFacadeLocal horaOrdenFacadeLocal;

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    @EJB
    CategoriaFacadeLocal categoriaFacadeLocal;

    @EJB
    MesaFacadeLocal mesaFacadeLocal;

    @EJB
    DomicilioFacadeLocal domicilioFacadeLocal;

    @Inject
    UserSession userSession;

    @Inject
    ProductSession productSession;

    @Inject
    MeseroSession meseroSession;

    private Producto productoObj = new Producto();
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private ArrayList<Orden> listaOrdenes = new ArrayList<>();
    private ArrayList<Orden> listaOrdenesPendientes = new ArrayList<>();
    private ArrayList<Orden> listaOrdenesCompletadas = new ArrayList<>();
    private ArrayList<Orden> listaOrdenesPreparacion = new ArrayList<>();
    private ArrayList<Orden> listaOrdenesEnviando = new ArrayList<>();
    private ArrayList<Orden> listaOrdenesUsuario = new ArrayList<>();
    private List<HoraOrden> listaHoraOrden = new ArrayList<>();
    private ArrayList<Articulo> carritoMesero = new ArrayList<>();
    private ArrayList<Carrito> productosPendientesParrilla = new ArrayList<>();
    private ArrayList<Carrito> productosPreparacionParrilla = new ArrayList<>();
    private ArrayList<Carrito> productosCompletadosParrilla = new ArrayList<>();
    private ArrayList<Carrito> productosPendientesCocina = new ArrayList<>();
    private ArrayList<Carrito> productosPreparacionCocina = new ArrayList<>();
    private ArrayList<Carrito> productosCompletadosCocina = new ArrayList<>();

    private String fecha;
    private int numeroMesa = 0;
    private double total;
    private double subTotal = 0;
    private int estadoOrden;
    private String codigoOrden = "ORDEN-";
    private String telefono;
    private String direccion;
    private String nombres;
    private Direccion objetoDir = new Direccion();
    private double paga_con = 0;
    private double valorEnvio;
    private EstadoOrden estadoOrdenObj = new EstadoOrden();
    String mensajes;
    private String codigoRastreo;
    EmailRequest emailRequest = new EmailRequest();
    private int cantidadActualProductoOrden;
    private int idCategoria;
    private String lblRangoFechas;
    private String rangoFechas;
    String fechaIni;
    String fechaFin;

    public VentasSession() {
    }

    private ValorEnvio valorEnvioObj = new ValorEnvio();
    private Orden objetoOrden = new Orden();
    private GeneradorCodigos generadorCodigos = new GeneradorCodigos();
    private Direccion objetoDireccion = new Direccion();
    Redireccion redir = new Redireccion();
    Alerts alertas = new Alerts();
    private Carrito objetoCarrito = new Carrito();
    private HoraOrden horaOrdenObj = new HoraOrden();
    private HoraOrden horaOrdenCompletada = new HoraOrden();
    private Domicilio objDomicilio = new Domicilio();

    private Orden selectorOrden = new Orden();
    private Usuario objOrdenUsuario = new Usuario();
    private Carrito selectorCarrito = new Carrito();
    private Orden orden = new Orden();
    private Orden selectorOrdenUsuario = new Orden();
    private Categoria objCategoria = new Categoria();
    private Producto objProducto = new Producto();
    private Direccion objDirPrincipal = new Direccion();
    private Telefono objTelPrincipal = new Telefono();
    private Carrito cambiarEstado = new Carrito();
    private Orden cambiarEstadoOrden = new Orden();
    EstadoOrden estadoOrdenEstado = new EstadoOrden();
    FechaHora fh = new FechaHora();
    private Mesa cambiarEstadoMesa = new Mesa();

    @PostConstruct
    public void cargaInicial() {
        valorEnvioObj = valorEnvioFacadeLocal.find(1);
        estadoOrdenObj = estadoOrdenFacadeLocal.find(1);
        listaProductos.addAll(productoFacadeLocal.listarTodosProductos());
        listaOrdenes.addAll(ordenFacadeLocal.listarTodasOrdenes());
        listaOrdenesPendientes.addAll(ordenFacadeLocal.listaOrdenesPendientes());
        listaOrdenesCompletadas.addAll(ordenFacadeLocal.listaOrdenesCompletadas());
        listaOrdenesPreparacion.addAll(ordenFacadeLocal.listaOrdenesPreparacion());
        listaOrdenesEnviando.addAll(ordenFacadeLocal.listaOrdenesEnviando());
        objCategoria = categoriaFacadeLocal.find(1);
        productosPendientesParrilla.addAll(carritoFacadeLocal.ordenesParrillaCocina("Parrilla", "no preparado"));
        productosPendientesCocina.addAll(carritoFacadeLocal.ordenesParrillaCocina("Cocina", "no preparado"));
        lblRangoFechas = fh.fecha();
        fechaIni = fh.fecha();
        fechaFin = fh.fecha();

    }

    public Direccion imprimirDirPrincipal() {
        return direccionFacadeLocal.listarDirPrincipal(userSession.getUsuarioLogin().getIdusuario(), "principal");
    }

    public List<Direccion> imprimirDirNornal() {
        return direccionFacadeLocal.listarDirNormal(userSession.getUsuarioLogin().getIdusuario(), "normal");
    }

    public Telefono imprimirTelPrincipal() {
        return telefonoFacadeLocal.listarTelPrincipal(userSession.getUsuarioLogin().getIdusuario(), "principal");
    }

    public List<Telefono> imprimirTelNormal() {
        return telefonoFacadeLocal.listarTelNormal(userSession.getUsuarioLogin().getIdusuario(), "normal");
    }

    public void actualizarValorEnvio() {
        valorEnvioObj.setValorEnvio(valorEnvio);
        valorEnvioFacadeLocal.edit(valorEnvioObj);
    }

    public void crearOrden() {
        try {
            if (productSession.getSubtotal() > 0) {
                double total = 0;
                restarProductos();
                objetoOrden.setUsuarioIdusuario(userSession.getUsuarioLogin());
                objetoOrden.setFecha(fh.fecha());
                objetoOrden.setHora(fh.hora());

                codigoRastreo = generadorCodigos.generarCodigo(codigoOrden);
                objetoOrden.setCodigoOrden(codigoRastreo);

                objetoOrden.setPagaCon(paga_con);
                objetoOrden.setEstadoOrdenIdestadoOrden(estadoOrdenFacadeLocal.find(1));

                if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Mesero")) {
                    nombres = meseroSession.getSelectorMesa().getNombreMesa();
                    numeroMesa = Integer.parseInt(meseroSession.getSelectorMesa().getNombreMesa().substring(5, meseroSession.getSelectorMesa().getNombreMesa().length()));
                    objetoOrden.setTelefono(meseroSession.getSelectorMesa().getNombreMesa());
                    objetoOrden.setDireccion(meseroSession.getSelectorMesa().getNombreMesa());
                    objetoOrden.setBarrio("La Esquina Llanera");
                    objetoOrden.setValorEnvio(0.0);
                    objetoOrden.setNombres(nombres);
                    total = productSession.getSubtotal();
                    Mesa m = meseroSession.getSelectorMesa();
                    m.setEstadoMesa("ocupada");
                    mesaFacadeLocal.edit(m);

                } else {
                    nombres = userSession.getUsuarioLogin().getNombre() + " " + userSession.getUsuarioLogin().getApellido();
                    numeroMesa = 0;
                    objetoOrden.setNombres(nombres);
                    objetoOrden.setTelefono(telefono);
                    objetoOrden.setDireccion(direccion);
                    objetoDireccion = direccionFacadeLocal.buscarBarrio(userSession.getUsuarioLogin().getIdusuario(), direccion);
                    objetoOrden.setBarrio(objetoDireccion.getBarrio());
                    objetoOrden.setValorEnvio(valorEnvioObj.getValorEnvio());
                    total = productSession.getSubtotal() + valorEnvioObj.getValorEnvio();
                }
                objetoOrden.setTotal(total);
                objetoOrden.setNumeroMesa(numeroMesa);
                objetoOrden.setSubtotal(productSession.getSubtotal());
                objetoOrden.setPago("pendiente");
                ordenFacadeLocal.create(objetoOrden);

                horaOrdenObj.setOrdenIdorden(objetoOrden);
                horaOrdenObj.setEstadoOrdenIdestadoOrden(estadoOrdenObj);
                horaOrdenObj.setFecha(fh.fecha());
                horaOrdenObj.setHora(fh.hora());
                horaOrdenFacadeLocal.create(horaOrdenObj);
                codigoRastreo = codigoRastreo + objetoOrden.getIdorden().toString();
                boolean respuesta = ordenFacadeLocal.actualizarOrden(objetoOrden.getIdorden(), codigoRastreo);

                if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Cliente")) {
                    emailRequest.codigoOrdenMail(userSession.getUsuarioLogin().getEmail(), nombres, codigoRastreo);
                }

                registrarCarrito();
                if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Cliente")) {
                    redir.redireccionar("/ventas/checkout_complete.xhtml");
                } else {
                    mensajes = "swal('Bien Hecho!', 'La orden se ha registrado correctamente', 'success')";
                }

                if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Cliente")) {
                    objDomicilio.setFechaDomicilio(fh.fecha());
                    objDomicilio.setNombreCliente(objetoOrden.getNombres());
                    objDomicilio.setDireccionDomicilio(objetoOrden.getDireccion());
                    objDomicilio.setBarrioDomicilio(objetoOrden.getBarrio());
                    objDomicilio.setTelefonoDomicilio(objetoOrden.getTelefono());
                    objDomicilio.setOrdenIdorden(objetoOrden);
                    EstadoDomicilio ed = new EstadoDomicilio();
                    ed.setIdestadoDomicilio(1);
                    objDomicilio.setEstadoDomicilioIdestadoDomicilio(ed);
                    domicilioFacadeLocal.create(objDomicilio);
                }
            } else {
                mensajes = "swal('Error!', 'Debes seleccionar al menos un producto', 'error')";
            }
        } catch (Exception e) {
            mensajes = "swal('Error!', 'No se ha podido registrar la orden', 'error')";
            System.out.println("edu.app.controlador.VentasSession.crearOrden()" + e.getMessage());
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void restarProductos() {
        int restaProducto = 0;
        if (carritoMesero.size() > 0) {
            productSession.setArticulos(carritoMesero);
        }
        for (int i = 0; i < listaProductos.size(); i++) {
            for (int j = 0; j < productSession.getArticulos().size(); j++) {
                if (listaProductos.get(i).getIdproducto() == productSession.getArticulos().get(j).getIdProducto()) {
                    restaProducto = listaProductos.get(i).getCantidad() - productSession.getArticulos().get(j).getCantidad();
                    if (restaProducto >= 0) {
                        productoObj = productoFacadeLocal.find(listaProductos.get(i).getIdproducto());
                        productoObj.setCantidad(restaProducto);
                        productoFacadeLocal.edit(productoObj);
                    } else {
                        mensajes = "swal('Error!', 'No se ha podido registrar la orden', 'error')";
                        break;
                    }
                }
            }
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void registrarCarrito() {

        try {
            if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Cliente")) {
                for (int i = 0; i < productSession.getArticulos().size(); i++) {
                    objetoCarrito.setOrdenIdorden(objetoOrden);
                    productoObj = productoFacadeLocal.find(productSession.getArticulos().get(i).getIdProducto());
                    objetoCarrito.setProductoIdproducto(productoObj);
                    objetoCarrito.setCantidad(productSession.getArticulos().get(i).getCantidad());
                    objetoCarrito.setFecha(fh.fecha());
                    objetoCarrito.setHora(fh.hora());
                    objetoCarrito.setPrecioUnit(productSession.getArticulos().get(i).getPrecio());
                    objetoCarrito.setEstadoProducto("no preparado");
                    carritoFacadeLocal.create(objetoCarrito);
                }
                productSession.getArticulos().clear();
                productSession.setSubtotal(0);
                productSession.setContarProductosCarrito(0);
            } else {
                for (int i = 0; i < carritoMesero.size(); i++) {
                    objetoCarrito.setOrdenIdorden(objetoOrden);
                    productoObj = productoFacadeLocal.find(carritoMesero.get(i).getIdProducto());
                    objetoCarrito.setProductoIdproducto(productoObj);
                    objetoCarrito.setCantidad(carritoMesero.get(i).getCantidad());
                    objetoCarrito.setFecha(fh.fecha());
                    objetoCarrito.setHora(fh.hora());
                    objetoCarrito.setPrecioUnit(carritoMesero.get(i).getPrecio());
                    objetoCarrito.setEstadoProducto("no preparado");
                    carritoFacadeLocal.create(objetoCarrito);
                }
                carritoMesero.clear();
                productSession.setSubtotal(0);
                productSession.setContarProductosCarrito(0);
            }

        } catch (Exception e) {
            System.out.println("edu.app.controlador.VentasSession.registrarCarrito()" + e.getMessage());
        }

    }

    public void seleccionarOrden(int idOrden, int idUsuario) {
        listaOrdenesUsuario.clear();
        selectorOrden = ordenFacadeLocal.find(idOrden);
        objOrdenUsuario = usuarioFacadeLocal.find(idUsuario);
        listaOrdenesUsuario.addAll(ordenFacadeLocal.listarOrdenUsuario(objOrdenUsuario.getIdusuario()));
        listaHoraOrden = horaOrdenFacadeLocal.horaEstadoOrden(selectorOrden.getIdorden());
        userSession.setPerfilUsuario(usuarioFacadeLocal.find(objOrdenUsuario.getIdusuario()));
        orden = selectorOrden;
        objDirPrincipal = direccionFacadeLocal.listarDirPrincipal(idUsuario, "principal");
        objTelPrincipal = telefonoFacadeLocal.listarTelPrincipal(idUsuario, "principal");
        if (null != userSession.getUsuarioLogin().getRolIdrol().getIdrol()) {
            switch (userSession.getUsuarioLogin().getRolIdrol().getIdrol()) {
                case 5:
                    redir.redireccionar("/admin-ultimate/ordenes/orden-detail-admin.xhtml");
                    break;
                case 3:
                    redir.redireccionar("/admin-ultimate/cajero/orden-detail.xhtml");
                    break;
                case 6:
                    redir.redireccionar("/admin-ultimate/parrilla/orden-detail.xhtml");
                    break;
                case 7:
                    redir.redireccionar("/admin-ultimate/cocina/orden-detail.xhtml");
                    break;
                default:
                    break;
            }
        }

    }

    public void seleccionarCarrito(int idCarrito) {
        selectorCarrito = carritoFacadeLocal.find(idCarrito);
        productoObj = selectorCarrito.getProductoIdproducto();
        cantidadActualProductoOrden = selectorCarrito.getCantidad();
        orden = ordenFacadeLocal.find(selectorCarrito.getOrdenIdorden().getIdorden());

        if (userSession.getUsuarioLogin().getRolIdrol().getIdrol() == 5) {
            redir.redireccionar("/admin-ultimate/ordenes/orden-detail-admin-editar.xhtml");
        } else if (userSession.getUsuarioLogin().getRolIdrol().getIdrol() == 3) {
            redir.redireccionar("/admin-ultimate/cajero/orden-detail-editar.xhtml");
        } else if (userSession.getUsuarioLogin().getRolIdrol().getIdrol() == 2) {
            redir.redireccionar("/admin-ultimate/mesero/content-mesero-editar.xhtml");
        }

    }

    public void editarCantidadProducto() {
        subTotal = 0;
        if (cantidadActualProductoOrden < selectorCarrito.getCantidad()) {
            productoObj.setCantidad(productoObj.getCantidad() - (selectorCarrito.getCantidad() - cantidadActualProductoOrden));
        } else {
            productoObj.setCantidad(productoObj.getCantidad() + (cantidadActualProductoOrden - selectorCarrito.getCantidad()));
        }
        productoFacadeLocal.edit(productoObj);
        cantidadActualProductoOrden = selectorCarrito.getCantidad();
        selectorCarrito.setCantidad(cantidadActualProductoOrden);
        carritoFacadeLocal.edit(selectorCarrito);
        recalcularTotal();
        if (userSession.getUsuarioLogin().getRolIdrol().getIdrol() == 2) {
            meseroSession.seleccionarMesa(meseroSession.getSelectorMesa().getIdmesa());
        }
    }

    public void eliminarProductoOrden() {

        productoObj.setCantidad(productoObj.getCantidad() + cantidadActualProductoOrden);
        productoFacadeLocal.edit(productoObj);
        carritoFacadeLocal.remove(selectorCarrito);
        recalcularTotal();

        if (userSession.getUsuarioLogin().getRolIdrol().getIdrol() == 5) {
            redir.redireccionar("/admin-ultimate/ordenes/orden-detail-admin.xhtml");
        } else if (userSession.getUsuarioLogin().getRolIdrol().getIdrol() == 3) {
            redir.redireccionar("/admin-ultimate/cajero/orden-detail.xhtml");
        } else if (userSession.getUsuarioLogin().getRolIdrol().getIdrol() == 2) {
            meseroSession.seleccionarMesa(meseroSession.getSelectorMesa().getIdmesa());
        }
    }

    public void recalcularTotal() {
        subTotal = 0;
        for (int i = 0; i < listarProductosCarrito().size(); i++) {
            subTotal += listarProductosCarrito().get(i).getCantidad() * listarProductosCarrito().get(i).getPrecioUnit();
        }
        if (userSession.getUsuarioLogin().getRolIdrol().getIdrol() == 2) {
            total = subTotal;
        } else {
            total = valorEnvioObj.getValorEnvio() + subTotal;
        }

        orden = ordenFacadeLocal.find(selectorCarrito.getOrdenIdorden().getIdorden());
        orden.setSubtotal(subTotal);
        orden.setTotal(total);
        ordenFacadeLocal.edit(orden);

        if (userSession.getUsuarioLogin().getRolIdrol().getIdrol() == 5) {
            redir.redireccionar("/admin-ultimate/ordenes/orden-detail-admin.xhtml");
        } else if (userSession.getUsuarioLogin().getRolIdrol().getIdrol() == 3) {
            redir.redireccionar("/admin-ultimate/cajero/orden-detail.xhtml");
        }
    }

    public void completarOrden() {
        try {
            boolean respuesta = ordenFacadeLocal.cambiarEstadoOrden(2, selectorOrden.getIdorden());
            estadoOrdenObj.setIdestadoOrden(2);

            horaOrdenCompletada.setOrdenIdorden(selectorOrden);
            horaOrdenCompletada.setEstadoOrdenIdestadoOrden(estadoOrdenObj);
            horaOrdenCompletada.setFecha(fh.fecha());
            horaOrdenCompletada.setHora(fh.hora());
            horaOrdenFacadeLocal.create(horaOrdenCompletada);

            if (userSession.getUsuarioLogin().getRolIdrol().getIdrol() == 5) {
                redir.redireccionar("/admin-ultimate/ordenes.xhtml");
            } else if (userSession.getUsuarioLogin().getRolIdrol().getIdrol() == 3) {
                redir.redireccionar("/admin-ultimate/cajero/pendientes.xhtml");
            }

        } catch (Exception e) {
            mensajes = "swal('Error!', 'No se ha podido realizar la operación', 'error')";
            System.out.println("edu.app.controlador.VentasSession.completarOrden()" + e.getMessage());
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void pagarOrden() {
        try {
            if (selectorOrden.getEstadoOrdenIdestadoOrden().getIdestadoOrden() == 5) {
                estadoOrdenEstado.setIdestadoOrden(2);
                if (selectorOrden.getNumeroMesa() != 0) {
                    selectorOrden.setEstadoOrdenIdestadoOrden(estadoOrdenEstado);
                    List<Mesa> m = mesaFacadeLocal.buscarIdMesa("Mesa " + selectorOrden.getNumeroMesa());
                    cambiarEstadoMesa = m.get(0);
                    cambiarEstadoMesa.setEstadoMesa("disponible");
                    mesaFacadeLocal.edit(cambiarEstadoMesa);
                }
            }
            selectorOrden.setPago("OK");
            ordenFacadeLocal.edit(selectorOrden);
            if (selectorOrden.getEstadoOrdenIdestadoOrden().getNombreEstadoOrden().equals("Preparada") || selectorOrden.getEstadoOrdenIdestadoOrden().getNombreEstadoOrden().equals("Enviada")) {
                completarOrden();
            }
            if (selectorOrden.getEstadoOrdenIdestadoOrden().getIdestadoOrden() == 6) {
                completarOrden();
            }
        } catch (Exception e) {
            System.out.println("edu.app.controlador.VentasSession.pagarOrden()" + e.getMessage());
        }
    }

    public List<Articulo> mostrarCarritoMesero() {
        return carritoMesero;
    }

    public void agregarProductosCarritoMesero(int idProducto, int cantidad, String nombre, double precio, String imagen, String tamanio, int cantidadTotal) {
        boolean flag = false;
        subTotal = 0;
        productSession.setSubtotal(0.0);
        if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Mesero")) {
            selectorOrden = meseroSession.getOrdenMesa();
        }

        if (carritoMesero.size() > 0) {
            for (Articulo carMes : carritoMesero) {
                if (idProducto == carMes.getIdProducto()) {
                    carMes.setCantidad(carMes.getCantidad() + cantidad);
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) {
            carritoMesero.add(new Articulo(idProducto, cantidad, nombre, precio, imagen, tamanio, cantidadTotal));
        }

        for (int i = 0; i < carritoMesero.size(); i++) {
            subTotal += carritoMesero.get(i).getCantidad() * carritoMesero.get(i).getPrecio();
            productSession.setSubtotal(subTotal);
        }
    }

    public void borrarProductoCarritoMesero(Articulo a) {
        subTotal -= (a.getPrecio() * a.getCantidad());
        carritoMesero.remove(a);
    }

    public void agregarProductosMesero() {
        try {

            mensajes = "";
            boolean flag = false;
            Carrito car = new Carrito();
            Producto p = new Producto();
            if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Mesero")) {
                selectorOrden = meseroSession.getOrdenMesa();
            }

            for (int i = 0; i < carritoMesero.size(); i++) {
                objProducto = productoFacadeLocal.find(carritoMesero.get(i).getIdProducto());
                objProducto.setCantidad(objProducto.getCantidad() - carritoMesero.get(i).getCantidad());
                productoFacadeLocal.edit(objProducto);
            }
            if (selectorOrden.getEstadoOrdenIdestadoOrden().getNombreEstadoOrden().equals("Preparada")) {
                for (int i = 0; i < carritoMesero.size(); i++) {
                    p = productoFacadeLocal.find(carritoMesero.get(i).getIdProducto());
                    car.setProductoIdproducto(p);
                    car.setOrdenIdorden(selectorOrden);
                    car.setPrecioUnit(carritoMesero.get(i).getPrecio());
                    car.setCantidad(carritoMesero.get(i).getCantidad());
                    car.setEstadoProducto("no preparado");
                    car.setFecha(fh.fecha());
                    car.setHora(fh.hora());
                    estadoOrdenEstado.setIdestadoOrden(1);
                    selectorOrden.setEstadoOrdenIdestadoOrden(estadoOrdenEstado);
                    carritoFacadeLocal.create(car);
                    ordenFacadeLocal.edit(selectorOrden);
                }

            } else if (carritoMesero.size() > 0) {

                for (int i = 0; i < carritoMesero.size(); i++) {
                    for (int j = 0; j < detallesOrden().size(); j++) {
                        if (detallesOrden().get(j).getProductoIdproducto().getIdproducto() == carritoMesero.get(i).getIdProducto()) {
                            car = carritoFacadeLocal.find(detallesOrden().get(j).getIdcarrito());

                            if (car.getEstadoProducto().equals("no preparado")) {
                                car.setCantidad(carritoMesero.get(i).getCantidad() + detallesOrden().get(j).getCantidad());
                                carritoFacadeLocal.edit(car);
                                flag = true;
                                break;
                            } else {
                                p = productoFacadeLocal.find(carritoMesero.get(i).getIdProducto());
                                car.setProductoIdproducto(p);
                                car.setOrdenIdorden(selectorOrden);
                                car.setPrecioUnit(carritoMesero.get(i).getPrecio());
                                car.setCantidad(carritoMesero.get(i).getCantidad());
                                car.setEstadoProducto("no preparado");
                                car.setFecha(fh.fecha());
                                car.setHora(fh.hora());
                                carritoFacadeLocal.create(car);
                                flag = true;
                                break;
                            }
                        }
                    }

                    if (!flag) {
                        p = productoFacadeLocal.find(carritoMesero.get(i).getIdProducto());
                        car.setProductoIdproducto(p);
                        car.setOrdenIdorden(selectorOrden);
                        car.setPrecioUnit(carritoMesero.get(i).getPrecio());
                        car.setCantidad(carritoMesero.get(i).getCantidad());
                        car.setEstadoProducto("no preparado");
                        car.setFecha(fh.fecha());
                        car.setHora(fh.hora());
                        carritoFacadeLocal.create(car);
                    }

                    flag = false;
                }
            } else {
                mensajes = "swal('Error!', 'Debe agregar productos para ejecutar esta acción', 'error')";
            }

            carritoMesero.clear();

            if (mensajes.equals("")) {
                subTotal = 0;
                for (int i = 0; i < detallesOrden().size(); i++) {
                    subTotal += detallesOrden().get(i).getCantidad() * detallesOrden().get(i).getPrecioUnit();
                }
                if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Mesero")) {
                    total = subTotal;
                } else {
                    total = subTotal + valorEnvioObj.getValorEnvio();
                }
                orden = ordenFacadeLocal.find(selectorOrden.getIdorden());
                orden.setSubtotal(subTotal);
                orden.setTotal(total);
                ordenFacadeLocal.edit(orden);
                if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Mesero")) {
                    meseroSession.seleccionarMesa(meseroSession.getSelectorMesa().getIdmesa());
                } else if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Cajero")) {
                    redir.redireccionar("/admin-ultimate/cajero/orden-detail.xhtml");
                }

            }

        } catch (Exception e) {
            mensajes = "swal('Error!', 'No se ha podido ejecutar la operación', 'error')";
            System.out.println("edu.app.controlador.VentasSession.agregarProductosMesero()" + e.getMessage());
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void seguimientoOrden() {
        selectorOrdenUsuario = objetoOrden;
        List<Domicilio> domicilioOrden = domicilioFacadeLocal.listarDomiciliosPorOrden(selectorOrdenUsuario.getIdorden());
        objDomicilio = domicilioOrden.get(0);
        redir.redireccionar("/ventas/checkout_tracking.xhtml");
    }

    public void rastrearOrden() {
        int idOrden = selectorOrdenUsuario.getIdorden();
        selectorOrdenUsuario = ordenFacadeLocal.find(idOrden);
        List<Domicilio> domicilioOrden = domicilioFacadeLocal.listarDomiciliosPorOrden(selectorOrdenUsuario.getIdorden());
        objDomicilio = domicilioOrden.get(0);
        redir.redireccionar("/ventas/checkout_tracking.xhtml");
    }

    public void seleccionarOrdenUsuario(int idOrden) {
        selectorOrdenUsuario = ordenFacadeLocal.find(idOrden);
        redir.redireccionar("/usuarios/client/perfil-ordenes-detalle.xhtml");
    }

    public List<Carrito> listarProductosCarritoUsuario() {
        return carritoFacadeLocal.productosOrden(selectorOrdenUsuario.getIdorden());
    }

    public List<Carrito> listarProductosCarrito() {
        return carritoFacadeLocal.productosOrden(selectorCarrito.getOrdenIdorden().getIdorden());
    }

    public List<Carrito> detallesOrden() {
        return carritoFacadeLocal.productosOrden(selectorOrden.getIdorden());
    }

    public List<HoraOrden> horaEstadoOrden() {
        return horaOrdenFacadeLocal.horaEstadoOrden(selectorOrden.getIdorden());
    }

    public List<HoraOrden> horaEstadoOrdenCliente() {
        return horaOrdenFacadeLocal.horaEstadoOrden(selectorOrdenUsuario.getIdorden());
    }

    public List<Orden> ordenesPendientes() {
        return ordenFacadeLocal.listaOrdenesPendientes();
    }

    public List<Orden> ordenesPreparacion() {
        return ordenFacadeLocal.listaOrdenesPreparacion();
    }

    public List<Orden> ordenesPreparadas() {
        return ordenFacadeLocal.listaOrdenesPreparadas();
    }

    public List<Orden> ordenesEnviando() {
        return ordenFacadeLocal.listaOrdenesEnviando();
    }

    public List<Orden> ordenesEntregadas() {
        return ordenFacadeLocal.listaOrdenesEntregadas();
    }

    public List<Orden> ordenesCompletadas() {
        return ordenFacadeLocal.listaOrdenesCompletadas();
    }

    public List<Orden> ordenesCompletadasFecha() {
        return ordenFacadeLocal.listarOrdenesPendientesFecha(fechaIni, fechaFin);
    }

    public List<Orden> ordenesTodas() {
        return ordenFacadeLocal.listarTodasOrdenes();
    }

    public void seleccionCategoria(int idCat) {
        objCategoria = categoriaFacadeLocal.find(idCat);
    }

    public List<Producto> listarporCategoria() {
        return productoFacadeLocal.listaPorCategoria(objCategoria.getIdcategoria());
    }

    public void prepararProducto(int idCarrito) {
        try {
            cambiarEstado = carritoFacadeLocal.find(idCarrito);
            cambiarEstado.setEstadoProducto("preparando");
            carritoFacadeLocal.edit(cambiarEstado);
            if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Parrilla")) {
                productosPendientesParrilla.clear();
                productosPendientesParrilla.addAll(carritoFacadeLocal.ordenesParrillaCocina("Parrilla", "no preparado"));
            } else if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Cocina")) {
                productosPendientesCocina.clear();
                productosPendientesCocina.addAll(carritoFacadeLocal.ordenesParrillaCocina("Cocina", "no preparado"));
            }

            if (cambiarEstado.getCantidad() == 1) {
                mensajes = "swal('Bien Hecho!', 'Se ha agregado " + cambiarEstado.getCantidad() + " " + cambiarEstado.getProductoIdproducto().getNombreProducto() + " a preparación', 'success')";
            } else {
                mensajes = "swal('Bien Hecho!', 'Se han agregado " + cambiarEstado.getCantidad() + " " + cambiarEstado.getProductoIdproducto().getNombreProducto() + " a preparación', 'success')";
            }

        } catch (Exception e) {
            System.out.println("edu.app.controlador.VentasSession.prepararProducto()" + e.getMessage());
            mensajes = "swal('Error!', 'No se ha podido realizar la acción', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void listarProductosPendientes() {
        try {
            int i = 0;
            if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Parrilla")) {
                productosPendientesParrilla.clear();
                productosPendientesParrilla.addAll(carritoFacadeLocal.ordenesParrillaCocina("Parrilla", "no preparado"));
                redir.redireccionar("/admin-ultimate/parrilla/productos-pendientes.xhtml");
            } else if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Cocina")) {
                productosPendientesCocina.clear();
                productosPendientesCocina.addAll(carritoFacadeLocal.ordenesParrillaCocina("Cocina", "no preparado"));
                redir.redireccionar("/admin-ultimate/cocina/productos-pendientes.xhtml");
            }

        } catch (Exception e) {
            System.out.println("edu.app.controlador.VentasSession.listarProductosPendientes()" + e.getMessage());
        }
    }

    public void listarProductosPreparacion() {
        try {
            int i = 0;
            if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Parrilla")) {
                productosPreparacionParrilla.clear();
                productosPreparacionParrilla.addAll(carritoFacadeLocal.ordenesParrillaCocina("Parrilla", "preparando"));
                redir.redireccionar("/admin-ultimate/parrilla/productos-preparando.xhtml");
            } else if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Cocina")) {
                productosPreparacionCocina.clear();
                productosPreparacionCocina.addAll(carritoFacadeLocal.ordenesParrillaCocina("Cocina", "preparando"));
                redir.redireccionar("/admin-ultimate/cocina/productos-preparando.xhtml");
            }
        } catch (Exception e) {
            System.out.println("edu.app.controlador.VentasSession.listarProductosPreparacion()" + e.getMessage());
        }
    }

    public void listarProductosCompletados() {
        try {
            if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Parrilla")) {
                productosCompletadosParrilla.clear();
                productosCompletadosParrilla.addAll(carritoFacadeLocal.ordenesParrillaCocinaDia("Parrilla", "completado", fh.fecha()));
                redir.redireccionar("/admin-ultimate/parrilla/productos-completados.xhtml");
            } else if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Cocina")) {
                productosCompletadosCocina.clear();
                productosCompletadosCocina.addAll(carritoFacadeLocal.ordenesParrillaCocinaDia("Cocina", "completado", fh.fecha()));
                redir.redireccionar("/admin-ultimate/cocina/productos-completados.xhtml");
            }
        } catch (Exception e) {
            System.out.println("edu.app.controlador.VentasSession.listarProductosCompletados()" + e.getMessage());
        }
    }

    public void completarProducto(int idCarrito) {
        try {
            boolean pendientes = false;
            cambiarEstado = carritoFacadeLocal.find(idCarrito);
            cambiarEstado.setEstadoProducto("completado");
            carritoFacadeLocal.edit(cambiarEstado);
            if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Parrilla")) {
                productosPreparacionParrilla.clear();
                productosPreparacionParrilla.addAll(carritoFacadeLocal.ordenesParrillaCocina("Parrilla", "preparando"));
            } else if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Cocina")) {
                productosPreparacionCocina.clear();
                productosPreparacionCocina.addAll(carritoFacadeLocal.ordenesParrillaCocina("Cocina", "preparando"));
            }

            ArrayList<Carrito> c = new ArrayList<>();
            c.addAll(carritoFacadeLocal.productosOrden(cambiarEstado.getOrdenIdorden().getIdorden()));
            Orden o = ordenFacadeLocal.find(cambiarEstado.getOrdenIdorden().getIdorden());

            for (int i = 0; i < c.size(); i++) {
                if (!c.get(i).getEstadoProducto().equals("completado")) {
                    pendientes = true;
                    break;
                }
            }
            if (!pendientes) {
                EstadoOrden eo = new EstadoOrden();
                eo.setIdestadoOrden(5);
                if (o.getPago().equals("OK")) {
                    eo.setIdestadoOrden(2);
                }
                o.setEstadoOrdenIdestadoOrden(eo);
                ordenFacadeLocal.edit(o);
                horaOrdenObj.setEstadoOrdenIdestadoOrden(eo);
                horaOrdenObj.setOrdenIdorden(o);
                horaOrdenObj.setFecha(fh.fecha());
                horaOrdenObj.setHora(fh.hora());
                horaOrdenFacadeLocal.create(horaOrdenObj);
                if (o.getNumeroMesa() != 0) {
                    List<Mesa> m = mesaFacadeLocal.buscarIdMesa("Mesa " + selectorOrden.getNumeroMesa());
                    cambiarEstadoMesa = m.get(0);
                    cambiarEstadoMesa.setEstadoMesa("disponible");
                    mesaFacadeLocal.edit(cambiarEstadoMesa);
                }
            }

            if (cambiarEstado.getCantidad() == 1) {
                mensajes = "swal('Bien Hecho!', 'Se ha cambiado " + cambiarEstado.getCantidad() + " " + cambiarEstado.getProductoIdproducto().getNombreProducto() + " a completado', 'success')";
            } else {
                mensajes = "swal('Bien Hecho!', 'Se han cambiado " + cambiarEstado.getCantidad() + " " + cambiarEstado.getProductoIdproducto().getNombreProducto() + " a completado', 'success')";
            }
        } catch (Exception e) {
            System.out.println("edu.app.controlador.VentasSession.completarProducto()" + e.getMessage());
            mensajes = "swal('Error!', 'No se ha podido realizar la acción', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void preparaOrden(int idOrden) {
        try {
            cambiarEstadoOrden = selectorOrden;
            estadoOrdenEstado.setIdestadoOrden(3);
            cambiarEstadoOrden.setEstadoOrdenIdestadoOrden(estadoOrdenEstado);
            ordenFacadeLocal.edit(cambiarEstadoOrden);
            horaOrdenObj.setEstadoOrdenIdestadoOrden(estadoOrdenEstado);
            horaOrdenObj.setOrdenIdorden(cambiarEstadoOrden);
            horaOrdenObj.setFecha(fh.fecha());
            horaOrdenObj.setHora(fh.hora());
            horaOrdenFacadeLocal.create(horaOrdenObj);
            if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Parrilla")) {
                redir.redireccionar("/admin-ultimate/parrilla/preparacion.xhtml");
            } else if (userSession.getUsuarioLogin().getRolIdrol().getNombreRol().equals("Cocina")) {
                redir.redireccionar("/admin-ultimate/cocina/preparacion.xhtml");
            }
        } catch (Exception e) {
            System.out.println("edu.app.controlador.VentasSession.preparaOrden()" + e.getMessage());
        }

    }

    public void filtrarOrdenesCompletadasFecha() {
        int i = 0;
        if (!rangoFechas.equals("")) {
            if (rangoFechas.substring(0, 10).equals(rangoFechas.substring(13, 23))) {
                lblRangoFechas = fh.filtrarFechaInicio(rangoFechas);
            } else {
                lblRangoFechas = fh.filtrarFechaInicio(rangoFechas) + " - " + fh.filtrarFechaFinal(rangoFechas);
            }
            fechaIni = fh.filtrarFechaInicio(rangoFechas);
            fechaFin = fh.filtrarFechaFinal(rangoFechas);
            ordenesCompletadasFecha();
        }
    }

    public void eliminarOrden(int idOrden) {
        try {
            List<Domicilio> listaDomi = domicilioFacadeLocal.listarDomiciliosPorOrden(idOrden);
            Orden o = ordenFacadeLocal.find(idOrden);
            if (listaDomi.size() > 0) {
                for (int i = 0; i < listaDomi.size(); i++) {
                    Domicilio objDomi = domicilioFacadeLocal.find(listaDomi.get(i).getIddomicilio());
                    domicilioFacadeLocal.remove(objDomi);
                }
            }
            boolean eliminarProductos = carritoFacadeLocal.eliminarProductosOrden(idOrden);
            ordenFacadeLocal.remove(o);
            mensajes = "swal('Bien Hecho!', 'Se ha eliminado la orden correctamente', 'success')";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.VentasSession.eliminarOrden()" + e.getMessage());
            mensajes = "swal('Error!', 'No se ha podido realizar la acción', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

//    GETTER
//            
//       &
//    
//    SETTER
    public Producto getProductoObj() {
        return productoObj;
    }

    public void setProductoObj(Producto productoObj) {
        this.productoObj = productoObj;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(int estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public String getCodigoOrden() {
        return codigoOrden;
    }

    public void setCodigoOrden(String codigoOrden) {
        this.codigoOrden = codigoOrden;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Direccion getObjetoDir() {
        return objetoDir;
    }

    public void setObjetoDir(Direccion objetoDir) {
        this.objetoDir = objetoDir;
    }

    public double getPaga_con() {
        return paga_con;
    }

    public void setPaga_con(double paga_con) {
        this.paga_con = paga_con;
    }

    public ValorEnvio getValorEnvioObj() {
        return valorEnvioObj;
    }

    public void setValorEnvioObj(ValorEnvio valorEnvioObj) {
        this.valorEnvioObj = valorEnvioObj;
    }

    public double getValorEnvio() {
        return valorEnvio;
    }

    public void setValorEnvio(double valorEnvio) {
        this.valorEnvio = valorEnvio;
    }

    public Orden getObjetoOrden() {
        return objetoOrden;
    }

    public void setObjetoOrden(Orden objetoOrden) {
        this.objetoOrden = objetoOrden;
    }

    public GeneradorCodigos getGeneradorCodigos() {
        return generadorCodigos;
    }

    public void setGeneradorCodigos(GeneradorCodigos generadorCodigos) {
        this.generadorCodigos = generadorCodigos;
    }

    public EstadoOrden getEstadoOrdenObj() {
        return estadoOrdenObj;
    }

    public void setEstadoOrdenObj(EstadoOrden estadoOrdenObj) {
        this.estadoOrdenObj = estadoOrdenObj;
    }

    public Direccion getObjetoDireccion() {
        return objetoDireccion;
    }

    public void setObjetoDireccion(Direccion objetoDireccion) {
        this.objetoDireccion = objetoDireccion;
    }

    public String getCodigoRastreo() {
        return codigoRastreo;
    }

    public void setCodigoRastreo(String codigoRastreo) {
        this.codigoRastreo = codigoRastreo;
    }

    public Carrito getObjetoCarrito() {
        return objetoCarrito;
    }

    public void setObjetoCarrito(Carrito objetoCarrito) {
        this.objetoCarrito = objetoCarrito;
    }

    public ArrayList<Orden> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaOrdenes(ArrayList<Orden> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }

    public ArrayList<Orden> getListaOrdenesPendientes() {
        return listaOrdenesPendientes;
    }

    public void setListaOrdenesPendientes(ArrayList<Orden> listaOrdenesPendientes) {
        this.listaOrdenesPendientes = listaOrdenesPendientes;
    }

    public ArrayList<Orden> getListaOrdenesCompletadas() {
        return listaOrdenesCompletadas;
    }

    public void setListaOrdenesCompletadas(ArrayList<Orden> listaOrdenesCompletadas) {
        this.listaOrdenesCompletadas = listaOrdenesCompletadas;
    }

    public HoraOrden getHoraOrdenObj() {
        return horaOrdenObj;
    }

    public void setHoraOrdenObj(HoraOrden horaOrdenObj) {
        this.horaOrdenObj = horaOrdenObj;
    }

    public Orden getSelectorOrden() {
        return selectorOrden;
    }

    public void setSelectorOrden(Orden selectorOrden) {
        this.selectorOrden = selectorOrden;
    }

    public Usuario getObjOrdenUsuario() {
        return objOrdenUsuario;
    }

    public void setObjOrdenUsuario(Usuario objOrdenUsuario) {
        this.objOrdenUsuario = objOrdenUsuario;
    }

    public ArrayList<Orden> getListaOrdenesUsuario() {
        return listaOrdenesUsuario;
    }

    public void setListaOrdenesUsuario(ArrayList<Orden> listaOrdenesUsuario) {
        this.listaOrdenesUsuario = listaOrdenesUsuario;
    }

    public List<HoraOrden> getListaHoraOrden() {
        return listaHoraOrden;
    }

    public void setListaHoraOrden(List<HoraOrden> listaHoraOrden) {
        this.listaHoraOrden = listaHoraOrden;
    }

    public ArrayList<Orden> getListaOrdenesPreparacion() {
        return listaOrdenesPreparacion;
    }

    public void setListaOrdenesPreparacion(ArrayList<Orden> listaOrdenesPreparacion) {
        this.listaOrdenesPreparacion = listaOrdenesPreparacion;
    }

    public ArrayList<Orden> getListaOrdenesEnviando() {
        return listaOrdenesEnviando;
    }

    public void setListaOrdenesEnviando(ArrayList<Orden> listaOrdenesEnviando) {
        this.listaOrdenesEnviando = listaOrdenesEnviando;
    }

    public Carrito getSelectorCarrito() {
        return selectorCarrito;
    }

    public void setSelectorCarrito(Carrito selectorCarrito) {
        this.selectorCarrito = selectorCarrito;
    }

    public int getCantidadActualProductoOrden() {
        return cantidadActualProductoOrden;
    }

    public void setCantidadActualProductoOrden(int cantidadActualProductoOrden) {
        this.cantidadActualProductoOrden = cantidadActualProductoOrden;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public HoraOrden getHoraOrdenCompletada() {
        return horaOrdenCompletada;
    }

    public void setHoraOrdenCompletada(HoraOrden horaOrdenCompletada) {
        this.horaOrdenCompletada = horaOrdenCompletada;
    }

    public Orden getSelectorOrdenUsuario() {
        return selectorOrdenUsuario;
    }

    public void setSelectorOrdenUsuario(Orden selectorOrdenUsuario) {
        this.selectorOrdenUsuario = selectorOrdenUsuario;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria getObjCategoria() {
        return objCategoria;
    }

    public void setObjCategoria(Categoria objCategoria) {
        this.objCategoria = objCategoria;
    }

    public ArrayList<Articulo> getCarritoMesero() {
        return carritoMesero;
    }

    public void setCarritoMesero(ArrayList<Articulo> carritoMesero) {
        this.carritoMesero = carritoMesero;
    }

    public Producto getObjProducto() {
        return objProducto;
    }

    public void setObjProducto(Producto objProducto) {
        this.objProducto = objProducto;
    }

    public Direccion getObjDirPrincipal() {
        return objDirPrincipal;
    }

    public void setObjDirPrincipal(Direccion objDirPrincipal) {
        this.objDirPrincipal = objDirPrincipal;
    }

    public Telefono getObjTelPrincipal() {
        return objTelPrincipal;
    }

    public void setObjTelPrincipal(Telefono objTelPrincipal) {
        this.objTelPrincipal = objTelPrincipal;
    }

    public ArrayList<Carrito> getProductosPendientesParrilla() {
        return productosPendientesParrilla;
    }

    public void setProductosPendientesParrilla(ArrayList<Carrito> productosPendientesParrilla) {
        this.productosPendientesParrilla = productosPendientesParrilla;
    }

    public Carrito getCambiarEstado() {
        return cambiarEstado;
    }

    public void setCambiarEstado(Carrito cambiarEstado) {
        this.cambiarEstado = cambiarEstado;
    }

    public ArrayList<Carrito> getProductosPreparacionParrilla() {
        return productosPreparacionParrilla;
    }

    public void setProductosPreparacionParrilla(ArrayList<Carrito> productosPreparacionParrilla) {
        this.productosPreparacionParrilla = productosPreparacionParrilla;
    }

    public ArrayList<Carrito> getProductosCompletadosParrilla() {
        return productosCompletadosParrilla;
    }

    public void setProductosCompletadosParrilla(ArrayList<Carrito> productosCompletadosParrilla) {
        this.productosCompletadosParrilla = productosCompletadosParrilla;
    }

    public Orden getCambiarEstadoOrden() {
        return cambiarEstadoOrden;
    }

    public void setCambiarEstadoOrden(Orden cambiarEstadoOrden) {
        this.cambiarEstadoOrden = cambiarEstadoOrden;
    }

    public ArrayList<Carrito> getProductosPendientesCocina() {
        return productosPendientesCocina;
    }

    public void setProductosPendientesCocina(ArrayList<Carrito> productosPendientesCocina) {
        this.productosPendientesCocina = productosPendientesCocina;
    }

    public ArrayList<Carrito> getProductosPreparacionCocina() {
        return productosPreparacionCocina;
    }

    public void setProductosPreparacionCocina(ArrayList<Carrito> productosPreparacionCocina) {
        this.productosPreparacionCocina = productosPreparacionCocina;
    }

    public ArrayList<Carrito> getProductosCompletadosCocina() {
        return productosCompletadosCocina;
    }

    public void setProductosCompletadosCocina(ArrayList<Carrito> productosCompletadosCocina) {
        this.productosCompletadosCocina = productosCompletadosCocina;
    }

    public String getRangoFechas() {
        return rangoFechas;
    }

    public void setRangoFechas(String rangoFechas) {
        this.rangoFechas = rangoFechas;
    }

    public String getLblRangoFechas() {
        return lblRangoFechas;
    }

    public void setLblRangoFechas(String lblRangoFechas) {
        this.lblRangoFechas = lblRangoFechas;
    }

    public Mesa getCambiarEstadoMesa() {
        return cambiarEstadoMesa;
    }

    public void setCambiarEstadoMesa(Mesa cambiarEstadoMesa) {
        this.cambiarEstadoMesa = cambiarEstadoMesa;
    }

    public Domicilio getObjDomicilio() {
        return objDomicilio;
    }

    public void setObjDomicilio(Domicilio objDomicilio) {
        this.objDomicilio = objDomicilio;
    }

}
