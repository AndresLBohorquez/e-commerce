/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Domicilio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dark Jack
 */
@Local
public interface DomicilioFacadeLocal {

    void create(Domicilio domicilio);

    void edit(Domicilio domicilio);

    void remove(Domicilio domicilio);

    Domicilio find(Object id);

    List<Domicilio> findAll();

    List<Domicilio> findRange(int[] range);

    int count();

    public List<Domicilio> listarDomicilios(int idEstado);

    public List<Domicilio> listarDomiciliosOrden(int idEstadoDomicilio, int idEstadoOrden);

    public List<Domicilio> listarDomiciliosEntregadosDia(int idestadoDomicilio, String fecha);

    public List<Domicilio> listarDomiciliosPorOrden(int idOrden);
    
}
