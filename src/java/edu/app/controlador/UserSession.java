/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.*;
import edu.app.facade.*;
import edu.app.util.Alerts;
import edu.app.util.Crypto;
import edu.app.util.EmailRequest;
import edu.app.util.FechaHora;
import edu.app.util.Redireccion;
import java.io.File;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.text.*;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.faces.context.*;
import javax.inject.Inject;
import org.apache.commons.codec.binary.Base64;
import org.primefaces.PrimeFaces;
import org.primefaces.shaded.commons.io.FilenameUtils;
import javax.servlet.http.Part;

@Named(value = "userSession")
@SessionScoped
public class UserSession implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    @EJB
    PqrsFacadeLocal pqrsFacadeLocal;

    @EJB
    MessageFacadeLocal messageFacadeLocal;

    @EJB
    RolFacadeLocal rolFacadeLocal;

    @EJB
    EstadoUsuarioFacadeLocal estadoUsuarioFacadeLocal;

    @EJB
    OrdenFacadeLocal ordenFacadeLocal;

    @EJB
    DireccionFacadeLocal direccionFacadeLocal;

    @EJB
    TelefonoFacadeLocal telefonoFacadeLocal;

    @EJB
    ImagenesFacadeLocal imagenesFacadeLocal;

    @Inject
    EmailRequest emailRequest;

    @Inject
    VentasSession ventasSession;

    /*
    Variables
     */
    private String correoIn;
    private String email;
    private String clave;
    private String tipoMensaje = "";
    private String tipoMensajeDir = "";
    private int idusuario;
    private int rol_idrol;
    private int estado_usuario_idestado_usuario;
    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String barrio;
    private String telefono1 = "";
    private String tipoTelefono = "";
    private String tipoDireccion;
    private boolean check;
    private boolean checkDir;
    private Part archivoImagen;
    String mensajes;

    String secretKey = "Somos los mejores";

    /*
    Variable para registrar orden
     */
    private String date_created;


    /*
    instancias objetos
     */
    private Pqrs pqrsObj = new Pqrs();
    private Message messageObj = new Message();
    private Usuario usuarioLogin = new Usuario();
    private Usuario editUserAdmin = new Usuario();
    private Usuario perfilUsuario = new Usuario();
    private Direccion dirObj = new Direccion();
    private Telefono telObj = new Telefono();
    private Alerts alerta = new Alerts();
    private Redireccion redir = new Redireccion();
    Crypto crypto = new Crypto();
    FechaHora fh = new FechaHora();

    /*
    Arreglos
     */
    private ArrayList<Pqrs> listaPqrs = new ArrayList<>();
    private ArrayList<Message> listMessage = new ArrayList<>();
    private ArrayList<Rol> listUserRol = new ArrayList<>();
    private ArrayList<EstadoUsuario> userStateList = new ArrayList<>();
    private ArrayList<Usuario> userList = new ArrayList<>();

    @PostConstruct
    public void cargaInicial() {
        listaPqrs.addAll(pqrsFacadeLocal.findAll());
        listMessage.addAll(messageFacadeLocal.findAll());
        listUserRol.addAll(rolFacadeLocal.findAll());
        userStateList.addAll(estadoUsuarioFacadeLocal.findAll());
        userList.addAll(usuarioFacadeLocal.findAll());
    }

    public UserSession() {
    }

    /*
     METHODS
     */
    public void iniciarSesion() {

        try {
            clave = encode(secretKey, clave);
            usuarioLogin = usuarioFacadeLocal.loginUsuario(correoIn, clave);
            usuarioLogin.setFechaLogin(fh.fecha());
            usuarioFacadeLocal.edit(usuarioLogin);
            if (usuarioLogin.getEstadoUsuarioIdestadoUsuario().getIdestadoUsuario() == 1) {
                if (usuarioLogin.getIdusuario() == null) {
                    mensajes = "swal('Error!', 'Usuario o contraseña incorrecta', 'error')";
                } else {
                    if (null != usuarioLogin.getRolIdrol().getIdrol()) {
                        switch (usuarioLogin.getRolIdrol().getIdrol()) {
                            case 5:
                                redir.redireccionar("/admin-ultimate/index.xhtml");
                                break;
                            case 1:
                                redir.redireccionar("/usuarios/client/platos.xhtml");
                                break;
                            case 3:
                                redir.redireccionar("/admin-ultimate/cajero/pendientes.xhtml");
                                break;
                            case 6:
                                redir.redireccionar("/admin-ultimate/parrilla/pendientes.xhtml");
                                break;
                            case 7:
                                redir.redireccionar("/admin-ultimate/cocina/pendientes.xhtml");
                                break;
                            case 2:
                                redir.redireccionar("/admin-ultimate/mesero/content-mesero.xhtml");
                                break;
                            case 4:
                                redir.redireccionar("/admin-ultimate/domiciliario/content-domiciliario.xhtml");
                                break;
                            default:
                                break;
                        }
                    }
                }
            } else {
                usuarioLogin = null;
                mensajes = "swal('Error!', 'El usuario no se encuentra registrado', 'error')";
            }

        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.iniciarSesion()" + e.getMessage());
            mensajes = "swal('Error!', 'El usuario no se encuentra registrado', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void cerrarSesion() {
        usuarioLogin = null;
        redir.redireccionar("/index.xhtml");
    }

    public void actualizarUsuario() {
        try {
            mensajes = "";

            if (email.equals("")) {
                email = usuarioLogin.getEmail();
            }
            if (clave.equals("")) {
                clave = crypto.desencriptar("Somos los mejores", usuarioLogin.getClave());
            }

            if (nombre.equals("")) {
                nombre = usuarioLogin.getNombre();
            }
            if (apellido.equals("")) {
                apellido = usuarioLogin.getApellido();
            }

            clave = crypto.encriptar("Somos los mejores", clave);

            boolean respuesta = usuarioFacadeLocal.actualizarUsuarioPerfil(email, clave, nombre, apellido, usuarioLogin.getIdusuario());
            mensajes = "swal('Bien Hecho!', 'La información se actualizado correctamente!', 'success')";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.actualizarUsuario()" + e.getMessage());
            mensajes = "swal('Error!', 'No se ha podido actualizar la información!', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void seleccionUsuarioPqrs(int usuario_idusuario) {
        pqrsObj = pqrsFacadeLocal.find(usuario_idusuario);
    }

    public void seleccionPqrs(int usuario_idusuario, int idpqrs) {
        pqrsObj = pqrsFacadeLocal.find(usuario_idusuario);
        pqrsObj = pqrsFacadeLocal.find(idpqrs);

        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            String ctx = ext.getRequestContextPath();
            if (usuarioLogin.getRolIdrol().getNombreRol().equals("Administrador")) {
                fc.getExternalContext().redirect(ctx + "/admin-ultimate/pqrs/responder.xhtml");
            } else if (usuarioLogin.getRolIdrol().getNombreRol().equals("Cliente")) {
                fc.getExternalContext().redirect("ticket-costumer-service.xhtml");
            }

        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.seleccionPqrs()" + e.getMessage());
        }

    }

    public void cargarImagenPerfil() {
        mensajes = "";
        if (archivoImagen != null) {
            if (archivoImagen.getSize() > 4000000) {
                mensajes = "swal('Error!', 'El archivo es muy grande!', 'error')";
            } else if (!"image/jpeg".equals(archivoImagen.getContentType())) {
                if (!"image/png".equals(archivoImagen.getContentType())) {
                    mensajes = "swal('Error!', 'El archivo no es una imagen!', 'error')";
                }
            } else if (!"image/png".equals(archivoImagen.getContentType())) {
                if (!"image/jpeg".equals(archivoImagen.getContentType())) {
                    mensajes = "swal('Error!', 'El archivo no es una imagen!', 'error')";
                }
            }

            if (mensajes.isEmpty()) {
                File carpeta = new File("C:/LaEsquinaLlanera/imagenes/profile");
                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }
                try (InputStream is = archivoImagen.getInputStream()) {

                    String nombreArchivo = "Profile" + usuarioLogin.getIdusuario() + ".";
                    nombreArchivo += FilenameUtils.getExtension(archivoImagen.getSubmittedFileName());
                    Files.copy(is, (new File(carpeta, nombreArchivo)).toPath(), StandardCopyOption.REPLACE_EXISTING);

                    usuarioFacadeLocal.imagenPerfil(nombreArchivo, usuarioLogin.getIdusuario());
                    mensajes = "swal('Bien Hecho!', 'La información se actualizado correctamente!', 'success')";
                } catch (Exception e) {
                    mensajes = "swal('Error!', 'No se pudo guardar la imagen!', 'error')";
                    System.out.println("edu.app.controlador.ProductSession.cargarImagen()" + e.getMessage());
                }
            }

        } else {
            mensajes = "swal('Error!', 'No se encontro una imagen!', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void borrarImgPerfilUsuario() {
        try {
            usuarioFacadeLocal.imagenPerfil("Profile.JPG", perfilUsuario.getIdusuario());
            mensajes = "swal('Bien Hecho!', 'La imagen se ha borrado correctamente!', 'success')";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.borrarImgperfilUsuario()" + e.getMessage());
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void seleccionMensajePqrs(int usuario_idusuario, int idRol) {
        pqrsObj = pqrsFacadeLocal.find(usuario_idusuario);
        pqrsObj = pqrsFacadeLocal.find(idRol);
    }

    public void seleccionMessage(int idpqrs) {
        messageObj = messageFacadeLocal.find(idpqrs);
    }

    public List<Usuario> listarUsuario() {
        return usuarioFacadeLocal.listarTodos();
    }

    public List<Pqrs> listarPqrsUsuario() {
        return pqrsFacadeLocal.listarPorUsuario(usuarioLogin.getIdusuario());
    }

    public List<Pqrs> listarPqrsIndividual() {
        return pqrsFacadeLocal.listarPorPqrs(usuarioLogin.getIdusuario(), pqrsObj.getIdpqrs());
    }

    public List<Pqrs> listarPqrsIndividualAdmin(int idUser, int idPqrs) {
        return pqrsFacadeLocal.listarPorPqrs(idUser, idPqrs);
    }

    public List<Message> listarChat(int id_idpqrs) {
        return messageFacadeLocal.listarPorMensaje(id_idpqrs);
    }

    /*
    Metodo para identificar el id del usuario a actualizar
     */
    public void seleccionUsuarioAdmin(int usuario_idusuario) {

        perfilUsuario = usuarioFacadeLocal.find(usuario_idusuario);
        try {
            redir.redireccionar("/admin-ultimate/usuarios/actualizar.xhtml");
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.seleccionUsuarioAdmin()" + e.getMessage());
        }
    }

    /*
    metodo para mostrar los datos del usuario a actualizar
     */
    public List<Usuario> listarDatosUsuario() {
        return usuarioFacadeLocal.listarDatosUsuario(perfilUsuario.getIdusuario());
    }

    public List<Usuario> listarDatosPerfilUsuario() {
        return usuarioFacadeLocal.listarDatosUsuario(perfilUsuario.getIdusuario());
    }

    public List<Usuario> listarDatosUsuario(int idUsuario) {
        return usuarioFacadeLocal.listarDatosUsuario(idUsuario);
    }

    public void seleccionPerfilUsuario(int usuario_idusuario) {
        perfilUsuario = usuarioFacadeLocal.find(usuario_idusuario);
        ventasSession.setObjDirPrincipal(direccionFacadeLocal.listarDirPrincipal(usuario_idusuario, "principal"));
        ventasSession.setObjTelPrincipal(telefonoFacadeLocal.listarTelPrincipal(usuario_idusuario, "principal"));
        try {
            redir.redireccionar("/admin-ultimate/usuarios/perfil.xhtml");
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.seleccionPerfilUsuario()" + e.getMessage());
        }
    }

    public List<Usuario> listarDatosUsuarioLogueado() {
        return usuarioFacadeLocal.listarDatosUsuario(usuarioLogin.getIdusuario());
    }

    public List<Orden> listarOrdenesUsuario() {
        return ordenFacadeLocal.listarOrdenUsuario(perfilUsuario.getIdusuario());
    }

    /*
    REGISTRAR USUARIO
     */
    public void registrarUsuario() {
        try {
            clave = encode(secretKey, clave);
            boolean respuesta = usuarioFacadeLocal.registrarUsuario(email, dni, clave, nombre, apellido, 1, 1, fh.fecha(), fh.fecha());

            if (respuesta) {
                inicioSesionAuto(email, clave);
                boolean respuestaDireccion = direccionFacadeLocal.registrarDireccion(direccion, barrio, usuarioLogin.getIdusuario(), "principal");
                boolean respuestaTelefono = telefonoFacadeLocal.registrarTelefono(telefono1, usuarioLogin.getIdusuario(), "principal");

            } else {
                tipoMensaje = "no_R";
            }

        } catch (Exception e) {
            System.out.println("edu.app.controlador.RegistroRequest.registrarUsuario()" + e.getMessage());
        }

        System.out.println(tipoMensaje);
    }

    public void registraTelefono(int idUsuario) {
        try {
            boolean respuestaTelefono = telefonoFacadeLocal.registrarTelefono(telefono1, usuarioLogin.getIdusuario(), "normal");
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.registraTelefono()" + e.getMessage());
        }
    }

    public void inicioSesionAuto(String correo, String clave) {
        try {
            usuarioLogin = usuarioFacadeLocal.loginUsuario(correo, clave);
            if (usuarioLogin.getIdusuario() == null) {
                tipoMensaje = "no_L";
            } else {

                FacesContext facesContext = FacesContext.getCurrentInstance();

                if (usuarioLogin.getRolIdrol().getIdrol() == 5) {
                    facesContext.getExternalContext().redirect("../admin-ultimate/index.xhtml");
                } else if (usuarioLogin.getRolIdrol().getIdrol() == 1) {
                    facesContext.getExternalContext().redirect("../usuarios/client/perfil.xhtml");
//                    boolean respuesta = ordenFacadeLocal.registrarOrdenes(usuarioLogin.getIdusuario(), 1, strDate);
                }
            }

        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.iniciarSesion()" + e.getMessage());
            tipoMensaje = "no_L";
        }
    }

    public String encode(String secretKey, String cadena) {
        String encriptacion = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] llavePassword = md5.digest(secretKey.getBytes("utf-8"));
            byte[] BytesKey = Arrays.copyOf(llavePassword, 24);
            SecretKey key = new SecretKeySpec(BytesKey, "DESede");
            Cipher cifrado = Cipher.getInstance("DESede");
            cifrado.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = cadena.getBytes("utf-8");
            byte[] buf = cifrado.doFinal(plainTextBytes);
            byte[] base64Bytes = org.apache.commons.codec.binary.Base64.encodeBase64(buf);
            encriptacion = new String(base64Bytes);
        } catch (Exception ex) {
            System.out.println("edu.encode.controlador.EncondeRequest.ecnode()" + ex.getMessage());
        }
        return encriptacion;
    }

    public String decode(String secretKey, String cadenaEncriptada) {
        String desencriptacion = "";
        try {
            byte[] message = Base64.decodeBase64(cadenaEncriptada.getBytes("utf-8"));
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md5.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText = decipher.doFinal(message);
            desencriptacion = new String(plainText, "UTF-8");

        } catch (Exception ex) {
            System.out.println("edu.encode.controlador.EncondeRequest.deecnode()" + ex.getMessage());
        }
        return desencriptacion;
    }

    public List<Direccion> listarDirecciones() {
        return direccionFacadeLocal.ListarDirecciones(usuarioLogin.getIdusuario());
    }

    public List<Telefono> listarTelefonos() {
        return telefonoFacadeLocal.listarTelefonos(usuarioLogin.getIdusuario());
    }

    public void registrarNewDir() {
        try {
            boolean respuesta = direccionFacadeLocal.registrarDireccion(direccion, barrio, usuarioLogin.getIdusuario(), "normal");
            direccion = "";
            barrio = "";
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.registrarNewDir()" + e.getMessage());
        }
    }

    public void actualizarTipodireccion() {
        try {
            boolean respuesta = direccionFacadeLocal.actualizarTipoDireccion(usuarioLogin.getIdusuario());
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.actualizarTipotelefono()" + e.getMessage());
        }
    }

    public void eliminarDir(Direccion diRemove) {
        try {
            direccionFacadeLocal.remove(diRemove);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.eliminarDir()" + e.getMessage());
        }
    }

    public void actualizarDireccion() {
        try {
            if (checkDir) {
                tipoDireccion = "principal";
                actualizarTipodireccion();
            } else {
                tipoDireccion = "normal";
            }

            if (direccion.equals("")) {
                direccion = dirObj.getNombreDireccion();
            }
            if (barrio.equals("")) {
                barrio = dirObj.getBarrio();
            }

            boolean respuesta = direccionFacadeLocal.actualizarDireccion(direccion, barrio, tipoDireccion, dirObj.getIddireccion());
            direccion = "";
            barrio = "";

            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            String ctx = ext.getRequestContextPath();
            fc.getExternalContext().redirect(ctx + "/usuarios/client/direcciones.xhtml");

        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.actualizarDireccion()" + e.getMessage());
        }
    }

    public void seleccionarDireccion(int iddireccion) {
        try {
            dirObj = direccionFacadeLocal.find(iddireccion);
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            String ctx = ext.getRequestContextPath();
            fc.getExternalContext().redirect(ctx + "/usuarios/client/update_direcciones.xhtml");

        } catch (Exception e) {
        }
    }

    public void seleccionarTelefono(int idTelefono) {
        try {
            telObj = telefonoFacadeLocal.find(idTelefono);
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            String ctx = ext.getRequestContextPath();
            fc.getExternalContext().redirect(ctx + "/usuarios/client/update_telefonos.xhtml");
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.seleccionarTelefono()" + e.getMessage());
        }
    }

    public void actualizarTipotelefono() {
        try {

            boolean respuesta = telefonoFacadeLocal.actualizarTipoTelefono(usuarioLogin.getIdusuario());
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.actualizarTipotelefono()" + e.getMessage());
        }
    }

    public void actualizarTelefono() {
        try {

            if (check) {
                tipoTelefono = "principal";
                actualizarTipotelefono();
            } else {
                tipoTelefono = "normal";
            }

            if (telefono1.equals("")) {
                telefono1 = telObj.getNumerotelefono();
            }

            //boolean respuesta = telefonoFacadeLocal.actualizarTelefonos(idTelefono, telefono1, tipoTelefono);
            boolean respuesta = telefonoFacadeLocal.actualizarTelefono(telefono1, tipoTelefono, telObj.getIdtelefono());
            telefono1 = "";
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            String ctx = ext.getRequestContextPath();
            fc.getExternalContext().redirect(ctx + "/usuarios/client/telefonos.xhtml");
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.actualizarTelefono()" + e.getMessage());
        }
    }

    public void recuperarClave() {

        try {
            //clave = encode(secretKey, clave);
            usuarioLogin = usuarioFacadeLocal.buscarPorCorreo(correoIn);
            if (usuarioLogin.getEmail() == null) {
                mensajes = "swal('Error!', 'Por favor ingrese un correo', 'error')";
                correoIn = "";
            } else {

                String nuevaClave = generarPassword();
                usuarioLogin.setClave(encode(secretKey, nuevaClave));
                usuarioFacadeLocal.edit(usuarioLogin);
                emailRequest.recuperarClaveMail(usuarioLogin.getEmail(), usuarioLogin.getNombre() + " " + usuarioLogin.getApellido(), nuevaClave);
                mensajes = "swal('Bien Hecho!', 'La información ha sido enviada a su correo electrónico', 'success')";
                correoIn = "";
            }

        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.recuperarClave()" + e.getMessage());
            mensajes = "swal('Error!', 'No se ha podido enviar la información', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");

    }

    public String generarPassword() {

        char[] mayusculas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] minusculas = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] numeros = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

        StringBuilder caracteres = new StringBuilder();
        caracteres.append(mayusculas);
        caracteres.append(minusculas);
        caracteres.append(numeros);

        StringBuilder password = new StringBuilder();

        for (int i = 0; i <= 15; i++) {
            int cantidadCaracteres = caracteres.length();
            int numeroRandom = (int) (Math.random() * cantidadCaracteres);

            password.append((caracteres.toString()).charAt(numeroRandom));
        }
        return password.toString();
    }

    public void eliminarTelefono(Telefono telefono) {
        try {
            telefonoFacadeLocal.remove(telefono);
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.eliminarTelefono()" + e.getMessage());
        }
    }

    public void ocultarUsuario(int idUsuario) {
        try {
            Usuario usu = usuarioFacadeLocal.find(idUsuario);
            if (usu.getIdusuario() == usuarioLogin.getIdusuario()) {
                mensajes = "swal('Error!', 'No es posible eliminar su propia cuenta', 'error')";
            } else {
                List<Orden> ordenUsuario = ordenFacadeLocal.listarOrdenUsuario(idUsuario);
                if (ordenUsuario.size() > 0) {
                    short visible = 0;
                    EstadoUsuario eu = new EstadoUsuario();
                    eu.setIdestadoUsuario(3);
                    usu.setVisible(visible);
                    usu.setEstadoUsuarioIdestadoUsuario(eu);
                    usuarioFacadeLocal.edit(usu);
                    mensajes = "swal('Bien Hecho!', 'Se ha eliminado el usuario correctamente', 'success')";
                } else {
                    List<Pqrs> pqrsCliente = pqrsFacadeLocal.listarPorUsuario(idUsuario);
                    List<Direccion> direccionesCliente = direccionFacadeLocal.ListarDirecciones(idUsuario);
                    List<Telefono> telefonosCliente = telefonoFacadeLocal.listarTelefonos(idUsuario);

                    if (direccionesCliente.size() > 0) {
                        for (int i = 0; i < direccionesCliente.size(); i++) {
                            Direccion objDireccion = direccionFacadeLocal.find(direccionesCliente.get(i).getIddireccion());
                            direccionFacadeLocal.remove(objDireccion);
                        }
                    }

                    if (telefonosCliente.size() > 0) {
                        for (int i = 0; i < telefonosCliente.size(); i++) {
                            Telefono objTelefono = telefonoFacadeLocal.find(telefonosCliente.get(i).getIdtelefono());
                            telefonoFacadeLocal.remove(objTelefono);
                        }
                    }

                    if (pqrsCliente.size() > 0) {
                        List<Message> mensajesPqrs = messageFacadeLocal.listarPqrs(pqrsCliente.get(0).getIdpqrs());

                        if (mensajesPqrs.size() > 0) {
                            for (int i = 0; i < mensajesPqrs.size(); i++) {
                                Message objMessage = messageFacadeLocal.find(mensajesPqrs.get(i).getIdmessage());
                                messageFacadeLocal.remove(objMessage);
                            }
                        }

                        for (int i = 0; i < pqrsCliente.size(); i++) {
                            Pqrs objPqrs = pqrsFacadeLocal.find(pqrsCliente.get(i).getIdpqrs());
                            pqrsFacadeLocal.remove(objPqrs);
                        }

                    }
                    usuarioFacadeLocal.remove(usu);
                    mensajes = "swal('Bien Hecho!', 'Se ha eliminado el usuario correctamente', 'success')";
                }
            }
        } catch (Exception e) {
            System.out.println("edu.app.controlador.UserSession.ocultarUsuario()" + e.getMessage());
            mensajes = "swal('Error!', 'No se ha eliminado el usuario', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    /*
    GETTERS Y SETTERS
     */
    public ArrayList<Usuario> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<Usuario> userList) {
        this.userList = userList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Rol> getListUserRol() {
        return listUserRol;
    }

    public void setListUserRol(ArrayList<Rol> listUserRol) {
        this.listUserRol = listUserRol;
    }

    public Message getMessageObj() {
        return messageObj;
    }

    public void setMessageObj(Message messageObj) {
        this.messageObj = messageObj;
    }

    public Pqrs getPqrsObj() {
        return pqrsObj;
    }

    public void setPqrsObj(Pqrs pqrsObj) {
        this.pqrsObj = pqrsObj;
    }

    public ArrayList<Pqrs> getListaPqrs() {
        return listaPqrs;
    }

    public void setListaPqrs(ArrayList<Pqrs> listaPqrs) {
        this.listaPqrs = listaPqrs;
    }

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public ArrayList<Message> getListMessage() {
        return listMessage;
    }

    public void setListMessage(ArrayList<Message> listMessage) {
        this.listMessage = listMessage;
    }

    public Usuario getEditUserAdmin() {
        return editUserAdmin;
    }

    public void setEditUserAdmin(Usuario editUserAdmin) {
        this.editUserAdmin = editUserAdmin;
    }

    public ArrayList<EstadoUsuario> getUserStateList() {
        return userStateList;
    }

    public void setUserStateList(ArrayList<EstadoUsuario> userStateList) {
        this.userStateList = userStateList;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getRol_idrol() {
        return rol_idrol;
    }

    public void setRol_idrol(int rol_idrol) {
        this.rol_idrol = rol_idrol;
    }

    public int getEstado_usuario_idestado_usuario() {
        return estado_usuario_idestado_usuario;
    }

    public void setEstado_usuario_idestado_usuario(int estado_usuario_idestado_usuario) {
        this.estado_usuario_idestado_usuario = estado_usuario_idestado_usuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTipoMensajeDir() {
        return tipoMensajeDir;
    }

    public void setTipoMensajeDir(String tipoMensajeDir) {
        this.tipoMensajeDir = tipoMensajeDir;
    }

    public Direccion getDirObj() {
        return dirObj;
    }

    public void setDirObj(Direccion dirObj) {
        this.dirObj = dirObj;
    }

    public Telefono getTelObj() {
        return telObj;
    }

    public void setTelObj(Telefono telObj) {
        this.telObj = telObj;
    }

    public String getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(String tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Part getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(Part archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public boolean isCheckDir() {
        return checkDir;
    }

    public void setCheckDir(boolean checkDir) {
        this.checkDir = checkDir;
    }

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public Alerts getAlerta() {
        return alerta;
    }

    public void setAlerta(Alerts alerta) {
        this.alerta = alerta;
    }

    public Redireccion getRedir() {
        return redir;
    }

    public void setRedir(Redireccion redir) {
        this.redir = redir;
    }

    public Usuario getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(Usuario perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

}
