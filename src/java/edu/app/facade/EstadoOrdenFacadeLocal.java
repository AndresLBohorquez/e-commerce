/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.EstadoOrden;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author david
 */
@Local
public interface EstadoOrdenFacadeLocal {

    void create(EstadoOrden estadoOrden);

    void edit(EstadoOrden estadoOrden);

    void remove(EstadoOrden estadoOrden);

    EstadoOrden find(Object id);

    List<EstadoOrden> findAll();

    List<EstadoOrden> findRange(int[] range);

    int count();
    
}
