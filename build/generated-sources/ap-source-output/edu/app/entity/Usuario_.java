package edu.app.entity;

import edu.app.entity.Direccion;
import edu.app.entity.EstadoUsuario;
import edu.app.entity.Message;
import edu.app.entity.Orden;
import edu.app.entity.Pqrs;
import edu.app.entity.Rol;
import edu.app.entity.Telefono;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile SingularAttribute<Usuario, String> fotoPerfil;
    public static volatile SingularAttribute<Usuario, Short> visible;
    public static volatile SingularAttribute<Usuario, String> fechaLogin;
    public static volatile SingularAttribute<Usuario, String> fechaRegistro;
    public static volatile CollectionAttribute<Usuario, Direccion> direccionCollection;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, Integer> idusuario;
    public static volatile CollectionAttribute<Usuario, Message> messageCollection;
    public static volatile SingularAttribute<Usuario, String> apellido;
    public static volatile SingularAttribute<Usuario, EstadoUsuario> estadoUsuarioIdestadoUsuario;
    public static volatile CollectionAttribute<Usuario, Pqrs> pqrsCollection;
    public static volatile SingularAttribute<Usuario, Rol> rolIdrol;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile SingularAttribute<Usuario, String> dni;
    public static volatile CollectionAttribute<Usuario, Orden> ordenCollection;
    public static volatile CollectionAttribute<Usuario, Telefono> telefonoCollection;

}