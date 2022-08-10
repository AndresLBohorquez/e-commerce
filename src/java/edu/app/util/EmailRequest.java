package edu.app.util;

import edu.app.entity.CorreoSmtp;
import edu.app.entity.Usuario;
import edu.app.facade.CorreoSmtpFacadeLocal;
import edu.app.facade.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.PrimeFaces;

@Named(value = "emailRequest")
@RequestScoped
public class EmailRequest implements Serializable {

    @EJB
    CorreoSmtpFacadeLocal correoSmtpFacadeLocal;

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    ArrayList<CorreoSmtp> listCorreoSmtp = new ArrayList<>();

    private String to;
    private String from;
    private String subject;
    private String descr;
    private String username;
    private String password;
    private String smtp;
    private int port;
    private String correos;
    private String resultado;
    private String tipoMensaje;
    ContenidoOrderMail com = new ContenidoOrderMail();
    String mensajes;

    public EmailRequest() {
        this.to = " ";
        this.from = "laesquinallanera333@gmail.com";
        this.subject = null;
        this.descr = null;
        this.username = "laesquinallanera333@gmail.com";
        this.password = "rootAdmin123";
        this.smtp = "smtp.gmail.com";
        this.port = 587;//25 Puerto 587 para que habilite el correo Gmail
    }
    
  
    public void validateEmail(FacesContext context, UIComponent toValidate, Object value) {
        String email = (String) value;
        if ((email == null) || (email.equals(""))) {
            ((UIInput) toValidate).setValid(false);
            tipoMensaje = "Email Requerido";

            context.addMessage(toValidate.getClientId(context), new FacesMessage(tipoMensaje));
        } else if ((!email.contains("@")) || (!email.contains("."))) {
            ((UIInput) toValidate).setValid(false);
            tipoMensaje = "Email Invalido";
            context.addMessage(toValidate.getClientId(context), new FacesMessage(tipoMensaje));
        }
    }

    public String submitEmail() {
        Properties props = null;
        Session session = null;
        MimeMessage message = null;
        Address fromAddress = null;
        Address toAddress = null;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.port", port);

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        message = new MimeMessage(session);

        try {
            message.setContent(getDescr(), "text/html");
            message.setSubject(getSubject());
            fromAddress = new InternetAddress(getFrom());
            message.setFrom(fromAddress);

            correos = getTo();
            convertirCorreo();

            String[] correos_destinos = resultado.split(",");
            Address[] receptores = new Address[correos_destinos.length];
            int j = 0;
            while (j < correos_destinos.length) {
                receptores[j] = new InternetAddress(correos_destinos[j]);
                j++;
            }

            message.setRecipients(Message.RecipientType.BCC, receptores);
            message.saveChanges();

            Transport transport = session.getTransport("smtp");
            transport.connect(this.smtp, this.port, this.username, this.password);
            if (!transport.isConnected()) {
                mensajes = "swal('Error!', 'El correo no se ha enviado', 'error')";
                return mensajes;
            }
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException me) {
            mensajes = "swal('Error!', 'El correo no se ha enviado', 'error')";
            return mensajes;
        }
        mensajes = "swal('Bien Hecho!', 'El correo se ha enviado correctamente', 'success')";
        descr = null;
        subject = null;
        to = null;

        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
        return mensajes;
        

    }

    public void convertirCorreo() {

        String nombre1 = correos.replaceAll("\"", "");
        String nombre2 = nombre1.replaceAll("value", "");
        String nombre3 = nombre2.replaceAll(":", "");
        String nombre4 = nombre3.replaceAll("}", "");
        String nombre5 = nombre4.replaceAll("]", "");
        String nombre6 = nombre5.replace('{', ' ');
        String nombre7 = nombre6.replace('[', ' ');
        resultado = nombre7.replaceAll(" ", "");
    }

    public void buscarCorreoClientes() {
        try {
            List<Usuario> email = usuarioFacadeLocal.listarCorreoClientes();
            int i = 0;
            StringBuilder correosTodos = new StringBuilder();
            
            for(Usuario correo : email){
                correosTodos.append(email.get(i).getEmail()+ ", ");
                i++;
                to = correosTodos.substring(0, correosTodos.length() - 2);
            }
            
        } catch (Exception e) {
            System.out.println("edu.app.util.EmailRequest.buscarCorreoClientes()" + e.getMessage());
        }
    }

    public void recuperarClaveMail(String para, String nombre, String clave) {

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", smtp);
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smpt.ssl.trust", smtp);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Recuperación de Contraseña");
            message.setContent(com.getCorreoPassword() + para + com.getCorreoPassword1() + clave + com.getCorreoPassword2(), "text/html");

            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void codigoOrdenMail(String para, String nombre, String codigoOrden) {

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", smtp);
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smpt.ssl.trust", smtp);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Codigo de Orden");
            message.setContent(com.getCorreoOrden() + nombre + com.getCorreoOrden1() + codigoOrden + com.getCorreoOrden2() , "text/html");

            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    
    
    

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getCorreos() {
        return correos;
    }

    public void setCorreos(String correos) {
        this.correos = correos;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

}
