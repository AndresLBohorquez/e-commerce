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
@Table(name = "tipo_pqrs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPqrs.findAll", query = "SELECT t FROM TipoPqrs t")})
public class TipoPqrs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipo_pqrs")
    private Integer idtipoPqrs;
    @Size(max = 45)
    @Column(name = "nombre_tipo_pqrs")
    private String nombreTipoPqrs;
    @OneToMany(mappedBy = "tipoPqrsIdtipoPqrs", fetch = FetchType.LAZY)
    private Collection<Pqrs> pqrsCollection;

    public TipoPqrs() {
    }

    public TipoPqrs(Integer idtipoPqrs) {
        this.idtipoPqrs = idtipoPqrs;
    }

    public Integer getIdtipoPqrs() {
        return idtipoPqrs;
    }

    public void setIdtipoPqrs(Integer idtipoPqrs) {
        this.idtipoPqrs = idtipoPqrs;
    }

    public String getNombreTipoPqrs() {
        return nombreTipoPqrs;
    }

    public void setNombreTipoPqrs(String nombreTipoPqrs) {
        this.nombreTipoPqrs = nombreTipoPqrs;
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
        hash += (idtipoPqrs != null ? idtipoPqrs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPqrs)) {
            return false;
        }
        TipoPqrs other = (TipoPqrs) object;
        if ((this.idtipoPqrs == null && other.idtipoPqrs != null) || (this.idtipoPqrs != null && !this.idtipoPqrs.equals(other.idtipoPqrs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.TipoPqrs[ idtipoPqrs=" + idtipoPqrs + " ]";
    }
    
}
