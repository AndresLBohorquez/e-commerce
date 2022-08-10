/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Producto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dark Jack
 */
@Local
public interface ProductoFacadeLocal {

    void create(Producto producto);

    void edit(Producto producto);

    void remove(Producto producto);

    Producto find(Object id);

    List<Producto> findAll();

    List<Producto> findRange(int[] range);

    int count();


    public List<Producto> listaPorCategoria(int categoria_idcategoria);

    public List<Producto> listaPorProducto(int idproducto);


    public boolean imagenProducto(String ruta, int id_producto);

    public Producto productoActualizado(int id_producto);

    public List<Producto> listarTodosProductos();

    public boolean imagenPrincipal(String imagen_principal, int idproducto);

    public boolean limpiarImgPrincipal(String ruta);

    public boolean crearImgPrincipal(String ruta);

    public boolean actualizarProducto(int cantidad, String nombre_producto, double precio_unitario, String tamanio, int categoria_idcategoria, int proveedor_idproveedor, String descripcion, String dependencia, int idproducto);

    public boolean registrarProducto(int cantidad, String nombre_producto, double precio_unitario, String tamanio, int categoria_idcategoria, int proveedor_idproveedor, String descripcion, String dependencia);



    

    


    

    
    
}
