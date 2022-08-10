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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "correo_smtp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CorreoSmtp.findAll", query = "SELECT c FROM CorreoSmtp c")})
public class CorreoSmtp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcorreo_smtp")
    private Integer idcorreoSmtp;
    @Size(max = 45)
    @Column(name = "correo")
    private String correo;
    @Size(max = 45)
    @Column(name = "password")
    private String password;
    @Size(max = 45)
    @Column(name = "smtp")
    private String smtp;
    @Column(name = "puerto")
    private Integer puerto;

    public CorreoSmtp() {
    }

    public CorreoSmtp(Integer idcorreoSmtp) {
        this.idcorreoSmtp = idcorreoSmtp;
    }

    public Integer getIdcorreoSmtp() {
        return idcorreoSmtp;
    }

    public void setIdcorreoSmtp(Integer idcorreoSmtp) {
        this.idcorreoSmtp = idcorreoSmtp;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcorreoSmtp != null ? idcorreoSmtp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorreoSmtp)) {
            return false;
        }
        CorreoSmtp other = (CorreoSmtp) object;
        if ((this.idcorreoSmtp == null && other.idcorreoSmtp != null) || (this.idcorreoSmtp != null && !this.idcorreoSmtp.equals(other.idcorreoSmtp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.app.entity.CorreoSmtp[ idcorreoSmtp=" + idcorreoSmtp + " ]";
    }
    
}
