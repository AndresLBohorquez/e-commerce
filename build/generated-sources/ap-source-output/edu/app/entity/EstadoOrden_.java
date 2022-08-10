package edu.app.entity;

import edu.app.entity.HoraOrden;
import edu.app.entity.Orden;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(EstadoOrden.class)
public class EstadoOrden_ { 

    public static volatile SingularAttribute<EstadoOrden, String> nombreEstadoOrden;
    public static volatile SingularAttribute<EstadoOrden, Integer> idestadoOrden;
    public static volatile CollectionAttribute<EstadoOrden, Orden> ordenCollection;
    public static volatile CollectionAttribute<EstadoOrden, HoraOrden> horaOrdenCollection;

}