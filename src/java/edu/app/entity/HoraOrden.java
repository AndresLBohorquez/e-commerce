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
@Table(name = "hora_orden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HoraOrden.findAll", query = "SELECT h FROM HoraOrden h")})
public class HoraOrden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "fecha")
    private String fecha;
    @Size(max = 45)
    @Column(name = "hora")
    private String hora;
    @JoinColumn(name = "estado_orden_idestado_orden", referencedColumnName = "idestado_orden")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoOrden estadoOrdenIdestadoOrden;
    @JoinColumn(name = "orden_idorden", referencedColumnName = "idorden")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Orden ordenIdorden;

    public HoraOrden() {
    }

    public HoraOrden(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public EstadoOrden getEstadoOrdenIdestadoOrden() {
        return estadoOrdenIdestadoOrden;
    }

    public void setEstadoOrdenIdestadoOrden(EstadoOrden estadoOrdenIdestadoOrden) {
        this.estadoOrdenIdestadoOrden = estadoOrdenIdestadoOrden;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HoraOrden)) {
            return false;
        }
        HoraOrden other = (HoraOrden) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.HoraOrden[ id=" + id + " ]";
    }
    
}
