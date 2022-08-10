/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.CorreoSmtp;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author andre
 */
@Local
public interface CorreoSmtpFacadeLocal {

    void create(CorreoSmtp correoSmtp);

    void edit(CorreoSmtp correoSmtp);

    void remove(CorreoSmtp correoSmtp);

    CorreoSmtp find(Object id);

    List<CorreoSmtp> findAll();

    List<CorreoSmtp> findRange(int[] range);

    int count();
    
}
