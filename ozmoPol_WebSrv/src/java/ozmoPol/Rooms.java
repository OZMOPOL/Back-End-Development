/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ozmoPol;

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
import javax.persistence.OneToOne;
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
@Table(name = "Rooms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findByPkRoomId", query = "SELECT r FROM Rooms r WHERE r.pkRoomId = :pkRoomId"),
    @NamedQuery(name = "Rooms.findByRoomTitle", query = "SELECT r FROM Rooms r WHERE r.roomTitle = :roomTitle")})
public class Rooms implements Serializable {
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
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fkuserXroomroomid")
    private Xuserflwroom xuserflwroom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPostRoomId")
    private Collection<Posts> postsCollection;

    public Rooms() {
    }

    public Rooms(String pkRoomId) {
        this.pkRoomId = pkRoomId;
    }

    public Rooms(String pkRoomId, String roomTitle) {
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

    public Xuserflwroom getXuserflwroom() {
        return xuserflwroom;
    }

    public void setXuserflwroom(Xuserflwroom xuserflwroom) {
        this.xuserflwroom = xuserflwroom;
    }

    @XmlTransient
    public Collection<Posts> getPostsCollection() {
        return postsCollection;
    }

    public void setPostsCollection(Collection<Posts> postsCollection) {
        this.postsCollection = postsCollection;
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
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.pkRoomId == null && other.pkRoomId != null) || (this.pkRoomId != null && !this.pkRoomId.equals(other.pkRoomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ozmoPol.Rooms[ pkRoomId=" + pkRoomId + " ]";
    }
    
}
