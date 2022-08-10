package edu.app.entity;

import edu.app.entity.EstadoOrden;
import edu.app.entity.Orden;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(HoraOrden.class)
public class HoraOrden_ { 

    public static volatile SingularAttribute<HoraOrden, String> fecha;
    public static volatile SingularAttribute<HoraOrden, String> hora;
    public static volatile SingularAttribute<HoraOrden, EstadoOrden> estadoOrdenIdestadoOrden;
    public static volatile SingularAttribute<HoraOrden, Orden> ordenIdorden;
    public static volatile SingularAttribute<HoraOrden, Integer> id;

}