/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmo.ent;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sav
 */
@Entity
@Table(name = "ozUser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OzUser.findAll", query = "SELECT o FROM OzUser o"),
    @NamedQuery(name = "OzUser.findByPkUserId", query = "SELECT o FROM OzUser o WHERE o.pkUserId = :pkUserId"),
    @NamedQuery(name = "OzUser.findByUserName", query = "SELECT o FROM OzUser o WHERE o.userName = :userName"),
    @NamedQuery(name = "OzUser.findByUserPass", query = "SELECT o FROM OzUser o WHERE o.userPass = :userPass"),
    @NamedQuery(name = "OzUser.findByUserEmail", query = "SELECT o FROM OzUser o WHERE o.userEmail = :userEmail"),
    @NamedQuery(name = "OzUser.findByUserBday", query = "SELECT o FROM OzUser o WHERE o.userBday = :userBday"),
    @NamedQuery(name = "OzUser.findByUserStatus", query = "SELECT o FROM OzUser o WHERE o.userStatus = :userStatus"),
    @NamedQuery(name = "OzUser.findByUseractHash", query = "SELECT o FROM OzUser o WHERE o.useractHash = :useractHash"),
    @NamedQuery(name = "User.checkAuthStatus", query = "SELECT u FROM OzUser u WHERE u.userName = :userName AND u.userPass = :userPass")    
})
public class OzUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "pk_user_id")
    private String pkUserId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "user_pass")
    private String userPass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "user_bday")
    @Temporal(TemporalType.DATE)
    private Date userBday;
    @Column(name = "user_status")
    private Boolean userStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "user_actHash")
    private String useractHash;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkuserXroomuserid")
    private Collection<Xuserflwroom> xuserflwroomCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPostUserId")
    private Collection<OzPost> ozPostCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkuserXuserflwduserid")
    private Collection<Xuserflwuser> xuserflwuserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkuserXuserflwruserid")
    private Collection<Xuserflwuser> xuserflwuserCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkVoteUserId")
    private Collection<OzVote> ozVoteCollection;

    public OzUser() {
    }

    public OzUser(String pkUserId) {
        this.pkUserId = pkUserId;
    }

    public OzUser(String pkUserId, String userName, String userPass, String userEmail, String useractHash) {
        this.pkUserId = pkUserId;
        this.userName = userName;
        this.userPass = userPass;
        this.userEmail = userEmail;
        this.useractHash = useractHash;
    }

    public String getPkUserId() {
        return pkUserId;
    }

    public void setPkUserId(String pkUserId) {
        this.pkUserId = pkUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserBday() {
        return userBday;
    }

    public void setUserBday(Date userBday) {
        this.userBday = userBday;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public String getUseractHash() {
        return useractHash;
    }

    public void setUseractHash(String useractHash) {
        this.useractHash = useractHash;
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

    @XmlTransient
    public Collection<Xuserflwuser> getXuserflwuserCollection() {
        return xuserflwuserCollection;
    }

    public void setXuserflwuserCollection(Collection<Xuserflwuser> xuserflwuserCollection) {
        this.xuserflwuserCollection = xuserflwuserCollection;
    }

    @XmlTransient
    public Collection<Xuserflwuser> getXuserflwuserCollection1() {
        return xuserflwuserCollection1;
    }

    public void setXuserflwuserCollection1(Collection<Xuserflwuser> xuserflwuserCollection1) {
        this.xuserflwuserCollection1 = xuserflwuserCollection1;
    }

    @XmlTransient
    public Collection<OzVote> getOzVoteCollection() {
        return ozVoteCollection;
    }

    public void setOzVoteCollection(Collection<OzVote> ozVoteCollection) {
        this.ozVoteCollection = ozVoteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkUserId != null ? pkUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OzUser)) {
            return false;
        }
        OzUser other = (OzUser) object;
        if ((this.pkUserId == null && other.pkUserId != null) || (this.pkUserId != null && !this.pkUserId.equals(other.pkUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ozmo.ent.OzUser[ pkUserId=" + pkUserId + " ]";
    }
    
}
