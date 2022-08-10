/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.EstadoPqrs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dark Jack
 */
@Local
public interface EstadoPqrsFacadeLocal {

    void create(EstadoPqrs estadoPqrs);

    void edit(EstadoPqrs estadoPqrs);

    void remove(EstadoPqrs estadoPqrs);

    EstadoPqrs find(Object id);

    List<EstadoPqrs> findAll();

    List<EstadoPqrs> findRange(int[] range);

    int count();
    
}
