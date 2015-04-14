/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmo.ent;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author sav
 */
@Entity
@Table(name = "X_user_flw_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Xuserflwuser.findAll", query = "SELECT x FROM Xuserflwuser x"),
    @NamedQuery(name = "Xuserflwuser.findFlwdUsersByUserId", query = "SELECT r FROM Xuserflwuser x INNER JOIN x.fkuserXuserflwduserid r WHERE x.fkuserXuserflwruserid= :userId"),
    @NamedQuery(name = "Xuserflwuser.findByPkuserXuserid", query = "SELECT x FROM Xuserflwuser x WHERE x.pkuserXuserid = :pkuserXuserid")})
public class Xuserflwuser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "pk_userXuser_id")
    private String pkuserXuserid;
    @JoinColumn(name = "fk_userXuser_flwd_user_id", referencedColumnName = "pk_user_id")
    @ManyToOne(optional = false)
    private OzUser fkuserXuserflwduserid;
    @JoinColumn(name = "fk_userXuser_flwr_user_id", referencedColumnName = "pk_user_id")
    @ManyToOne(optional = false)
    private OzUser fkuserXuserflwruserid;

    public Xuserflwuser() {
    }

    public Xuserflwuser(String pkuserXuserid) {
        this.pkuserXuserid = pkuserXuserid;
    }

    public String getPkuserXuserid() {
        return pkuserXuserid;
    }

    public void setPkuserXuserid(String pkuserXuserid) {
        this.pkuserXuserid = pkuserXuserid;
    }

    public OzUser getFkuserXuserflwduserid() {
        return fkuserXuserflwduserid;
    }

    public void setFkuserXuserflwduserid(OzUser fkuserXuserflwduserid) {
        this.fkuserXuserflwduserid = fkuserXuserflwduserid;
    }

    public OzUser getFkuserXuserflwruserid() {
        return fkuserXuserflwruserid;
    }

    public void setFkuserXuserflwruserid(OzUser fkuserXuserflwruserid) {
        this.fkuserXuserflwruserid = fkuserXuserflwruserid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkuserXuserid != null ? pkuserXuserid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Xuserflwuser)) {
            return false;
        }
        Xuserflwuser other = (Xuserflwuser) object;
        if ((this.pkuserXuserid == null && other.pkuserXuserid != null) || (this.pkuserXuserid != null && !this.pkuserXuserid.equals(other.pkuserXuserid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ozmo.ent.Xuserflwuser[ pkuserXuserid=" + pkuserXuserid + " ]";
    }
    
}
