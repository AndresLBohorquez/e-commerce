package edu.app.entity;

import edu.app.entity.Domicilio;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(EstadoDomicilio.class)
public class EstadoDomicilio_ { 

    public static volatile SingularAttribute<EstadoDomicilio, String> nombreEstadoDomicilio;
    public static volatile SingularAttribute<EstadoDomicilio, Integer> idestadoDomicilio;
    public static volatile CollectionAttribute<EstadoDomicilio, Domicilio> domicilioCollection;

}