/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "estado_domicilio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoDomicilio.findAll", query = "SELECT e FROM EstadoDomicilio e")})
public class EstadoDomicilio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestado_domicilio")
    private Integer idestadoDomicilio;
    @Size(max = 45)
    @Column(name = "nombre_estado_domicilio")
    private String nombreEstadoDomicilio;
    @OneToMany(mappedBy = "estadoDomicilioIdestadoDomicilio", fetch = FetchType.LAZY)
    private Collection<Domicilio> domicilioCollection;

    public EstadoDomicilio() {
    }

    public EstadoDomicilio(Integer idestadoDomicilio) {
        this.idestadoDomicilio = idestadoDomicilio;
    }

    public Integer getIdestadoDomicilio() {
        return idestadoDomicilio;
    }

    public void setIdestadoDomicilio(Integer idestadoDomicilio) {
        this.idestadoDomicilio = idestadoDomicilio;
    }

    public String getNombreEstadoDomicilio() {
        return nombreEstadoDomicilio;
    }

    public void setNombreEstadoDomicilio(String nombreEstadoDomicilio) {
        this.nombreEstadoDomicilio = nombreEstadoDomicilio;
    }

    @XmlTransient
    public Collection<Domicilio> getDomicilioCollection() {
        return domicilioCollection;
    }

    public void setDomicilioCollection(Collection<Domicilio> domicilioCollection) {
        this.domicilioCollection = domicilioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestadoDomicilio != null ? idestadoDomicilio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoDomicilio)) {
            return false;
        }
        EstadoDomicilio other = (EstadoDomicilio) object;
        if ((this.idestadoDomicilio == null && other.idestadoDomicilio != null) || (this.idestadoDomicilio != null && !this.idestadoDomicilio.equals(other.idestadoDomicilio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.EstadoDomicilio[ idestadoDomicilio=" + idestadoDomicilio + " ]";
    }
    
}
