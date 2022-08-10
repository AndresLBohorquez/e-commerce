package edu.app.entity;

import edu.app.entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(EstadoUsuario.class)
public class EstadoUsuario_ { 

    public static volatile SingularAttribute<EstadoUsuario, String> nombreEstado;
    public static volatile CollectionAttribute<EstadoUsuario, Usuario> usuarioCollection;
    public static volatile SingularAttribute<EstadoUsuario, Integer> idestadoUsuario;

}