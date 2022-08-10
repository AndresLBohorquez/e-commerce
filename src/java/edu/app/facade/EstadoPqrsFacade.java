/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.EstadoPqrs;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dark Jack
 */
@Stateless
public class EstadoPqrsFacade extends AbstractFacade<EstadoPqrs> implements EstadoPqrsFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoPqrsFacade() {
        super(EstadoPqrs.class);
    }
    
}
