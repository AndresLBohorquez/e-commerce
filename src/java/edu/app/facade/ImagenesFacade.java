/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Imagenes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author andre
 */
@Stateless
public class ImagenesFacade extends AbstractFacade<Imagenes> implements ImagenesFacadeLocal {

    @PersistenceContext(unitName = "LaEsquinaLlaneraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImagenesFacade() {
        super(Imagenes.class);
    }

    @Override
    public boolean registrarImagen(String nombre, String ruta) {
        try {
            Query qt = em.createNativeQuery("INSERT INTO imagenes (nombre, ruta) VALUES (?, ?)");
            qt.setParameter(1, nombre);
            qt.setParameter(2, ruta);
            int salida = qt.executeUpdate();
            return (salida == 1);
        } catch (Exception e) {
            System.out.println("edu.app.facade.ImagenesFacade.registrarImagen()" + e.getMessage());
            return false;
        }
    }
}
