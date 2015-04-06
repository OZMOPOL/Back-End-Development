/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
 * @author amind
 */
@Entity
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByPkUserId", query = "SELECT u FROM User u WHERE u.pkUserId = :pkUserId"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName"),
    @NamedQuery(name = "User.findByUserPass", query = "SELECT u FROM User u WHERE u.userPass = :userPass"),
    @NamedQuery(name = "User.findByUserEmail", query = "SELECT u FROM User u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "User.findByUserBday", query = "SELECT u FROM User u WHERE u.userBday = :userBday"),
    @NamedQuery(name = "User.findByUserStatus", query = "SELECT u FROM User u WHERE u.userStatus = :userStatus"),
    @NamedQuery(name = "checkLogin", query = "SELECT u FROM User u WHERE u.userName = :userName AND u.userPass = :userPass")})
public class User implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "user_status")
    private String userStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkuserXroomuserid")
    private Collection<Xuserflwroom> xuserflwroomCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkVoteUserId")
    private Collection<Vote> voteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPostUserId")
    private Collection<Post> postCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkuserXuserflwruserid")
    private Collection<Xuserflwuser> xuserflwuserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkuserXuserflwduserid")
    private Collection<Xuserflwuser> xuserflwuserCollection1;

    public User() {
    }

    public User(String pkUserId) {
        this.pkUserId = pkUserId;
    }

    public User(String pkUserId, String userName, String userPass, String userEmail, String userStatus) {
        this.pkUserId = pkUserId;
        this.userName = userName;
        this.userPass = userPass;
        this.userEmail = userEmail;
        this.userStatus = userStatus;
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @XmlTransient
    public Collection<Xuserflwroom> getXuserflwroomCollection() {
        return xuserflwroomCollection;
    }

    public void setXuserflwroomCollection(Collection<Xuserflwroom> xuserflwroomCollection) {
        this.xuserflwroomCollection = xuserflwroomCollection;
    }

    @XmlTransient
    public Collection<Vote> getVoteCollection() {
        return voteCollection;
    }

    public void setVoteCollection(Collection<Vote> voteCollection) {
        this.voteCollection = voteCollection;
    }

    @XmlTransient
    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkUserId != null ? pkUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.pkUserId == null && other.pkUserId != null) || (this.pkUserId != null && !this.pkUserId.equals(other.pkUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.User[ pkUserId=" + pkUserId + " ]";
    }
    
}
