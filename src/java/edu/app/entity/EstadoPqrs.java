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
@Table(name = "estado_pqrs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoPqrs.findAll", query = "SELECT e FROM EstadoPqrs e")})
public class EstadoPqrs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestado_pqrs")
    private Integer idestadoPqrs;
    @Size(max = 45)
    @Column(name = "nombre_estado_pqrs")
    private String nombreEstadoPqrs;
    @OneToMany(mappedBy = "estadoPqrsIdestadoPqrs", fetch = FetchType.LAZY)
    private Collection<Pqrs> pqrsCollection;

    public EstadoPqrs() {
    }

    public EstadoPqrs(Integer idestadoPqrs) {
        this.idestadoPqrs = idestadoPqrs;
    }

    public Integer getIdestadoPqrs() {
        return idestadoPqrs;
    }

    public void setIdestadoPqrs(Integer idestadoPqrs) {
        this.idestadoPqrs = idestadoPqrs;
    }

    public String getNombreEstadoPqrs() {
        return nombreEstadoPqrs;
    }

    public void setNombreEstadoPqrs(String nombreEstadoPqrs) {
        this.nombreEstadoPqrs = nombreEstadoPqrs;
    }

    @XmlTransient
    public Collection<Pqrs> getPqrsCollection() {
        return pqrsCollection;
    }

    public void setPqrsCollection(Collection<Pqrs> pqrsCollection) {
        this.pqrsCollection = pqrsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestadoPqrs != null ? idestadoPqrs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoPqrs)) {
            return false;
        }
        EstadoPqrs other = (EstadoPqrs) object;
        if ((this.idestadoPqrs == null && other.idestadoPqrs != null) || (this.idestadoPqrs != null && !this.idestadoPqrs.equals(other.idestadoPqrs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.EstadoPqrs[ idestadoPqrs=" + idestadoPqrs + " ]";
    }
    
}
