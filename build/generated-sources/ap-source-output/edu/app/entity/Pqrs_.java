package edu.app.entity;

import edu.app.entity.EstadoPqrs;
import edu.app.entity.Message;
import edu.app.entity.Prioridad;
import edu.app.entity.TipoPqrs;
import edu.app.entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(Pqrs.class)
public class Pqrs_ { 

    public static volatile SingularAttribute<Pqrs, String> fechaActualizacionPqrs;
    public static volatile SingularAttribute<Pqrs, String> descripcion;
    public static volatile CollectionAttribute<Pqrs, Message> messageCollection;
    public static volatile SingularAttribute<Pqrs, Usuario> usuarioIdusuario;
    public static volatile SingularAttribute<Pqrs, String> fechaRegistroPqrs;
    public static volatile SingularAttribute<Pqrs, EstadoPqrs> estadoPqrsIdestadoPqrs;
    public static volatile SingularAttribute<Pqrs, TipoPqrs> tipoPqrsIdtipoPqrs;
    public static volatile SingularAttribute<Pqrs, Prioridad> prioridadIdprioridad;
    public static volatile SingularAttribute<Pqrs, Integer> idpqrs;

}