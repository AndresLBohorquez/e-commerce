/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.*;
import edu.app.facade.*;
import edu.app.util.Caracteres;
import edu.app.util.Redireccion;
import java.io.File;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.*;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.Part;
import org.primefaces.PrimeFaces;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author david
 */
@Named(value = "productSession")
@SessionScoped
public class ProductSession implements Serializable {

    private int cantidad;
    private String nombre_producto;
    private double precio_unitario;
    private String tamanio;
    private String descripcion;
    private int categoria_idcategoria;
    private int proveedor_idproveedor;
    private String tipoMensaje = "";
    private Part archivoImagen;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
    private String rutaImgPrincipal;
    private double subtotal;
    private double total;
    private int contarProductosCarrito;
    private String dependencia;
    private String cantidadStr;
    private String precioStr;

    String mensajes;
    /*
    FACADEs
     */
    @EJB
    ProductoFacadeLocal productoFacadeLocal;

    @EJB
    ImagenesFacadeLocal imagenesFacadeLocal;

    @EJB
    ProveedorFacadeLocal proveedorFacadeLocal;

    @EJB
    CategoriaFacadeLocal categoriaFacadeLocal;

    @EJB
    CarritoFacadeLocal carritoFacadeLocal;

    /*
    INSTANCEs
     */
    private Producto objProducto = new Producto();
    private Producto productoRegistrado = new Producto();
    private Imagenes objImagen = new Imagenes();
    private Articulo articuloSelect = new Articulo();
    Redireccion redir = new Redireccion();
    Caracteres caracteres = new Caracteres();


    /*
    Arreglos
     */
    private ArrayList<Producto> listaProducto = new ArrayList<>();
    private ArrayList<Producto> productosCarrito = new ArrayList<>();
    private ArrayList<Proveedor> listaProveedores = new ArrayList<>();
    private ArrayList<Categoria> categoriasProductos = new ArrayList<>();
    private ArrayList<Articulo> articulos = new ArrayList<>();

    private String toast = "";

    public ProductSession() {
    }

    @PostConstruct
    public void cargaInicial() {
        listaProducto.addAll(productoFacadeLocal.listarTodosProductos());
        objProducto = productoFacadeLocal.find(13);
        listaProveedores.addAll(proveedorFacadeLocal.findAll());
        categoriasProductos.addAll(categoriaFacadeLocal.findAll());

    }

    public void seleccionProducto(int idProducto) {
        objProducto = productoFacadeLocal.find(idProducto);
        cantidad = objProducto.getCantidad();
        precio_unitario = objProducto.getPrecioUnitario();

        try {
            redir.redireccionar("/admin-ultimate/productos/actualizar.xhtml");
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProductSession.seleccionProducto()" + e.getMessage());
        }
    }

    public List<Articulo> agregarProductos(int idProducto, int cantidadProducto, String nombre, double precio, String imagen, String tamanio, int cantidadTotal) {
        subtotal = 0;
        boolean flag = false;
        if (articulos.size() > 0) {

            for (Articulo articulo : articulos) {
                if (idProducto == articulo.getIdProducto()) {
                    articulo.setCantidad(articulo.getCantidad() + cantidadProducto);
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) {
            articulos.add(new Articulo(idProducto, cantidadProducto, nombre, precio, imagen, tamanio, cantidadTotal));
        }

        for (int i = 0; i < articulos.size(); i++) {
            subtotal += articulos.get(i).getCantidad() * articulos.get(i).getPrecio();
        }
        contarProductosCarrito += cantidadProducto;
        return articulos;
    }

    public void actualizarProductosCarrito() {
        subtotal = 0;
        contarProductosCarrito = 0;
        for (int i = 0; i < articulos.size(); i++) {
            subtotal += articulos.get(i).getCantidad() * articulos.get(i).getPrecio();
            contarProductosCarrito += articulos.get(i).getCantidad();
        }
    }

    public void actualizarProductosCarritoRedir() {
        subtotal = 0;
        contarProductosCarrito = 0;
        for (int i = 0; i < articulos.size(); i++) {
            subtotal += articulos.get(i).getCantidad() * articulos.get(i).getPrecio();
            contarProductosCarrito += articulos.get(i).getCantidad();
        }
        redir.redireccionar("/usuarios/client/checkout.xhtml");
    }

    public void eliminarProductoCarrito(Articulo a) {
        contarProductosCarrito -= a.getCantidad();
        articulos.remove(a);
        subtotal = 0;
        for (int i = 0; i < articulos.size(); i++) {
            subtotal += articulos.get(i).getCantidad() * articulos.get(i).getPrecio();
        }
    }

    public List<Producto> mostrarDatosProducto() {
        return productoFacadeLocal.listaPorProducto(objProducto.getIdproducto());
    }

    public List<Producto> listarDatosProducto() {
        return productoFacadeLocal.listarTodosProductos();
    }

    public void registrarProducto() {
        try {
            boolean respuesta = productoFacadeLocal.registrarProducto(cantidad, nombre_producto, precio_unitario, tamanio, categoria_idcategoria, proveedor_idproveedor, descripcion, dependencia);

            cantidad = 0;
            nombre_producto = "";
            precio_unitario = 0;
            tamanio = "";
            descripcion = "";
            categoria_idcategoria = 0;
            proveedor_idproveedor = 0;
            mensajes = "swal('Bien Hecho!', 'El producto se ha registrado correctamente!', 'success')";

        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProductSession.registrarProducto()" + e.getMessage());
            mensajes = "swal('Error!', 'No se ha podido registrar la información!', 'error')";

        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void actualizarProducto() {
        boolean flag = false;
        try {
            if (!dependencia.equals("0")) {
                if (!nombre_producto.equals("")) {
                    objProducto.setNombreProducto(nombre_producto);
                }
                if (cantidad != 0) {
                    objProducto.setCantidad(cantidad);
                }
                if (precio_unitario != 0) {
                    objProducto.setPrecioUnitario(precio_unitario);
                }
                if (!tamanio.equals("")) {
                    objProducto.setTamanio(tamanio);
                }
                if (!descripcion.equals("")) {
                    objProducto.setDescripcion(descripcion);
                }
                if (proveedor_idproveedor > 0) {
                    Proveedor provee;
                    provee = proveedorFacadeLocal.find(proveedor_idproveedor);
                    objProducto.setProveedorIdproveedor(provee);
                }
                if (categoria_idcategoria > 0) {
                    Categoria cat;
                    cat = categoriaFacadeLocal.find(categoria_idcategoria);
                    objProducto.setCategoriaIdcategoria(cat);
                }

                objProducto.setDependencia(dependencia);

                mensajes = "swal('Bien Hecho!', 'La información se actualizado correctamente!', 'success')";
                flag = true;
            } else {
                mensajes = "swal('Error!', 'Debe seleccionar una dependencia!', 'error')";
            }
            if (archivoImagen != null) {
                cargarImagen();
                flag = true;
            }

            if (flag) {
                productoFacadeLocal.edit(objProducto);
            }

        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProductSession.actualizarProducto()" + e.getMessage());
            mensajes = "swal('Error!', 'No se ha podido actualizar la información!', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void eliminarProducto(Producto producto) {
        try {
            productoFacadeLocal.remove(producto);
            listaProducto.remove(producto);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProductSession.eliminarProducto()" + e.getMessage());
        }
    }

    public void eliminarOcultarProducto(int idProducto) {
        try {
            List<Carrito> productosCarrito = carritoFacadeLocal.listaPorProductos(idProducto);
            Producto p = productoFacadeLocal.find(idProducto);
            if (productosCarrito.size() > 0) {
                short visible = 0;
                p.setVisible(visible);
                productoFacadeLocal.edit(p);
            } else {
                productoFacadeLocal.remove(p);
            }
            mensajes = "swal('Bien Hecho!', 'el producto se ha eliminado correctamente!', 'success')";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProductSession.eliminarOcultarProducto()" + e.getMessage());
            mensajes = "swal('Error!', 'No se ha podido eliminar el producto!', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public String cargarImagen() {
        mensajes = "";

        if (archivoImagen != null) {
            if (archivoImagen.getSize() > 4000000) {
                mensajes = "swal('Error!', 'El archivo es muy grande!', 'error')";
            } else if (!"image/jpeg".equals(archivoImagen.getContentType())) {
                if (!"image/png".equals(archivoImagen.getContentType())) {
                    mensajes = "swal('Error!', 'El archivo no es una imagen!', 'error')";
                }
            } else if (!"image/png".equals(archivoImagen.getContentType())) {
                if (!"image/jpeg".equals(archivoImagen.getContentType())) {
                    mensajes = "swal('Error!', 'El archivo no es una imagen!', 'error')";
                }
            }
            if (mensajes.isEmpty()) {
                File carpeta = new File("C:/LaEsquinaLlanera/imagenes/productos");
                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }
                try (InputStream is = archivoImagen.getInputStream()) {
                    Calendar hoy = Calendar.getInstance();
                    String nombreArchivo = caracteres.caracteresEspeciales(objProducto.getNombreProducto()) + sdf.format(hoy.getTime()) + ".";
                    nombreArchivo += FilenameUtils.getExtension(archivoImagen.getSubmittedFileName());
                    Files.copy(is, (new File(carpeta, nombreArchivo)).toPath(), StandardCopyOption.REPLACE_EXISTING);

                    Imagenes imgNew = new Imagenes();

                    imgNew.setRuta(nombreArchivo);
                    imagenesFacadeLocal.create(imgNew);
                    productoFacadeLocal.imagenProducto(imgNew.getRuta(), objProducto.getIdproducto());
                    mensajes = "swal('Bien Hecho!', 'La imagen se ha cargado correctamente!', 'success')";
                } catch (Exception e) {
                    mensajes = "swal('Error!', 'No se pudo guardar la imagen!', 'error')";
                    System.out.println("edu.app.controlador.ProductSession.cargarImagen()" + e.getMessage());
                }
            }

        } else {
            mensajes = "swal('Error!', 'No se encontro una imagen!', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
        return mensajes;
    }

    public void imagenPrincipal() {
        try {

            productoFacadeLocal.listarTodosProductos();
            productoFacadeLocal.imagenPrincipal(objImagen.getRuta(), objProducto.getIdproducto());
            redir.redireccionar("/admin-ultimate/productos/actualizar.xhtml");
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProductSession.imagenPrincipal()" + e.getMessage());
        }
    }

    public void seleccionarImgPrincipal(String ruta) {
        try {
            objImagen = imagenesFacadeLocal.find(ruta);
            redir.redireccionar("/admin-ultimate/productos/confirmar_imagen.xhtml");

        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProductSession.seleccionarImgPrincipal()" + e.getMessage());
        }
    }

    /*
    GETs y SETs
     */
    public ArrayList<Producto> getProductosCarrito() {
        return productosCarrito;
    }

    public void setProductosCarrito(ArrayList<Producto> productosCarrito) {
        this.productosCarrito = productosCarrito;
    }

    public Producto getObjProducto() {
        return objProducto;
    }

    public void setObjProducto(Producto objProducto) {
        this.objProducto = objProducto;
    }

    public ArrayList<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(ArrayList<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public String getToast() {
        return toast;
    }

    public void setToast(String toast) {
        this.toast = toast;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public int getCategoria_idcategoria() {
        return categoria_idcategoria;
    }

    public void setCategoria_idcategoria(int categoria_idcategoria) {
        this.categoria_idcategoria = categoria_idcategoria;
    }

    public int getProveedor_idproveedor() {
        return proveedor_idproveedor;
    }

    public void setProveedor_idproveedor(int proveedor_idproveedor) {
        this.proveedor_idproveedor = proveedor_idproveedor;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public Part getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(Part archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public ArrayList<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(ArrayList<Proveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public ArrayList<Categoria> getCategoriasProductos() {
        return categoriasProductos;
    }

    public void setCategoriasProductos(ArrayList<Categoria> categoriasProductos) {
        this.categoriasProductos = categoriasProductos;
    }

    public Producto getProductoRegistrado() {
        return productoRegistrado;
    }

    public void setProductoRegistrado(Producto productoRegistrado) {
        this.productoRegistrado = productoRegistrado;
    }

    public Imagenes getObjImagen() {
        return objImagen;
    }

    public void setObjImagen(Imagenes objImagen) {
        this.objImagen = objImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaImgPrincipal() {
        return rutaImgPrincipal;
    }

    public void setRutaImgPrincipal(String rutaImgPrincipal) {
        this.rutaImgPrincipal = rutaImgPrincipal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getContarProductosCarrito() {
        return contarProductosCarrito;
    }

    public void setContarProductosCarrito(int contarProductosCarrito) {
        this.contarProductosCarrito = contarProductosCarrito;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList<Articulo> articulos) {
        this.articulos = articulos;
    }

    public Articulo getArticuloSelect() {
        return articuloSelect;
    }

    public void setArticuloSelect(Articulo articuloSelect) {
        this.articuloSelect = articuloSelect;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getCantidadStr() {
        return cantidadStr;
    }

    public void setCantidadStr(String cantidadStr) {
        this.cantidadStr = cantidadStr;
    }

    public String getPrecioStr() {
        return precioStr;
    }

    public void setPrecioStr(String precioStr) {
        this.precioStr = precioStr;
    }

}
