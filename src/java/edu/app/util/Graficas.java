/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.util;

import edu.app.entity.TopProductos;
import java.util.List;

/**
 *
 * @author andre
 */
public class Graficas {
    
    public int numeroMayor(List<TopProductos> estadisticasProductos){
        int mayor = estadisticasProductos.get(0).getCantidad();
		for (int i = 1; i < estadisticasProductos.size(); i++) {
			if (estadisticasProductos.get(i).getCantidad() > mayor) {
				mayor = estadisticasProductos.get(i).getCantidad();
			}
		}
        return mayor;
    }
    
}
