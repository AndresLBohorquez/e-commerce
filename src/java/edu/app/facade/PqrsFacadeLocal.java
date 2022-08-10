/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Pqrs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dark Jack
 */
@Local
public interface PqrsFacadeLocal {

    void create(Pqrs pqrs);

    void edit(Pqrs pqrs);

    void remove(Pqrs pqrs);

    Pqrs find(Object id);

    List<Pqrs> findAll();

    List<Pqrs> findRange(int[] range);

    int count();

    public boolean registrarPqrs(String fecha_registro_pqrs, String fecha_actualizacion_pqrs, String descripcion, int usuario_idusuario, int estado_pqrs_idestado_pqrs, int tipo_pqrs_idtipo_pqrs, int prioridad_idprioridad);

    public List<Pqrs> listarPorUsuario(int usuario_idusuario);

    public List<Pqrs> listarPorPqrs(int usuario_idusuario, int idpqrs);

    public boolean eliminarPqrs(int usuario_idusuario);

    public List<Pqrs> listarPqrs(int estadoPqrs);

    
}
