package edu.app.entity;

import edu.app.entity.Carrito;
import edu.app.entity.Domicilio;
import edu.app.entity.EstadoOrden;
import edu.app.entity.HoraOrden;
import edu.app.entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(Orden.class)
public class Orden_ { 

    public static volatile SingularAttribute<Orden, String> barrio;
    public static volatile SingularAttribute<Orden, String> hora;
    public static volatile SingularAttribute<Orden, EstadoOrden> estadoOrdenIdestadoOrden;
    public static volatile SingularAttribute<Orden, String> direccion;
    public static volatile SingularAttribute<Orden, Usuario> usuarioIdusuario;
    public static volatile SingularAttribute<Orden, Integer> idorden;
    public static volatile SingularAttribute<Orden, String> codigoOrden;
    public static volatile SingularAttribute<Orden, Double> valorEnvio;
    public static volatile SingularAttribute<Orden, String> pago;
    public static volatile SingularAttribute<Orden, Double> pagaCon;
    public static volatile SingularAttribute<Orden, String> nombres;
    public static volatile SingularAttribute<Orden, String> fecha;
    public static volatile SingularAttribute<Orden, Double> total;
    public static volatile SingularAttribute<Orden, Integer> numeroMesa;
    public static volatile SingularAttribute<Orden, Double> subtotal;
    public static volatile CollectionAttribute<Orden, Carrito> carritoCollection;
    public static volatile SingularAttribute<Orden, String> telefono;
    public static volatile CollectionAttribute<Orden, Domicilio> domicilioCollection;
    public static volatile CollectionAttribute<Orden, HoraOrden> horaOrdenCollection;

}