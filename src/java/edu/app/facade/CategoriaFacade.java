/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Categoria;
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
public class CategoriaFacade extends AbstractFacade<Categoria> implements CategoriaFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }

    
    @Override
    public Categoria listarnombreCategoria(String nombreCategoria) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query c = em.createQuery("SELECT c FROM Categoria c WHERE c.nombreCategoria = :nombreCategoria");
            c.setParameter("nombreCategoria", nombreCategoria);
            return (Categoria) c.getSingleResult();
        } catch (Exception e) {
            System.out.println("edu.app.facade.CategoriaFacade.listarnombreCategoria()" + e.getMessage());
            return new Categoria();
        }
    }

}
