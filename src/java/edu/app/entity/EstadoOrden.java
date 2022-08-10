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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dark Jack
 */
@Entity
@Table(name = "estado_orden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoOrden.findAll", query = "SELECT e FROM EstadoOrden e")})
public class EstadoOrden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestado_orden")
    private Integer idestadoOrden;
    @Size(max = 45)
    @Column(name = "nombre_estado_orden")
    private String nombreEstadoOrden;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoOrdenIdestadoOrden", fetch = FetchType.LAZY)
    private Collection<Orden> ordenCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoOrdenIdestadoOrden", fetch = FetchType.LAZY)
    private Collection<HoraOrden> horaOrdenCollection;

    public EstadoOrden() {
    }

    public EstadoOrden(Integer idestadoOrden) {
        this.idestadoOrden = idestadoOrden;
    }

    public Integer getIdestadoOrden() {
        return idestadoOrden;
    }

    public void setIdestadoOrden(Integer idestadoOrden) {
        this.idestadoOrden = idestadoOrden;
    }

    public String getNombreEstadoOrden() {
        return nombreEstadoOrden;
    }

    public void setNombreEstadoOrden(String nombreEstadoOrden) {
        this.nombreEstadoOrden = nombreEstadoOrden;
    }

    @XmlTransient
    public Collection<Orden> getOrdenCollection() {
        return ordenCollection;
    }

    public void setOrdenCollection(Collection<Orden> ordenCollection) {
        this.ordenCollection = ordenCollection;
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
        hash += (idestadoOrden != null ? idestadoOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoOrden)) {
            return false;
        }
        EstadoOrden other = (EstadoOrden) object;
        if ((this.idestadoOrden == null && other.idestadoOrden != null) || (this.idestadoOrden != null && !this.idestadoOrden.equals(other.idestadoOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.EstadoOrden[ idestadoOrden=" + idestadoOrden + " ]";
    }
    
}
