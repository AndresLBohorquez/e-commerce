/*
 * To change this license header choose License Headers in Project Properties.
 * To change this template file choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import com.csvreader.CsvReader;
import edu.app.entity.*;
import edu.app.facade.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;
import org.primefaces.PrimeFaces;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author david
 */
@Named(value = "productoView")
@ViewScoped
public class ProductoView implements Serializable {

    @EJB
    CategoriaFacadeLocal categoriaFacadeLocal;

    @EJB
    ProveedorFacadeLocal proveedorFacadeLocal;

    @EJB
    ProductoFacadeLocal productoFacadeLocal;

    @EJB
    ImagenesFacadeLocal imagenesFacadeLocal;

    private int cantidad;
    private String nombre_producto;
    private double precio_unitario;
    private String tamanio;
    private int categoria_idcategoria;
    private int proveedor_idproveedor;
    private String tipoMensaje = "";
    private Part archivoImagen;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
    private UploadedFile file;
    private String mensajes;

    private ArrayList<Categoria> categoriasProductos = new ArrayList<>();
    private ArrayList<Proveedor> listaProveedores = new ArrayList<>();
    private ArrayList<Producto> listaProductos = new ArrayList<>();

    Producto objProducto = new Producto();
    Categoria objCategoria = new Categoria();
    Proveedor objProveedor = new Proveedor();

    @PostConstruct
    public void cargaInicial() {
        try {
            listaProveedores.addAll(proveedorFacadeLocal.findAll());
            listaProductos.addAll(productoFacadeLocal.listarTodosProductos());
            objCategoria = categoriaFacadeLocal.find(1);
            categoriasProductos.addAll(categoriaFacadeLocal.findAll());
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProductoView.cargaInicial()" + e.getMessage());
        }
    }

    public List<Producto> importarProductosCSV() {
        List<Producto> producto = new ArrayList<>();
        boolean respuesta = false;
        try {
            CsvReader leerProductos = new CsvReader(new InputStreamReader(file.getInputStream()));
            String path = file.getFileName();
            leerProductos.readHeaders();

            while (leerProductos.readRecord()) {
                String nombre_producto = leerProductos.get(0);
                String precio_unitario = leerProductos.get(1);
                precio_unitario = precio_unitario.substring(1, precio_unitario.length());
                String tamanio = leerProductos.get(2);
                String categoria = leerProductos.get(3);
                String proveedor = leerProductos.get(4);
                String cantidad = leerProductos.get(5);
                String descripcion = leerProductos.get(6);
                String imagen = leerProductos.get(7);

                objProducto.setCantidad(Integer.parseInt(cantidad));
                objProducto.setNombreProducto(nombre_producto);
                objProducto.setPrecioUnitario(Double.parseDouble(precio_unitario));
                objProducto.setTamanio(tamanio);
                objProducto.setDescripcion(descripcion);
                objProducto.setImagenPrincipal(imagen);
                objCategoria = categoriaFacadeLocal.listarnombreCategoria(categoria);
                objProducto.setCategoriaIdcategoria(objCategoria);
                objProveedor = proveedorFacadeLocal.listarnombreProveedor(proveedor);
                objProducto.setProveedorIdproveedor(objProveedor);
                productoFacadeLocal.create(objProducto);
            }
            file = null;
            leerProductos.close();
            mensajes = "swal('Bien hecho!', 'La información se ha registrado correctamente!', 'success')";
        } catch (FileNotFoundException e) {
            mensajes = "swal('Error!', 'No se ha podido registrar la información!', 'error')";
            e.printStackTrace();
        } catch (IOException e) {
            mensajes = "swal('Error!', 'No se ha podido registrar la información!', 'error')";
            e.printStackTrace();
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");

        return producto;
    }

    /**
     * GET y SET
     *
     * @return
     */
    public Producto getObjProducto() {
        return objProducto;
    }

    public void setObjProducto(Producto objProducto) {
        this.objProducto = objProducto;
    }

    public Categoria getObjCategoria() {
        return objCategoria;
    }

    public void setObjCategoria(Categoria objCategoria) {
        this.objCategoria = objCategoria;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ArrayList<Categoria> getCategoriasProductos() {
        return categoriasProductos;
    }

    public void setCategoriasProductos(ArrayList<Categoria> categoriasProductos) {
        this.categoriasProductos = categoriasProductos;
    }

    public ArrayList<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(ArrayList<Proveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public ProductoView() {
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

    /**
     * Select category of products + Methods fields
     *
     * @param idCategoria
     */
    public void seleccionCategorias(int idCategoria) {
        objCategoria = categoriaFacadeLocal.find(idCategoria);
    }

//    public void registrarProducto() {
//        try {
//            boolean respuesta = productoFacadeLocal.registrarProducto(cantidad, nombre_producto, precio_unitario, tamanio, categoria_idcategoria, proveedor_idproveedor);
//            tipoMensaje = "ok_PR";
//            cantidad = 0;
//            nombre_producto = "";
//            precio_unitario = 0;
//            tamanio = "";
//            categoria_idcategoria = 0;
//            proveedor_idproveedor = 0;
//            productSession.cargarImagen();
//        } catch (Exception e) {
//            System.out.println("edu.app.controlador.ProductoView.registrarProducto()" + e.getMessage());
//            tipoMensaje = "no_ PR";
//        }
//    }
    public void eliminarProducto(Producto producto) {
        try {
            productoFacadeLocal.remove(producto);
            listaProductos.remove(producto);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.ProductoView.eliminarProducto()" + e.getMessage());
        }
    }

    public List<Producto> listaPorCategoria() {
        return productoFacadeLocal.listaPorCategoria(objCategoria.getIdcategoria());
    }

//    public void actualizarProducto(int idproducto) {
//        try {
//            boolean respuesta = productoFacadeLocal.actualizarProducto(cantidad, nombre_producto, precio_unitario, tamanio, categoria_idcategoria, proveedor_idproveedor, idproducto);
//            tipoMensaje = "ok_PA";
//            cantidad = 0;
//            nombre_producto = "";
//            precio_unitario = 0;
//            tamanio = "";
//            categoria_idcategoria = 0;
//            proveedor_idproveedor = 0;
//            cargarImagen();
//        } catch (Exception e) {
//            System.out.println("edu.app.controlador.ProductoView.actualizarProducto()" + e.getMessage());
//            tipoMensaje = "no_PA";
//        }
//    }
//    public void cargarImagen() {
//        String mensajes = "";
//        if (archivoImagen != null) {
//            if (archivoImagen.getSize() > 4000000) {
//                mensajes = "swal('Error!', 'El archivo es muy grande!', 'error')";
//            } else if (!"image/jpeg".equals(archivoImagen.getContentType())) {
//                mensajes = "swal('Error!', 'El archivo no es una imagen!', 'error')";
//            }
//            if (mensajes.isEmpty()) {
//                File carpeta = new File("D:/ecommerce/imagenes/productos");
//                if (!carpeta.exists()) {
//                    carpeta.mkdirs();
//                }
//                try (InputStream is = archivoImagen.getInputStream()) {
//                    Calendar hoy = Calendar.getInstance();
//                    String nombreArchivo = sdf.format(hoy.getTime()) + ".";
//                    nombreArchivo += FilenameUtils.getExtension(archivoImagen.getSubmittedFileName());
//                    Files.copy(is, (new File(carpeta, nombreArchivo)).toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//                    Imagenes imgNew = new Imagenes();
//                    if (archivoImagen.getSubmittedFileName().length() > 30) {
//                        imgNew.setNombre(archivoImagen.getSubmittedFileName().substring(0, 30));
//                    } else {
//                        imgNew.setNombre(archivoImagen.getSubmittedFileName());
//
//                    }
//                    imgNew.setRuta(nombreArchivo);
////boolean respuesta = imagenesFacadeLocal.registrarImagen(archivoImagen.getName(), archivoImagen.getSubmittedFileName());
//                    imagenesFacadeLocal.create(imgNew);
//                    productoFacadeLocal.imagenProducto(imgNew.getIdimagenes(), objProducto.getIdproducto());
//                    objProducto = productoFacadeLocal.productoActualizado(objProducto.getIdproducto());
//                } catch (Exception e) {
//                    mensajes = "swal('Error!', 'No se pudo guardar la imagen!', 'error')";
//                    System.out.println("edu.app.controlador.ProductoView.cargarImagen()" + e.getMessage());
//
//                }
//
//            }
//
//        } else {
//            mensajes = "swal('Error!', 'No se encontro una imagen!', 'error')";
//        }
//        PrimeFaces.current().executeScript(mensajes);
//        PrimeFaces.current().executeScript("$('#reset').click()");
//    }
//    public void seleccionProducto(int idProducto) {
//        objProducto = productoFacadeLocal.find(idProducto);
//        try {
//            FacesContext fc = FacesContext.getCurrentInstance();
//            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
//            String ctx = ext.getRequestContextPath();
//            fc.getExternalContext().redirect(ctx + "/admin-ultimate/productos/actualizar.xhtml");
//        } catch (Exception e) {
//            System.out.println("edu.app.controlador.ProductSession.seleccionProducto()" + e.getMessage());
//        }
//    }
    public List<Producto> mostrarDatosProducto() {
        return productoFacadeLocal.listaPorProducto(objProducto.getIdproducto());
    }

    public Part getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(Part archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getMsjCsv() {
        return mensajes;
    }

    public void setMsjCsv(String msjCsv) {
        this.mensajes = msjCsv;
    }

}
