/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Mesa;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Dark Jack
 */
@Stateless
public class MesaFacade extends AbstractFacade<Mesa> implements MesaFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MesaFacade() {
        super(Mesa.class);
    }

    @Override
    public List<Mesa> listarTodas() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT m FROM Mesa m ORDER BY m.nombreMesa");
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.MesaFacade.listarTodas()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Mesa> listarDisponibilidad(String estadoMesa) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT m FROM Mesa m WHERE m.estadoMesa = :estadoMesa ORDER BY m.nombreMesa");
            qt.setParameter("estadoMesa", estadoMesa);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.MesaFacade.listardisponibilidad()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Mesa> buscarIdMesa(String nombreMesa) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT m FROM Mesa m WHERE m.nombreMesa = :nombreMesa");
            qt.setParameter("nombreMesa", nombreMesa);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.MesaFacade.buscarIdMesa()" + e.getMessage());
            return new ArrayList<>();
        }
    }

}
