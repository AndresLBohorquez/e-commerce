/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Pqrs;
import java.util.ArrayList;
import java.util.Date;
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
public class PqrsFacade extends AbstractFacade<Pqrs> implements PqrsFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PqrsFacade() {
        super(Pqrs.class);
    }

//    public boolean insertarPqrs(Date fecha_registro_pqrs, Date fecha_actualizacion_pqrs, String descripcion, int usuario_idusuario, int estado_pqrs_idestado_pqrs, int tipo_pqrs_idtipo_pqrs, int prioridad_idprioridad) {
//        try {
//            Query qt = em.createNativeQuery("INSERT INTO pqrs (fecha_registro_pqrs, fecha_actualizacion_pqrs, descripcion, usuario_idusuario, estado_pqrs_idestado_pqrs, tipo_pqrs_idtipo_pqrs, prioridad_idprioridad) VALUES (?, ?, ?, ?, ?, ?, ?);");
//            qt.setParameter(1, fecha_registro_pqrs);
//            qt.setParameter(2, fecha_actualizacion_pqrs);
//            qt.setParameter(3, descripcion);
//            qt.setParameter(4, usuario_idusuario);
//            qt.setParameter(5, estado_pqrs_idestado_pqrs);
//            qt.setParameter(6, tipo_pqrs_idtipo_pqrs);
//            qt.setParameter(7, prioridad_idprioridad);
//        } catch (Exception e) {
//            System.out.println("edu.app.facade.PqrsFacade.insertarPqrs()" + e.getMessage());
//        }
//    }
    
    @Override
    public boolean registrarPqrs(String fecha_registro_pqrs, String fecha_actualizacion_pqrs, String descripcion, int usuario_idusuario, int estado_pqrs_idestado_pqrs, int tipo_pqrs_idtipo_pqrs, int prioridad_idprioridad){
        try {
            Query qt = em.createNativeQuery("INSERT INTO pqrs (fecha_registro_pqrs, fecha_actualizacion_pqrs, descripcion, usuario_idusuario, estado_pqrs_idestado_pqrs, tipo_pqrs_idtipo_pqrs, prioridad_idprioridad) VALUES (?, ?, ?, ?, ?, ?, ?)");
            qt.setParameter(1, fecha_registro_pqrs);
            qt.setParameter(2, fecha_actualizacion_pqrs);
            qt.setParameter(3, descripcion);
            qt.setParameter(4, usuario_idusuario);
            qt.setParameter(5, estado_pqrs_idestado_pqrs);
            qt.setParameter(6, tipo_pqrs_idtipo_pqrs);
            qt.setParameter(7, prioridad_idprioridad);
            int salida = qt.executeUpdate();
            return (salida == 1) ? true : false;
        } catch (Exception e) {
            System.out.println("edu.app.facade.PqrsFacade.registrarPqrs()" + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List <Pqrs> listarPorUsuario(int usuario_idusuario){
        try {
            Query qt = em.createQuery("SELECT p FROM Pqrs p WHERE p.usuarioIdusuario.idusuario = :usuario_idusuario");
            qt.setParameter("usuario_idusuario", usuario_idusuario);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.PqrsFacade.listarPorUsuario()" + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    @Override
    public List <Pqrs> listarPorPqrs(int usuario_idusuario, int idpqrs){
        try {
            Query qt = em.createQuery("SELECT p FROM Pqrs p WHERE p.usuarioIdusuario.idusuario = :usuario_idusuario AND p.idpqrs = :idpqrs");
            qt.setParameter("usuario_idusuario", usuario_idusuario);
            qt.setParameter("idpqrs", idpqrs);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.PqrsFacade.listarPorPqrs()" + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    @Override
    public boolean eliminarPqrs(int usuario_idusuario){
        
        try {
            Query qt = em.createNativeQuery("DELETE FROM pqrs WHERE (usuario_idusuario = ?)");
            qt.setParameter(1, usuario_idusuario);
            int salida = qt.executeUpdate();
            return (salida == 1) ? true:false;
        } catch (Exception e) {
            System.out.println("edu.app.facade.PqrsFacade.eliminarPqrs()" +e.getMessage());
            return false;
        }
        
    }
    
    @Override
    public List<Pqrs> listarPqrs(int estadoPqrs){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT p FROM Pqrs p WHERE p.estadoPqrsIdestadoPqrs.idestadoPqrs = :estadoPqrs");
            qt.setParameter("estadoPqrs", estadoPqrs);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.PqrsFacade.listarPqrs()" +e.getMessage());
            return new ArrayList<>();
        }
    }
   
    

}
