package edu.app.entity;

import edu.app.entity.Pqrs;
import edu.app.entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-09T08:54:22")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, String> messageText;
    public static volatile SingularAttribute<Message, Usuario> usuarioIdusuario;
    public static volatile SingularAttribute<Message, Integer> idmessage;
    public static volatile SingularAttribute<Message, Pqrs> pqrsIdpqrs;

}