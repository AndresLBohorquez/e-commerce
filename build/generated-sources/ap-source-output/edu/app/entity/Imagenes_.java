package edu.app.entity;

import edu.app.entity.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(Imagenes.class)
public class Imagenes_ { 

    public static volatile SingularAttribute<Imagenes, Short> principal;
    public static volatile SingularAttribute<Imagenes, String> ruta;
    public static volatile CollectionAttribute<Imagenes, Producto> productoCollection;

}