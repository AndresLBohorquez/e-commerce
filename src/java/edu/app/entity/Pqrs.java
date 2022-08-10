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
import javax.persistence.Lob;
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
@Table(name = "pqrs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pqrs.findAll", query = "SELECT p FROM Pqrs p")})
public class Pqrs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpqrs")
    private Integer idpqrs;
    @Size(max = 45)
    @Column(name = "fecha_registro_pqrs")
    private String fechaRegistroPqrs;
    @Size(max = 45)
    @Column(name = "fecha_actualizacion_pqrs")
    private String fechaActualizacionPqrs;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pqrsIdpqrs", fetch = FetchType.LAZY)
    private Collection<Message> messageCollection;
    @JoinColumn(name = "estado_pqrs_idestado_pqrs", referencedColumnName = "idestado_pqrs")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstadoPqrs estadoPqrsIdestadoPqrs;
    @JoinColumn(name = "prioridad_idprioridad", referencedColumnName = "idprioridad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Prioridad prioridadIdprioridad;
    @JoinColumn(name = "tipo_pqrs_idtipo_pqrs", referencedColumnName = "idtipo_pqrs")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoPqrs tipoPqrsIdtipoPqrs;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioIdusuario;

    public Pqrs() {
    }

    public Pqrs(Integer idpqrs) {
        this.idpqrs = idpqrs;
    }

    public Pqrs(Integer idpqrs, String descripcion) {
        this.idpqrs = idpqrs;
        this.descripcion = descripcion;
    }

    public Integer getIdpqrs() {
        return idpqrs;
    }

    public void setIdpqrs(Integer idpqrs) {
        this.idpqrs = idpqrs;
    }

    public String getFechaRegistroPqrs() {
        return fechaRegistroPqrs;
    }

    public void setFechaRegistroPqrs(String fechaRegistroPqrs) {
        this.fechaRegistroPqrs = fechaRegistroPqrs;
    }

    public String getFechaActualizacionPqrs() {
        return fechaActualizacionPqrs;
    }

    public void setFechaActualizacionPqrs(String fechaActualizacionPqrs) {
        this.fechaActualizacionPqrs = fechaActualizacionPqrs;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    public EstadoPqrs getEstadoPqrsIdestadoPqrs() {
        return estadoPqrsIdestadoPqrs;
    }

    public void setEstadoPqrsIdestadoPqrs(EstadoPqrs estadoPqrsIdestadoPqrs) {
        this.estadoPqrsIdestadoPqrs = estadoPqrsIdestadoPqrs;
    }

    public Prioridad getPrioridadIdprioridad() {
        return prioridadIdprioridad;
    }

    public void setPrioridadIdprioridad(Prioridad prioridadIdprioridad) {
        this.prioridadIdprioridad = prioridadIdprioridad;
    }

    public TipoPqrs getTipoPqrsIdtipoPqrs() {
        return tipoPqrsIdtipoPqrs;
    }

    public void setTipoPqrsIdtipoPqrs(TipoPqrs tipoPqrsIdtipoPqrs) {
        this.tipoPqrsIdtipoPqrs = tipoPqrsIdtipoPqrs;
    }

    public Usuario getUsuarioIdusuario() {
        return usuarioIdusuario;
    }

    public void setUsuarioIdusuario(Usuario usuarioIdusuario) {
        this.usuarioIdusuario = usuarioIdusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpqrs != null ? idpqrs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pqrs)) {
            return false;
        }
        Pqrs other = (Pqrs) object;
        if ((this.idpqrs == null && other.idpqrs != null) || (this.idpqrs != null && !this.idpqrs.equals(other.idpqrs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.Pqrs[ idpqrs=" + idpqrs + " ]";
    }
    
}
