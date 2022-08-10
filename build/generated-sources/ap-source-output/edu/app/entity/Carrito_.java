package edu.app.entity;

import edu.app.entity.Orden;
import edu.app.entity.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(Carrito.class)
public class Carrito_ { 

    public static volatile SingularAttribute<Carrito, Double> precioUnit;
    public static volatile SingularAttribute<Carrito, String> fecha;
    public static volatile SingularAttribute<Carrito, String> estadoProducto;
    public static volatile SingularAttribute<Carrito, String> hora;
    public static volatile SingularAttribute<Carrito, Orden> ordenIdorden;
    public static volatile SingularAttribute<Carrito, Integer> idcarrito;
    public static volatile SingularAttribute<Carrito, Integer> cantidad;
    public static volatile SingularAttribute<Carrito, Producto> productoIdproducto;

}