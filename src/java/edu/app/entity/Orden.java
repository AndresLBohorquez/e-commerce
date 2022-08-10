/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dark Jack
 */
@Entity
@Table(name = "orden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orden.findAll", query = "SELECT o FROM Orden o")})
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorden")
    private Integer idorden;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fecha")
    private String fecha;
    @Size(max = 45)
    @Column(name = "hora")
    private String hora;
    @Column(name = "numero_mesa")
    private Integer numeroMesa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo_orden")
    private String codigoOrden;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Column(name = "paga_con")
    private Double pagaCon;
    @Size(max = 60)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 45)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "barrio")
    private String barrio;
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "valor_envio")
    private Double valorEnvio;
    @Size(max = 15)
    @Column(name = "pago")
    private String pago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenIdorden", fetch = FetchType.LAZY)
    private Collection<Carrito> carritoCollection;
    @OneToMany(mappedBy = "ordenIdorden", fetch = FetchType.LAZY)
    private Collection<Domicilio> domicilioCollection;
    @JoinColumn(name = "estado_orden_idestado_orden", referencedColumnName = "idestado_orden")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoOrden estadoOrdenIdestadoOrden;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioIdusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenIdorden", fetch = FetchType.LAZY)
    private Collection<HoraOrden> horaOrdenCollection;

    public Orden() {
    }

    public Orden(Integer idorden) {
        this.idorden = idorden;
    }

    public Orden(Integer idorden, String fecha, String codigoOrden) {
        this.idorden = idorden;
        this.fecha = fecha;
        this.codigoOrden = codigoOrden;
    }

    public Integer getIdorden() {
        return idorden;
    }

    public void setIdorden(Integer idorden) {
        this.idorden = idorden;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public String getCodigoOrden() {
        return codigoOrden;
    }

    public void setCodigoOrden(String codigoOrden) {
        this.codigoOrden = codigoOrden;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPagaCon() {
        return pagaCon;
    }

    public void setPagaCon(Double pagaCon) {
        this.pagaCon = pagaCon;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getValorEnvio() {
        return valorEnvio;
    }

    public void setValorEnvio(Double valorEnvio) {
        this.valorEnvio = valorEnvio;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    @XmlTransient
    public Collection<Carrito> getCarritoCollection() {
        return carritoCollection;
    }

    public void setCarritoCollection(Collection<Carrito> carritoCollection) {
        this.carritoCollection = carritoCollection;
    }

    @XmlTransient
    public Collection<Domicilio> getDomicilioCollection() {
        return domicilioCollection;
    }

    public void setDomicilioCollection(Collection<Domicilio> domicilioCollection) {
        this.domicilioCollection = domicilioCollection;
    }

    public EstadoOrden getEstadoOrdenIdestadoOrden() {
        return estadoOrdenIdestadoOrden;
    }

    public void setEstadoOrdenIdestadoOrden(EstadoOrden estadoOrdenIdestadoOrden) {
        this.estadoOrdenIdestadoOrden = estadoOrdenIdestadoOrden;
    }

    public Usuario getUsuarioIdusuario() {
        return usuarioIdusuario;
    }

    public void setUsuarioIdusuario(Usuario usuarioIdusuario) {
        this.usuarioIdusuario = usuarioIdusuario;
    }

    @XmlTransient
    public Collection<HoraOrden> getHoraOrdenCollection() {
        return horaOrdenCollection;
    }

    public void setHoraOrdenCollection(Collection<HoraOrden> horaOrdenCollection) {
        this.horaOrdenCollection = horaOrdenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idorden != null ? idorden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        if ((this.idorden == null && other.idorden != null) || (this.idorden != null && !this.idorden.equals(other.idorden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.Orden[ idorden=" + idorden + " ]";
    }
    
}
