package edu.app.entity;

import edu.app.entity.Pqrs;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(EstadoPqrs.class)
public class EstadoPqrs_ { 

    public static volatile SingularAttribute<EstadoPqrs, String> nombreEstadoPqrs;
    public static volatile SingularAttribute<EstadoPqrs, Integer> idestadoPqrs;
    public static volatile CollectionAttribute<EstadoPqrs, Pqrs> pqrsCollection;

}