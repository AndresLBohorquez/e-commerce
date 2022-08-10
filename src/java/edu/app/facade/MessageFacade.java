/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.*;
import java.util.*;
import javax.persistence.*;
import javax.ejb.Stateless;


/**
 *
 * @author david
 */
@Stateless
public class MessageFacade extends AbstractFacade<Message> implements MessageFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }
    
    @Override
    public boolean registerMessage(String message_text, int usuario_idusuario, int pqrs_idpqrs){
        try {
            Query qt = em.createNativeQuery("INSERT INTO sysrecoldb.message (message_text, usuario_idusuario, pqrs_idpqrs) VALUES (?, ?, ?)");
            qt.setParameter(1, message_text);
            qt.setParameter(2, usuario_idusuario);
            qt.setParameter(3, pqrs_idpqrs);
            int salida = qt.executeUpdate();
            return (salida == 1) ?  true : false;
        } catch (Exception e) {
            System.out.println("edu.app.facade.MessageFacade.registerMessage()" + e.getMessage());
            return false;
        }
    }
    
    @Override
     public List <Message> listarPorMensaje(int pqrs_idpqrs){
        try {
            Query qt = em.createQuery("SELECT m FROM Message m WHERE m.pqrsIdpqrs.idpqrs = :pqrs_idpqrs");
            qt.setParameter("pqrs_idpqrs", pqrs_idpqrs);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.PqrsFacade.listarPorPqrs()" + e.getMessage());
            return new ArrayList<>();
        }
    }
     
    @Override
     public boolean eliminarMensajes(int usuario_idusuario){
         try {
            Query qt = em.createNativeQuery("DELETE FROM message WHERE (usuario_idusuario = ?)");
            qt.setParameter(1, usuario_idusuario);
            int salida = qt.executeUpdate();
            return (salida == 1) ? true:false;
         } catch (Exception e) {
             System.out.println("edu.app.facade.MessageFacade.eliminarMensajes()" + e.getMessage());
             return false;
         }
     }
     
    @Override
     public List<Message> listarPqrs(int idPqrs){
         try {
             em.getEntityManagerFactory().getCache().evictAll();
             Query qt = em.createQuery("SELECT m FROM Message m WHERE m.pqrsIdpqrs.idpqrs = :idPqrs");
             qt.setParameter("idPqrs", idPqrs);
             return qt.getResultList();
         } catch (Exception e) {
             System.out.println("edu.app.facade.MessageFacade.listarPqrs()" + e.getMessage());
             return new ArrayList<>();
         }
     }
    
}
