/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Domicilio;
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
public class DomicilioFacade extends AbstractFacade<Domicilio> implements DomicilioFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DomicilioFacade() {
        super(Domicilio.class);
    }

    @Override
    public List<Domicilio> listarDomicilios(int idEstado) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT d FROM Domicilio d WHERE d.estadoDomicilioIdestadoDomicilio.idestadoDomicilio = :idEstado");
            qt.setParameter("idEstado", idEstado);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.DomicilioFacade.listarDomicilios()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Domicilio> listarDomiciliosOrden(int idEstadoDomicilio, int idEstadoOrden) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT d FROM Domicilio d WHERE d.estadoDomicilioIdestadoDomicilio.idestadoDomicilio = :idEstadoDomicilio AND d.ordenIdorden.estadoOrdenIdestadoOrden.idestadoOrden = :idEstadoOrden");
            qt.setParameter("idEstadoDomicilio", idEstadoDomicilio);
            qt.setParameter("idEstadoOrden", idEstadoOrden);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.DomicilioFacade.listarDomiciliosOrden()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Domicilio> listarDomiciliosEntregadosDia(int idestadoDomicilio, String fecha) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT d FROM Domicilio d WHERE d.estadoDomicilioIdestadoDomicilio.idestadoDomicilio = :idestadoDomicilio AND d.fechaEntrega = :fecha");
            qt.setParameter("idestadoDomicilio", idestadoDomicilio);
            qt.setParameter("fecha", fecha);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.DomicilioFacade.listarDomiciliosEntregadosDia()" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Domicilio> listarDomiciliosPorOrden(int idOrden) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
               Query qt = em.createQuery("SELECT d FROM Domicilio d WHERE d.ordenIdorden.idorden = :idOrden");
               qt.setParameter("idOrden", idOrden);
               return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.DomicilioFacade.listarDomiciliosPorOrden()" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
