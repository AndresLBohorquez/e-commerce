/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.HoraOrden;
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
public class HoraOrdenFacade extends AbstractFacade<HoraOrden> implements HoraOrdenFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HoraOrdenFacade() {
        super(HoraOrden.class);
    }
    
    @Override
    public List<HoraOrden> horaEstadoOrden(int idOrden){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query ho = em.createQuery("SELECT ho FROM HoraOrden ho WHERE ho.ordenIdorden.idorden = :idOrden");
            ho.setParameter("idOrden", idOrden);
            return ho.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.HoraOrdenFacade.horaEstadoOrden()" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
