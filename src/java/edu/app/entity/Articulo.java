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
public class Articulo {

    private int idProducto;
    private int cantidad = 0;
    private String nombre;
    private double precio;
    private String imagen;
    private String tamanio;
    private int cantidadTotal;

    public Articulo() {
    }

    public Articulo(int idProducto, int cantidad, String nombre, double precio, String imagen, String tamanio, int cantidadTotal) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.tamanio = tamanio;
        this.cantidadTotal = cantidadTotal;
    }

   
    
    
//    GETTER
//            
//       &
//    
//    SETTER
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

}
