/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Direccion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author andre
 */
@Local
public interface DireccionFacadeLocal {

    void create(Direccion direccion);

    void edit(Direccion direccion);

    void remove(Direccion direccion);

    Direccion find(Object id);

    List<Direccion> findAll();

    List<Direccion> findRange(int[] range);

    int count();

    public List<Direccion> ListarDirecciones(int idUsuario);

    public List<Direccion> listarDireccionesUsuario(int idUsuario, int idDireccion);

    public boolean registrarDireccion(String nombre_direccion, String barrio, int usuario_idusuario, String tipoDireccion);

    public boolean actualizarTipoDireccion(int idUsuario);

    public boolean actualizarDireccion(String direccion, String barrio, String tipoDireccion, int idDireccion);

    public Direccion listarDirPrincipal(int idUsuario, String tipoDireccion);

    public List<Direccion> listarDirNormal(int idUsuario, String tipoDireccion);

    public Direccion buscarBarrio(int idUsuario, String nombreDireccion);




}
