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
@Table(name = "estado_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoUsuario.findAll", query = "SELECT e FROM EstadoUsuario e")})
public class EstadoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestado_usuario")
    private Integer idestadoUsuario;
    @Size(max = 45)
    @Column(name = "nombre_estado")
    private String nombreEstado;
    @OneToMany(mappedBy = "estadoUsuarioIdestadoUsuario", fetch = FetchType.LAZY)
    private Collection<Usuario> usuarioCollection;

    public EstadoUsuario() {
    }

    public EstadoUsuario(Integer idestadoUsuario) {
        this.idestadoUsuario = idestadoUsuario;
    }

    public Integer getIdestadoUsuario() {
        return idestadoUsuario;
    }

    public void setIdestadoUsuario(Integer idestadoUsuario) {
        this.idestadoUsuario = idestadoUsuario;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestadoUsuario != null ? idestadoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoUsuario)) {
            return false;
        }
        EstadoUsuario other = (EstadoUsuario) object;
        if ((this.idestadoUsuario == null && other.idestadoUsuario != null) || (this.idestadoUsuario != null && !this.idestadoUsuario.equals(other.idestadoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.EstadoUsuario[ idestadoUsuario=" + idestadoUsuario + " ]";
    }
    
}
