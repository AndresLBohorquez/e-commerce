/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Telefono;
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
public class TelefonoFacade extends AbstractFacade<Telefono> implements TelefonoFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TelefonoFacade() {
        super(Telefono.class);
    }

    @Override
    public boolean registrarTelefono(String numeroTelefono, int usuario_idusuario, String tipoTelefono) {
        try {
            Query qt = em.createNativeQuery("INSERT INTO telefono (numerotelefono, usuario_idusuario, tipo_telefono) VALUES (?, ?, ?)");
            qt.setParameter(1, numeroTelefono);
            qt.setParameter(2, usuario_idusuario);
            qt.setParameter(3, tipoTelefono);

            int salida = qt.executeUpdate();
            if (salida == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("edu.app.facade.TelefonoFacade.registrarDireccion()" + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Telefono> listarTelefonos(int idUsuario) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT u FROM Telefono u WHERE u.usuarioIdusuario.idusuario = :idUsuario");
            qt.setParameter("idUsuario", idUsuario);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.TelefonoFacade.listarTelefonos()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean actualizarTelefono(String numeroTelefono, String tipoTelefono, int idTelefono) {
        try {
            Query qt = em.createNativeQuery("UPDATE telefono SET numerotelefono = ?, tipo_telefono = ? WHERE (idtelefono = ?)");
            qt.setParameter(1, numeroTelefono);
            qt.setParameter(2, tipoTelefono);
            qt.setParameter(3, idTelefono);
            int salida = qt.executeUpdate();
            if (salida == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("edu.app.facade.TelefonoFacade.actualizarTeledonos()" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarTipoTelefono(int idUsuario) {
        try {
            Query qt = em.createNativeQuery("UPDATE telefono SET tipo_telefono = 'normal' WHERE (usuario_idusuario = ?)");
            qt.setParameter(1, idUsuario);
            int salida = qt.executeUpdate();
            return (salida == 1) ? true : false;
        } catch (Exception e) {
            System.out.println("edu.app.facade.TelefonoFacade.actualizarTipoTelefono()" + e.getMessage());
            return false;
        }
    }

    
    @Override
    public Telefono listarTelPrincipal(int idUsuario, String tipoTelefono) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT t FROM Telefono t WHERE t.usuarioIdusuario.idusuario = :idUsuario AND t.tipoTelefono = :tipoTelefono");
            qt.setParameter("idUsuario", idUsuario);
            qt.setParameter("tipoTelefono", tipoTelefono);
            return (Telefono) qt.getSingleResult();
        } catch (Exception e) {
            System.out.println("edu.app.facade.TelefonoFacade.listarDirPrincipal()" + e.getMessage());
            return new Telefono();
        }
    }
    
       
    @Override
    public List<Telefono> listarTelNormal(int idUsuario, String tipoTelefono) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT t FROM Telefono t WHERE t.usuarioIdusuario.idusuario = :idUsuario AND t.tipoTelefono = :tipoTelefono");
            qt.setParameter("idUsuario", idUsuario);
            qt.setParameter("tipoTelefono", tipoTelefono);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.TelefonoFacade.listarDirNormal()" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
