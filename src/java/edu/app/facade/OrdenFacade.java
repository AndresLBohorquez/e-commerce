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
 * @author Tatiana Rodriguez
 */
@Stateless
public class OrdenFacade extends AbstractFacade<Orden> implements OrdenFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenFacade() {
        super(Orden.class);
    }

    @Override
    public boolean crearOrden(int idUsuario, String fecha, int numeroMesa, double total, int estadoOrden, String codigoOrden) {
        try {
            Query qt = em.createNativeQuery("INSERT INTO orden (usuario_idusuario, fecha, numero_mesa, total, estado_orden_idestado_orden, codigo_orden) VALUES (?, ?, ?, ?, ?, ?)");
            qt.setParameter(1, idUsuario);
            qt.setParameter(2, fecha);
            qt.setParameter(3, numeroMesa);
            qt.setParameter(4, total);
            qt.setParameter(5, estadoOrden);
            qt.setParameter(6, codigoOrden);
            int salida = qt.executeUpdate();
            return (salida == 1) ? true : false;
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.crearOrden()" + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Orden> listarOrdenUsuario(int idUsuario) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT o FROM Orden o WHERE o.usuarioIdusuario.idusuario = :idUsuario");
            qt.setParameter("idUsuario", idUsuario);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listarOrdenUsuario()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean eliminarOrdenesUsuario(int usuario_idusuario) {
        try {
            Query qt = em.createNativeQuery("DELETE FROM orden WHERE (usuario_idusuario = ?)");
            qt.setParameter(1, usuario_idusuario);
            int salida = qt.executeUpdate();
            return (salida == 1) ? true : false;
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.eliminarOrdenesUsuario()" + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Orden> listarTodasOrdenes() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query u = em.createQuery("SELECT u FROM Orden u");
            return u.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listarTodasOrdenes()" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Orden> listarOrdenesPendientesUsuario(int idUsuario) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query u = em.createQuery("SELECT u FROM Orden u WHERE (u.usuarioIdusuario.idusuario = :idUsuario AND u.estadoOrdenIdestadoOrden.idestadoOrden = 1)");
            u.setParameter("idUsuario", idUsuario);
            return u.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listarOrdenesPendientesUsuario()");
            return null;
        }
    }

    @Override
    public List<Orden> listaOrdenesPendientes() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query o = em.createQuery("SELECT o FROM Orden o WHERE (o.estadoOrdenIdestadoOrden.idestadoOrden = 1)");
            return o.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listaOrdenesPendientes()" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Orden> listaOrdenesCompletadas() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query o = em.createQuery("SELECT o FROM Orden o WHERE (o.estadoOrdenIdestadoOrden.idestadoOrden = 2)");
            return o.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listaOrdenesCompletadas()" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Orden> listaOrdenesPreparacion() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query o = em.createQuery("SELECT o FROM Orden o WHERE (o.estadoOrdenIdestadoOrden.idestadoOrden = 3)");
            return o.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listaOrdenesPreparacion()" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Orden> listaOrdenesPreparadas() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query o = em.createQuery("SELECT o FROM Orden o WHERE (o.estadoOrdenIdestadoOrden.idestadoOrden = 5)");
            return o.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listaOrdenesPreparadas()" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Orden> listaOrdenesEnviando() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query o = em.createQuery("SELECT o FROM Orden o WHERE (o.estadoOrdenIdestadoOrden.idestadoOrden = 4)");
            return o.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listaOrdenesEnviando()" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Orden> listaOrdenesEntregadas() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query o = em.createQuery("SELECT o FROM Orden o WHERE (o.estadoOrdenIdestadoOrden.idestadoOrden = 6)");
            return o.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listaOrdenesEntregadas()" + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean actualizarOrden(int idOrden, String codigoOrden) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("UPDATE orden SET codigo_orden = ? WHERE (idOrden = ?)");
            qt.setParameter(1, codigoOrden);
            qt.setParameter(2, idOrden);
            int salida = qt.executeUpdate();
            return (salida == 1) ? true : false;
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.actualizarOrden()" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean cambiarEstadoOrden(int estado, int idOrden) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("UPDATE orden SET estado_orden_idestado_orden = ? WHERE (idOrden = ?)");
            qt.setParameter(1, estado);
            qt.setParameter(2, idOrden);
            int salida = qt.executeUpdate();
            return (salida == 1) ? true : false;
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.completarOrden()" + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Orden> listarOrdenesPendientesFecha(String fechaIni, String fechaFin) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT o FROM Orden o WHERE (o.estadoOrdenIdestadoOrden.idestadoOrden = 2) AND o.fecha BETWEEN :fechaIni AND :fechaFin");
            qt.setParameter("fechaIni", fechaIni);
            qt.setParameter("fechaFin", fechaFin);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listarOrdenesPendientesFecha()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Orden> listarOrdenesMesa(String fecha, int idMesa) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT o FROM Orden o WHERE (o.fecha = :fecha) AND o.numeroMesa  =:idMesa");
            qt.setParameter("fecha", fecha);
            qt.setParameter("idMesa", idMesa);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listarOrdenesMesa()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Orden> buscarOrdenMesa(int numeroMesa) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT o FROM Orden o WHERE o.numeroMesa = :numeroMesa AND o.estadoOrdenIdestadoOrden.idestadoOrden != 2");
            qt.setParameter("numeroMesa", numeroMesa);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.buscarOrdenMesa()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Orden> listarOrdenesFecha(String fechaIni, String fechaFin) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT o FROM Orden o WHERE o.fecha BETWEEN :fechaIni AND :fechaFin");
            qt.setParameter("fechaIni", fechaIni);
            qt.setParameter("fechaFin", fechaFin);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listarOrdenesFecha()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Orden> listarOrdenesFecha(String fecha) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT o FROM Orden o WHERE o.fecha = :fecha");
            qt.setParameter("fecha", fecha);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listarOrdenesFecha()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Orden> listarOrdenesPorMes(String fecha) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT o FROM Orden o WHERE o.fecha LIKE :fecha");
            qt.setParameter("fecha", fecha);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.OrdenFacade.listarOrdenesPorMes()" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
