/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.*;
import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;

/**
 *
 * @author Dark Jack
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario validarUsuario(String correoIn, String claveIn) {

        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT u FROM Usuario u WHERE u.clave = :claveIn AND u.email = :correoIn");
            qt.setParameter("claveIn", claveIn);
            qt.setParameter("correoIn", correoIn);
            return (Usuario) qt.getSingleResult();
        } catch (Exception e) {
            System.out.println("edu.app.facade.UsuarioFacade.validarUsuario()" + e.getMessage());
            return new Usuario();
        }

    }
    
    @Override
    public Usuario loginUsuario(String correoIn, String claveIn) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :correoIn AND u.clave = :claveIn");
            q.setParameter("correoIn", correoIn);
            q.setParameter("claveIn", claveIn);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return new Usuario();
        }
    }
    
 
    @Override
    public boolean registrarUsuario (String email, String dni, String clave, String nombre, String apellido, int rol_idrol, int estado_usuario_idestado_usuario, String fecha_registro, String fecha_login){
        try {
            Query qt = em.createNativeQuery("INSERT INTO usuario (email, dni, clave, nombre, apellido, rol_idrol, estado_usuario_idestado_usuario, fecha_registro, fecha_login) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            qt.setParameter(1, email);
            qt.setParameter(2, dni);
            qt.setParameter(3, clave);
            qt.setParameter(4, nombre);
            qt.setParameter(5, apellido);
            qt.setParameter(6, rol_idrol);
            qt.setParameter(7, estado_usuario_idestado_usuario);
            qt.setParameter(8, fecha_registro);
            qt.setParameter(9, fecha_login);
            
            int salida = qt.executeUpdate();
            
            if(salida == 1){
                return true;
            }else{
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("edu.app.facade.UsuarioFacade.registrarUsuario()" + e.getMessage());
            return false;
            
        }
        
    }
    
    public boolean removerUsuario(int id) {
        boolean retorno = false;
        try {
            Query qt = em.createQuery("DELETE FROM Usuario u WHERE u.id = :id");
            qt.setParameter("id", id);
            int salida = qt.executeUpdate();
            return true;
        } catch (Exception e) {
            return retorno;
        }
    }
    
    @Override
    public List <Usuario> listarDatosUsuario(int usuario_idusuario){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT u FROM Usuario u WHERE u.idusuario = :usuario_idusuario");
            qt.setParameter("usuario_idusuario", usuario_idusuario);
            return qt.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.UsuarioFacade.listarDatosUsuario()" + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    @Override
    public boolean actualizarUsuario(String email, String dni, String clave, String nombre, String apellido, int rol_idrol, int estado_usuario_idestado_usuario, int idusuario){
        
        try {
            Query qt = em.createNativeQuery("UPDATE usuario SET email = ?, dni = ?, clave = ?, nombre = ?, apellido = ?, rol_idrol = ?, estado_usuario_idestado_usuario = ? WHERE (idusuario = ?)");
            qt.setParameter(1, email);
            qt.setParameter(2, dni);
            qt.setParameter(3, clave);
            qt.setParameter(4, nombre);
            qt.setParameter(5, apellido);
            qt.setParameter(6, rol_idrol);
            qt.setParameter(7, estado_usuario_idestado_usuario);
            qt.setParameter(8, idusuario);
            int salida = qt.executeUpdate();
            return (salida == 1) ? true : false;
        } catch (Exception e) {
            System.out.println("edu.app.facade.UsuarioFacade.actualizarUsuario()" + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean actualizarUsuarioPerfil(String email, String clave, String nombre, String apellido, int idusuario){
        try {
            Query qt = em.createNativeQuery("UPDATE usuario SET email = ?, clave = ?, nombre = ?, apellido = ? WHERE (idusuario = ?)");
            qt.setParameter(1, email);
            qt.setParameter(2, clave);
            qt.setParameter(3, nombre);
            qt.setParameter(4, apellido);
            qt.setParameter(5, idusuario);
            int salida = qt.executeUpdate();
            return (salida == 1) ? true : false;
        } catch (Exception e) {
            System.out.println("edu.app.facade.UsuarioFacade.actualizarUsuarioPerfil()" + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<Usuario> listarUsuarios(){
        
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("SELECT * FROM usuario inner join direccion on idusuario = direccion.usuario_idusuario inner join telefono where idusuario = telefono.usuario_idusuario group by idusuario");
            return qt.getResultList();
          
        } catch (Exception e) {
            System.out.println("edu.app.facade.UsuarioFacade.listarUsuarios()" + e.getMessage());
            return new ArrayList<>();
        }
        
    }
    
    @Override
    public List<Usuario> listarTodos(){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query u = em.createQuery("SELECT u FROM Usuario u WHERE u.visible = 1");
            return u.getResultList();
        } catch (Exception e) {
            System.out.println("edu.app.facade.UsuarioFacade.listarTodos()" + e.getMessage());
            return null;
        }
    }
    
    
    @Override
     public Usuario buscarPorCorreo(String email) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email");
            q.setParameter("email", email);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return new Usuario();
        }
    }
    
    @Override
     public List<Usuario> listarCorreoClientes (){
         try {
             em.getEntityManagerFactory().getCache().evictAll();
             Query qt = em.createQuery("SELECT u FROM Usuario u WHERE u.rolIdrol.idrol = 1");
             return qt.getResultList();
         } catch (Exception e) {
             System.out.println("edu.app.facade.UsuarioFacade.listarCorreoClientes()" + e.getMessage());
             return null;
         }
     }

    @Override
     public boolean imagenPerfil(String fotoPerfil, int idUsuario){
         try {
             Query qt = em.createNativeQuery("UPDATE usuario SET foto_perfil = ? WHERE (idusuario = ?)");
             qt.setParameter(1, fotoPerfil);
             qt.setParameter(2, idUsuario);
             int salida = qt.executeUpdate();
            return (salida == 1);
         } catch (Exception e) {
             System.out.println("edu.app.facade.UsuarioFacade.imagenPerfil()" + e.getMessage());
             return false;
                     
         }
     }
             
}
