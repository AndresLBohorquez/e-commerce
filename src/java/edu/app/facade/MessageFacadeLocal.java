/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Message;
import edu.app.entity.Pqrs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author david
 */
@Local
public interface MessageFacadeLocal {

    void create(Message message);

    void edit(Message message);

    void remove(Message message);

    Message find(Object id);

    List<Message> findAll();

    List<Message> findRange(int[] range);

    int count();

    public boolean registerMessage(String message_text, int usuario_idusuario, int pqrs_idpqrs);

    public List<Message> listarPorMensaje(int pqrs_idpqrs);

    public boolean eliminarMensajes(int usuario_idusuario);

    public List<Message> listarPqrs(int idPqrs);

    
}
