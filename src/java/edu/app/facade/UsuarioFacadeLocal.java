/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dark Jack
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();

    public Usuario validarUsuario(String correoIn, String claveIn);

    public Usuario loginUsuario(String correoIn, String claveIn);

    public boolean removerUsuario(int id);

    public List<Usuario> listarDatosUsuario(int usuario_idusuario);

    public boolean registrarUsuario(String email, String dni, String clave, String nombre, String apellido, int rol_idrol, int estado_usuario_idestado_usuario, String fecha_registro, String fecha_login);

    public List<Usuario> listarUsuarios();

    public boolean actualizarUsuario(String email, String dni, String clave, String nombre, String apellido, int rol_idrol, int estado_usuario_idestado_usuario, int idusuario);

    public List<Usuario> listarTodos();

    public Usuario buscarPorCorreo(String email);

    public List<Usuario> listarCorreoClientes();

    public boolean actualizarUsuarioPerfil(String email, String clave, String nombre, String apellido, int idUsuario);

    public boolean imagenPerfil(String fotoPerfil, int idUsuario);
    
}
