/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.ContactoProveedor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dark Jack
 */
@Local
public interface ContactoProveedorFacadeLocal {

    void create(ContactoProveedor contactoProveedor);

    void edit(ContactoProveedor contactoProveedor);

    void remove(ContactoProveedor contactoProveedor);

    ContactoProveedor find(Object id);

    List<ContactoProveedor> findAll();

    List<ContactoProveedor> findRange(int[] range);

    int count();
    
}
