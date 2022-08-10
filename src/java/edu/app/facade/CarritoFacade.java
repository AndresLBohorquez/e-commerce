/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Carrito;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author andre
 */
@Stateless
public class CarritoFacade extends AbstractFacade<Carrito> implements CarritoFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarritoFacade() {
        super(Carrito.class);
    }

    @Override
    public List<Carrito> listarTodos() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query c = em.createQuery("SELECT c FROM Carrito c", Carrito.class);
            List<Carrito> listaCarrito = c.getResultList();
            return listaCarrito;
        } catch (Exception e) {
            System.out.println("edu.app.facade.CarritoFacade.listarTodos()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Carrito> productosVendidos() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNamedQuery("SELECT *, sum(cantidad) FROM carrito group by producto_idproducto");
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.CarritoFacade.productosVendidos()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Carrito> productosOrden(int idOrden) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query c = em.createQuery("SELECT c FROM Carrito c WHERE c.ordenIdorden.idorden = :idOrden");
            c.setParameter("idOrden", idOrden);
            return c.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.CarritoFacade.productosOrden()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Carrito> ordenesParrillaCocina(String dependencia, String estadoProducto) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT c FROM Carrito c WHERE c.productoIdproducto.dependencia = :dependencia AND c.estadoProducto = :estadoProducto");
            qt.setParameter("dependencia", dependencia);
            qt.setParameter("estadoProducto", estadoProducto);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.CarritoFacade.ordenesParrillaCocina()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Carrito> ordenesParrillaCocinaDia(String dependencia, String estadoProducto, String fecha) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT c FROM Carrito c WHERE c.productoIdproducto.dependencia = :dependencia AND c.estadoProducto = :estadoProducto AND c.fecha = :fecha");
            qt.setParameter("dependencia", dependencia);
            qt.setParameter("estadoProducto", estadoProducto);
            qt.setParameter("fecha", fecha);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.CarritoFacade.ordenesParrillaCocinaDia()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean eliminarProductosOrden(int idOrden) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("DELETE FROM Carrito WHERE orden_idorden = ?");
            qt.setParameter(1, idOrden);
            int salida = qt.executeUpdate();
            return (salida == 1) ? true : false;
        } catch (Exception e) {
            System.out.println("edu.app.facade.CarritoFacade.eliminarProductosOrden()" + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Carrito> listaPorProductos(int idProducto) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT c FROM Carrito c WHERE c.productoIdproducto.idproducto = :idProducto");
            qt.setParameter("idProducto", idProducto);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.CarritoFacade.listaPorProductos()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Carrito> listarPorFecha(String fecha) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT c FROM Carrito c WHERE c.fecha = :fecha");
            qt.setParameter("fecha", fecha);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.CarritoFacade.listarPorFecha()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Carrito> listaPorFechaProducto(String fecha, int idProducto) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT c FROM Carrito c WHERE c.fecha = :fecha AND c.productoIdproducto.idproducto = :idProducto");
            qt.setParameter("fecha", fecha);
            qt.setParameter("idProducto", idProducto);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.CarritoFacade.listaPorFechaProducto()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    
    @Override
    public List<Carrito> listaTopDiez() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT c, sum(c.cantidad)as Total FROM Carrito c GROUP BY c.productoIdproducto.idproducto ORDER BY total DESC");
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.CarritoFacade.listaTopDiez()" + e.getMessage());
            return new ArrayList<>();
        }

    }

}
