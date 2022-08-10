/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dark Jack
 */
@Entity
@Table(name = "domicilio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Domicilio.findAll", query = "SELECT d FROM Domicilio d")})
public class Domicilio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddomicilio")
    private Integer iddomicilio;
    @Size(max = 35)
    @Column(name = "fecha_domicilio")
    private String fechaDomicilio;
    @Size(max = 45)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Size(max = 80)
    @Column(name = "direccion_domicilio")
    private String direccionDomicilio;
    @Size(max = 45)
    @Column(name = "barrio_domicilio")
    private String barrioDomicilio;
    @Size(max = 45)
    @Column(name = "telefono_domicilio")
    private String telefonoDomicilio;
    @Size(max = 35)
    @Column(name = "nombre_domiciliario")
    private String nombreDomiciliario;
    @Size(max = 45)
    @Column(name = "fecha_entrega")
    private String fechaEntrega;
    @Size(max = 45)
    @Column(name = "hora_entrega")
    private String horaEntrega;
    @JoinColumn(name = "estado_domicilio_idestado_domicilio", referencedColumnName = "idestado_domicilio")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstadoDomicilio estadoDomicilioIdestadoDomicilio;
    @JoinColumn(name = "orden_idorden", referencedColumnName = "idorden")
    @ManyToOne(fetch = FetchType.LAZY)
    private Orden ordenIdorden;

    public Domicilio() {
    }

    public Domicilio(Integer iddomicilio) {
        this.iddomicilio = iddomicilio;
    }

    public Integer getIddomicilio() {
        return iddomicilio;
    }

    public void setIddomicilio(Integer iddomicilio) {
        this.iddomicilio = iddomicilio;
    }

    public String getFechaDomicilio() {
        return fechaDomicilio;
    }

    public void setFechaDomicilio(String fechaDomicilio) {
        this.fechaDomicilio = fechaDomicilio;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccionDomicilio() {
        return direccionDomicilio;
    }

    public void setDireccionDomicilio(String direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
    }

    public String getBarrioDomicilio() {
        return barrioDomicilio;
    }

    public void setBarrioDomicilio(String barrioDomicilio) {
        this.barrioDomicilio = barrioDomicilio;
    }

    public String getTelefonoDomicilio() {
        return telefonoDomicilio;
    }

    public void setTelefonoDomicilio(String telefonoDomicilio) {
        this.telefonoDomicilio = telefonoDomicilio;
    }

    public String getNombreDomiciliario() {
        return nombreDomiciliario;
    }

    public void setNombreDomiciliario(String nombreDomiciliario) {
        this.nombreDomiciliario = nombreDomiciliario;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public EstadoDomicilio getEstadoDomicilioIdestadoDomicilio() {
        return estadoDomicilioIdestadoDomicilio;
    }

    public void setEstadoDomicilioIdestadoDomicilio(EstadoDomicilio estadoDomicilioIdestadoDomicilio) {
        this.estadoDomicilioIdestadoDomicilio = estadoDomicilioIdestadoDomicilio;
    }

    public Orden getOrdenIdorden() {
        return ordenIdorden;
    }

    public void setOrdenIdorden(Orden ordenIdorden) {
        this.ordenIdorden = ordenIdorden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddomicilio != null ? iddomicilio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Domicilio)) {
            return false;
        }
        Domicilio other = (Domicilio) object;
        if ((this.iddomicilio == null && other.iddomicilio != null) || (this.iddomicilio != null && !this.iddomicilio.equals(other.iddomicilio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.Domicilio[ iddomicilio=" + iddomicilio + " ]";
    }
    
}
