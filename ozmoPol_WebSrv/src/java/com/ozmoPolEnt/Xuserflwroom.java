/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmoPolEnt;

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
@Table(name = "X_user_flw_room")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Xuserflwroom.findAll", query = "SELECT x FROM Xuserflwroom x"),
    @NamedQuery(name = "Xuserflwroom.findByPkuserXroomid", query = "SELECT x FROM Xuserflwroom x WHERE x.pkuserXroomid = :pkuserXroomid")})
public class Xuserflwroom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "pk_userXroom_id")
    private String pkuserXroomid;
    @JoinColumn(name = "fk_userXroom_room_id", referencedColumnName = "pk_room_id")
    @ManyToOne(optional = false)
    private Room fkuserXroomroomid;
    @JoinColumn(name = "fk_userXroom_user_id", referencedColumnName = "pk_user_id")
    @ManyToOne(optional = false)
    private User fkuserXroomuserid;

    public Xuserflwroom() {
    }

    public Xuserflwroom(String pkuserXroomid) {
        this.pkuserXroomid = pkuserXroomid;
    }

    public String getPkuserXroomid() {
        return pkuserXroomid;
    }

    public void setPkuserXroomid(String pkuserXroomid) {
        this.pkuserXroomid = pkuserXroomid;
    }

    public Room getFkuserXroomroomid() {
        return fkuserXroomroomid;
    }

    public void setFkuserXroomroomid(Room fkuserXroomroomid) {
        this.fkuserXroomroomid = fkuserXroomroomid;
    }

    public User getFkuserXroomuserid() {
        return fkuserXroomuserid;
    }

    public void setFkuserXroomuserid(User fkuserXroomuserid) {
        this.fkuserXroomuserid = fkuserXroomuserid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkuserXroomid != null ? pkuserXroomid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Xuserflwroom)) {
            return false;
        }
        Xuserflwroom other = (Xuserflwroom) object;
        if ((this.pkuserXroomid == null && other.pkuserXroomid != null) || (this.pkuserXroomid != null && !this.pkuserXroomid.equals(other.pkuserXroomid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ozmoPolEnt.Xuserflwroom[ pkuserXroomid=" + pkuserXroomid + " ]";
    }
    
}
