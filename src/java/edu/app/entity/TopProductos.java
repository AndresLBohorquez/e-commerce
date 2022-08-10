/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.entity;

/**
 *
 * @author andre
 */
public class TopProductos implements Comparable<TopProductos> {

    private int idProducto;
    private int cantidad;
    private String nombre;
    private String fecha;
    private boolean flag;

    public TopProductos(int idProducto, int cantidad, String nombre) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    public TopProductos(int idProducto, int cantidad, String nombre, String fecha, boolean flag) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.fecha = fecha;
        this.flag = flag;
    }

    public TopProductos() {
    }

    @Override
    public int compareTo(TopProductos tp) {
        if (tp.getCantidad() > cantidad) {
            return 1;
        } else if (tp.getCantidad() > cantidad) {
            return 0;
        } else {
            return -1;
        }
    }

    //Getter
    //
    //&
    //
    //Setter
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    

}
