/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmo.ent;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sav
 */
@Entity
@Table(name = "ozRoom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OzRoom.findAll", query = "SELECT o FROM OzRoom o"),
    @NamedQuery(name = "OzRoom.findByPkRoomId", query = "SELECT o FROM OzRoom o WHERE o.pkRoomId = :pkRoomId"),
    @NamedQuery(name = "OzRoom.findByRoomTitle", query = "SELECT o FROM OzRoom o WHERE o.roomTitle = :roomTitle"),
    @NamedQuery(name = "OzRoom.findByRoomStatus", query = "SELECT o FROM OzRoom o WHERE o.roomStatus = :roomStatus")})
public class OzRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "pk_room_id")
    private String pkRoomId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "room_title")
    private String roomTitle;
    @Lob
    @Size(max = 65535)
    @Column(name = "room_desc")
    private String roomDesc;
    @Column(name = "room_status")
    private Boolean roomStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkuserXroomroomid")
    private Collection<Xuserflwroom> xuserflwroomCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPostRoomId")
    private Collection<OzPost> ozPostCollection;

    public OzRoom() {
    }

    public OzRoom(String pkRoomId) {
        this.pkRoomId = pkRoomId;
    }

    public OzRoom(String pkRoomId, String roomTitle) {
        this.pkRoomId = pkRoomId;
        this.roomTitle = roomTitle;
    }

    public String getPkRoomId() {
        return pkRoomId;
    }

    public void setPkRoomId(String pkRoomId) {
        this.pkRoomId = pkRoomId;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public Boolean getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Boolean roomStatus) {
        this.roomStatus = roomStatus;
    }

    @XmlTransient
    public Collection<Xuserflwroom> getXuserflwroomCollection() {
        return xuserflwroomCollection;
    }

    public void setXuserflwroomCollection(Collection<Xuserflwroom> xuserflwroomCollection) {
        this.xuserflwroomCollection = xuserflwroomCollection;
    }

    @XmlTransient
    public Collection<OzPost> getOzPostCollection() {
        return ozPostCollection;
    }

    public void setOzPostCollection(Collection<OzPost> ozPostCollection) {
        this.ozPostCollection = ozPostCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkRoomId != null ? pkRoomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OzRoom)) {
            return false;
        }
        OzRoom other = (OzRoom) object;
        if ((this.pkRoomId == null && other.pkRoomId != null) || (this.pkRoomId != null && !this.pkRoomId.equals(other.pkRoomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ozmo.ent.OzRoom[ pkRoomId=" + pkRoomId + " ]";
    }
    
}
