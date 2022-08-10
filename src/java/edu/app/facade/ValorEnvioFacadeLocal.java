/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.ValorEnvio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author andre
 */
@Local
public interface ValorEnvioFacadeLocal {

    void create(ValorEnvio valorEnvio);

    void edit(ValorEnvio valorEnvio);

    void remove(ValorEnvio valorEnvio);

    ValorEnvio find(Object id);

    List<ValorEnvio> findAll();

    List<ValorEnvio> findRange(int[] range);

    int count();
    
}
