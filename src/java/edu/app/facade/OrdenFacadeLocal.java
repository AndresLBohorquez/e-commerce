/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.facade;

import edu.app.entity.Orden;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author david
 */
@Local
public interface OrdenFacadeLocal {

    void create(Orden orden);

    void edit(Orden orden);

    void remove(Orden orden);

    Orden find(Object id);

    List<Orden> findAll();

    List<Orden> findRange(int[] range);

    int count();

    public List<Orden> listarOrdenUsuario(int idUsuario);

    public boolean eliminarOrdenesUsuario(int usuario_idusuario);

    public boolean crearOrden(int idUsuario, String fecha, int numeroMesa, double total, int estadoOrden, String codigoOrden);

    public List<Orden> listarTodasOrdenes();

    public List<Orden> listarOrdenesPendientesUsuario(int idUsuario);

    public List<Orden> listaOrdenesPendientes();

    public List<Orden> listaOrdenesCompletadas();

    public List<Orden> listaOrdenesPreparacion();

    public List<Orden> listaOrdenesEnviando();

    public boolean actualizarOrden(int idOrden, String codigoOrden);

    public boolean cambiarEstadoOrden(int estado, int idOrden);

    public List<Orden> listaOrdenesPreparadas();

    public List<Orden> listarOrdenesPendientesFecha(String fechaIni, String fechaFin);

    public List<Orden> listarOrdenesMesa(String fecha, int idMesa);

    public List<Orden> buscarOrdenMesa(int numeroMesa);

    public List<Orden> listaOrdenesEntregadas();

    public List<Orden> listarOrdenesFecha(String fechaIni, String fechaFin);

    public List<Orden> listarOrdenesFecha(String fecha);

    public List<Orden> listarOrdenesPorMes(String fecha);

    

    
    
}
