/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.*;
import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author GAES 8
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> implements ProductoFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    
    @Override
    public boolean registrarProducto(int cantidad, String nombre_producto, double precio_unitario, String tamanio, int categoria_idcategoria, int proveedor_idproveedor, String descripcion, String dependencia) {

        try {
            Query qt = em.createNativeQuery("INSERT INTO producto (cantidad, nombre_producto, precio_unitario, tamanio, categoria_idcategoria, proveedor_idproveedor, descripcion, dependencia) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            qt.setParameter(1, cantidad);
            qt.setParameter(2, nombre_producto);
            qt.setParameter(3, precio_unitario);
            qt.setParameter(4, tamanio);
            qt.setParameter(5, categoria_idcategoria);
            qt.setParameter(6, proveedor_idproveedor);
            qt.setParameter(7, descripcion);
            qt.setParameter(8, dependencia);
            int salida = qt.executeUpdate();

            if (salida == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("edu.app.facade.ProductoFacade.registrarProducto()" + e.getMessage());
            return false;
        }

    }

    @Override
    public List<Producto> listaPorCategoria(int categoria_idcategoria) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT p FROM Producto p WHERE p.categoriaIdcategoria.idcategoria = :categoria_idcategoria AND p.visible=1");
            qt.setParameter("categoria_idcategoria", categoria_idcategoria);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.ProductoFacade.listaPorCategoria()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Producto> listaPorProducto(int idproducto) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT p from Producto p WHERE p.idproducto = :idproducto");
            qt.setParameter("idproducto", idproducto);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.ProductoFacade.ListaPorProducto()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    
    
    @Override
    public boolean actualizarProducto(int cantidad, String nombre_producto, double precio_unitario, String tamanio, int categoria_idcategoria, int proveedor_idproveedor, String descripcion, String dependencia, int idproducto ) {

        try {
            Query qt = em.createNativeQuery("UPDATE producto SET cantidad = ?, nombre_producto = ?, precio_unitario = ?, tamanio = ?, descripcion = ?, imagen_principal = ?, proveedor_idproveedor = ?, dependencia = ? WHERE (idproducto = ?)");
            qt.setParameter(1, cantidad);
            qt.setParameter(2, nombre_producto);
            qt.setParameter(3, precio_unitario);
            qt.setParameter(4, tamanio);
            qt.setParameter(5, categoria_idcategoria);
            qt.setParameter(6, proveedor_idproveedor);
            qt.setParameter(7, descripcion);
            qt.setParameter(8, dependencia);
            qt.setParameter(9, idproducto);
            int salida = qt.executeUpdate();
            return (salida == 1) ? true : false;
        } catch (Exception e) {
            System.out.println("edu.app.facade.ProductoFacade.actualizarProducto()" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean imagenProducto(String ruta, int id_producto) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createNativeQuery("INSERT INTO imagenes_producto (imagenes_ruta, producto_idproducto) VALUES (?, ?)");
            q.setParameter(1, ruta);
            q.setParameter(2, id_producto);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Producto productoActualizado(int id_producto) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT p FROM Producto p WHERE p.idProducto = :id_producto");
            q.setParameter("id_producto", id_producto);
            return (Producto) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Producto> listarTodosProductos() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query u = em.createQuery("SELECT u FROM Producto u WHERE u.visible = 1");
            return u.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean imagenPrincipal(String imagen_principal, int idproducto) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("UPDATE producto SET imagen_principal = ? WHERE (idproducto = ?)");
            qt.setParameter(1, imagen_principal);
            qt.setParameter(2, idproducto);
            qt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("edu.app.facade.ProductoFacade.imagenPrincipal()" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean limpiarImgPrincipal(String ruta) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("UPDATE imagenes SET principal = 0 WHERE (ruta = ?)");
            qt.setParameter(1, ruta);
            qt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("edu.app.facade.ProductoFacade.limpiarImgPrincipal()" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean crearImgPrincipal(String ruta) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("UPDATE imagenes SET principal = 1 WHERE (ruta = ?)");
            qt.setParameter(1, ruta);
            qt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("edu.app.facade.ProductoFacade.crearImgPrincipal()" + e.getMessage());
            return false;
        }
    }

}
