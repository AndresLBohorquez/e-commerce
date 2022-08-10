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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dark Jack
 */
@Entity
@Table(name = "contacto_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactoProveedor.findAll", query = "SELECT c FROM ContactoProveedor c")})
public class ContactoProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddireccion_proveedor")
    private Integer iddireccionProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombre_direccion")
    private String nombreDireccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "barrio")
    private String barrio;
    @Size(max = 15)
    @Column(name = "telefono1_proveedor")
    private String telefono1Proveedor;
    @Size(max = 15)
    @Column(name = "telefono2_proveedor")
    private String telefono2Proveedor;
    @JoinColumn(name = "proveedor_idproveedor", referencedColumnName = "idproveedor")
    @ManyToOne(fetch = FetchType.LAZY)
    private Proveedor proveedorIdproveedor;

    public ContactoProveedor() {
    }

    public ContactoProveedor(Integer iddireccionProveedor) {
        this.iddireccionProveedor = iddireccionProveedor;
    }

    public ContactoProveedor(Integer iddireccionProveedor, String nombreDireccion, String barrio) {
        this.iddireccionProveedor = iddireccionProveedor;
        this.nombreDireccion = nombreDireccion;
        this.barrio = barrio;
    }

    public Integer getIddireccionProveedor() {
        return iddireccionProveedor;
    }

    public void setIddireccionProveedor(Integer iddireccionProveedor) {
        this.iddireccionProveedor = iddireccionProveedor;
    }

    public String getNombreDireccion() {
        return nombreDireccion;
    }

    public void setNombreDireccion(String nombreDireccion) {
        this.nombreDireccion = nombreDireccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getTelefono1Proveedor() {
        return telefono1Proveedor;
    }

    public void setTelefono1Proveedor(String telefono1Proveedor) {
        this.telefono1Proveedor = telefono1Proveedor;
    }

    public String getTelefono2Proveedor() {
        return telefono2Proveedor;
    }

    public void setTelefono2Proveedor(String telefono2Proveedor) {
        this.telefono2Proveedor = telefono2Proveedor;
    }

    public Proveedor getProveedorIdproveedor() {
        return proveedorIdproveedor;
    }

    public void setProveedorIdproveedor(Proveedor proveedorIdproveedor) {
        this.proveedorIdproveedor = proveedorIdproveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddireccionProveedor != null ? iddireccionProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactoProveedor)) {
            return false;
        }
        ContactoProveedor other = (ContactoProveedor) object;
        if ((this.iddireccionProveedor == null && other.iddireccionProveedor != null) || (this.iddireccionProveedor != null && !this.iddireccionProveedor.equals(other.iddireccionProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.ContactoProveedor[ iddireccionProveedor=" + iddireccionProveedor + " ]";
    }
    
}
