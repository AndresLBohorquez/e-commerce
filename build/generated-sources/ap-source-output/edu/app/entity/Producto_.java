package edu.app.entity;

import edu.app.entity.Carrito;
import edu.app.entity.Categoria;
import edu.app.entity.Imagenes;
import edu.app.entity.Proveedor;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile SingularAttribute<Producto, Categoria> categoriaIdcategoria;
    public static volatile CollectionAttribute<Producto, Imagenes> imagenesCollection;
    public static volatile SingularAttribute<Producto, Double> precioUnitario;
    public static volatile SingularAttribute<Producto, Short> visible;
    public static volatile SingularAttribute<Producto, String> dependencia;
    public static volatile SingularAttribute<Producto, String> nombreProducto;
    public static volatile SingularAttribute<Producto, String> tamanio;
    public static volatile SingularAttribute<Producto, Proveedor> proveedorIdproveedor;
    public static volatile CollectionAttribute<Producto, Carrito> carritoCollection;
    public static volatile SingularAttribute<Producto, Integer> cantidad;
    public static volatile SingularAttribute<Producto, Integer> idproducto;
    public static volatile SingularAttribute<Producto, String> imagenPrincipal;

}