package edu.app.entity;

import edu.app.entity.ContactoProveedor;
import edu.app.entity.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(Proveedor.class)
public class Proveedor_ { 

    public static volatile SingularAttribute<Proveedor, String> nombreProveedor;
    public static volatile SingularAttribute<Proveedor, Integer> idproveedor;
    public static volatile CollectionAttribute<Proveedor, ContactoProveedor> contactoProveedorCollection;
    public static volatile CollectionAttribute<Proveedor, Producto> productoCollection;

}