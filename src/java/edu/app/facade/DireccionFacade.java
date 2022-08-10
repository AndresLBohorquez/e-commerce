/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Direccion;
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
public class DireccionFacade extends AbstractFacade<Direccion> implements DireccionFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DireccionFacade() {
        super(Direccion.class);
    }

    @Override
    public boolean registrarDireccion(String nombre_direccion, String barrio, int usuario_idusuario, String tipoDireccion) {
        try {
            Query qt = em.createNativeQuery("INSERT INTO direccion (nombre_direccion, barrio, usuario_idusuario, tipo_direccion) VALUES (?, ?, ?, ?)");
            qt.setParameter(1, nombre_direccion);
            qt.setParameter(2, barrio);
            qt.setParameter(3, usuario_idusuario);
            qt.setParameter(4, tipoDireccion);

            int salida = qt.executeUpdate();
            if (salida == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("edu.app.facade.DireccionFacade.registrarDireccion()" + e.getMessage());
            return false;
        }

    }

    @Override
    public List<Direccion> ListarDirecciones(int idUsuario) {

        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT u FROM Direccion u WHERE u.usuarioIdusuario.idusuario = :idUsuario");
            qt.setParameter("idUsuario", idUsuario);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.DireccionFacade.validarUsuario()" + e.getMessage());
            return new ArrayList<>();
        }

    }

    @Override
    public boolean actualizarDireccion(String direccion, String barrio, String tipoDireccion, int idDireccion) {
        try {
            Query qt = em.createNativeQuery("UPDATE direccion SET nombre_direccion = ?, barrio = ?, tipo_direccion = ? WHERE (iddireccion = ?)");
            qt.setParameter(1, direccion);
            qt.setParameter(2, barrio);
            qt.setParameter(3, tipoDireccion);
            qt.setParameter(4, idDireccion);
            int salida = qt.executeUpdate();
            if (salida == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("edu.app.facade.DireccionFacade.actualizarDireccion()" + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Direccion> listarDireccionesUsuario(int idUsuario, int idDireccion) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT d FROM Direccion d where d.usuarioIdusuario.idusuario = :idUsuario and d.iddireccion = :idDireccion");
            qt.setParameter("idUsuario", idUsuario);
            qt.setParameter("idDireccion", idDireccion);

            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.DireccionFacade.listarDireccionesUsuario()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean actualizarTipoDireccion(int idUsuario) {
        try {
            Query qt = em.createNativeQuery("UPDATE direccion SET tipo_direccion = 'normal' WHERE (usuario_idusuario = ?)");
            qt.setParameter(1, idUsuario);
            int salida = qt.executeUpdate();
            return (salida == 1) ? true : false;
        } catch (Exception e) {
            System.out.println("edu.app.facade.DireccionFacade.actualizarTipoDireccion()" + e.getMessage());
            return false;
        }
    }

    @Override
    public Direccion listarDirPrincipal(int idUsuario, String tipoDireccion) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT d FROM Direccion d WHERE d.usuarioIdusuario.idusuario = :idUsuario AND d.tipoDireccion = :tipoDireccion");
            qt.setParameter("idUsuario", idUsuario);
            qt.setParameter("tipoDireccion", tipoDireccion);
            return (Direccion) qt.getSingleResult();
        } catch (Exception e) {
            System.out.println("edu.app.facade.DireccionFacade.listarDirPrincipal()" + e.getMessage());
            return new Direccion();
        }
    }

    @Override
    public List<Direccion> listarDirNormal(int idUsuario, String tipoDireccion) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT d FROM Direccion d WHERE d.usuarioIdusuario.idusuario = :idUsuario AND d.tipoDireccion = :tipoDireccion");
            qt.setParameter("idUsuario", idUsuario);
            qt.setParameter("tipoDireccion", tipoDireccion);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.DireccionFacade.listarDirPrincipal()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Direccion buscarBarrio(int idUsuario, String nombreDireccion) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT b FROM Direccion b WHERE b.usuarioIdusuario.idusuario = :idUsuario AND b.nombreDireccion = :nombreDireccion");
            qt.setParameter("idUsuario", idUsuario);
            qt.setParameter("nombreDireccion", nombreDireccion);
            return (Direccion) qt.getSingleResult();
        } catch (Exception e) {
            System.out.println("edu.app.facade.DireccionFacade.buscarBarrio()" + e.getMessage());
            return new Direccion();
        }
    }
}

