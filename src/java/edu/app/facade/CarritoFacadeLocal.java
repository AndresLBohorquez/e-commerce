/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Carrito;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author andre
 */
@Local
public interface CarritoFacadeLocal {

    void create(Carrito carrito);

    void edit(Carrito carrito);

    void remove(Carrito carrito);

    Carrito find(Object id);

    List<Carrito> findAll();

    List<Carrito> findRange(int[] range);

    int count();

    public List<Carrito> listarTodos();

    public List<Carrito> productosVendidos();

    public List<Carrito> productosOrden(int idOrden);

    public List<Carrito> ordenesParrillaCocina(String dependencia, String estadoProducto);

    public List<Carrito> ordenesParrillaCocinaDia(String dependencia, String estadoProducto, String fecha);

    public boolean eliminarProductosOrden(int idOrden);

    public List<Carrito> listaPorProductos(int idProducto);

    public List<Carrito> listarPorFecha(String fecha);

    public List<Carrito> listaPorFechaProducto(String fecha, int idProducto);

    public List<Carrito> listaTopDiez();


    

}
