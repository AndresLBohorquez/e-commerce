/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Telefono;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author andre
 */
@Local
public interface TelefonoFacadeLocal {

    void create(Telefono telefono);

    void edit(Telefono telefono);

    void remove(Telefono telefono);

    Telefono find(Object id);

    List<Telefono> findAll();

    List<Telefono> findRange(int[] range);

    int count();

    public List<Telefono> listarTelefonos(int idUsuario);

    public boolean actualizarTipoTelefono(int idUsuario);

    public boolean registrarTelefono(String numeroTelefono, int usuario_idusuario, String tipoTelefono);

    public boolean actualizarTelefono(String numeroTelefono, String tipoTelefono, int idTelefono);

    public Telefono listarTelPrincipal(int idUsuario, String tipoTelefono);

    public List<Telefono> listarTelNormal(int idUsuario, String tipoTelefono);


}
