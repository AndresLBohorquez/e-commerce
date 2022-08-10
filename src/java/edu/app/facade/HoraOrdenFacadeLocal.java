/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.HoraOrden;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author andre
 */
@Local
public interface HoraOrdenFacadeLocal {

    void create(HoraOrden horaOrden);

    void edit(HoraOrden horaOrden);

    void remove(HoraOrden horaOrden);

    HoraOrden find(Object id);

    List<HoraOrden> findAll();

    List<HoraOrden> findRange(int[] range);

    int count();

    public List<HoraOrden> horaEstadoOrden(int idOrden);
    
}
