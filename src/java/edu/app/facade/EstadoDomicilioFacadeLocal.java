/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.EstadoDomicilio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dark Jack
 */
@Local
public interface EstadoDomicilioFacadeLocal {

    void create(EstadoDomicilio estadoDomicilio);

    void edit(EstadoDomicilio estadoDomicilio);

    void remove(EstadoDomicilio estadoDomicilio);

    EstadoDomicilio find(Object id);

    List<EstadoDomicilio> findAll();

    List<EstadoDomicilio> findRange(int[] range);

    int count();
    
}
