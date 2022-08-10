/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Proveedor;
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
public class ProveedorFacade extends AbstractFacade<Proveedor> implements ProveedorFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorFacade() {
        super(Proveedor.class);
    }
    
    
    
    @Override
    public Proveedor listarnombreProveedor(String nombreProveedor) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query p = em.createQuery("SELECT p FROM Proveedor p WHERE p.nombreProveedor = :nombreProveedor");
            p.setParameter("nombreProveedor", nombreProveedor);
            return (Proveedor) p.getSingleResult();
        } catch (Exception e) {
            System.out.println("edu.app.facade.ProveedorFacade.listarnombreProveedor()"+ e.getMessage());            
            return new Proveedor();
        }
    }
    
}
