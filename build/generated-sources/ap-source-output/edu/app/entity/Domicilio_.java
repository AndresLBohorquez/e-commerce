package edu.app.entity;

import edu.app.entity.EstadoDomicilio;
import edu.app.entity.Orden;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(Domicilio.class)
public class Domicilio_ { 

    public static volatile SingularAttribute<Domicilio, String> nombreDomiciliario;
    public static volatile SingularAttribute<Domicilio, String> nombreCliente;
    public static volatile SingularAttribute<Domicilio, String> horaEntrega;
    public static volatile SingularAttribute<Domicilio, String> telefonoDomicilio;
    public static volatile SingularAttribute<Domicilio, String> fechaEntrega;
    public static volatile SingularAttribute<Domicilio, Integer> iddomicilio;
    public static volatile SingularAttribute<Domicilio, String> direccionDomicilio;
    public static volatile SingularAttribute<Domicilio, Orden> ordenIdorden;
    public static volatile SingularAttribute<Domicilio, String> barrioDomicilio;
    public static volatile SingularAttribute<Domicilio, EstadoDomicilio> estadoDomicilioIdestadoDomicilio;
    public static volatile SingularAttribute<Domicilio, String> fechaDomicilio;

}