/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ozmoPol;

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
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByPkUserId", query = "SELECT u FROM Users u WHERE u.pkUserId = :pkUserId"),
    @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName"),
    @NamedQuery(name = "Users.findByUserPass", query = "SELECT u FROM Users u WHERE u.userPass = :userPass"),
    @NamedQuery(name = "Users.findByUserEmail", query = "SELECT u FROM Users u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "Users.findByUserBday", query = "SELECT u FROM Users u WHERE u.userBday = :userBday"),
    @NamedQuery(name = "Users.findByUserStatus", query = "SELECT u FROM Users u WHERE u.userStatus = :userStatus")})
public class Users implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPostUserId")
    private Collection<Posts> postsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkVoteUserId")
    private Collection<Votes> votesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkuserXuserflwduserid")
    private Collection<Xuserflwuser> xuserflwuserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkuserXuserflwruserid")
    private Collection<Xuserflwuser> xuserflwuserCollection1;

    public Users() {
    }

    public Users(String pkUserId) {
        this.pkUserId = pkUserId;
    }

    public Users(String pkUserId, String userName, String userPass, String userEmail, String userStatus) {
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
    public Collection<Posts> getPostsCollection() {
        return postsCollection;
    }

    public void setPostsCollection(Collection<Posts> postsCollection) {
        this.postsCollection = postsCollection;
    }

    @XmlTransient
    public Collection<Votes> getVotesCollection() {
        return votesCollection;
    }

    public void setVotesCollection(Collection<Votes> votesCollection) {
        this.votesCollection = votesCollection;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.pkUserId == null && other.pkUserId != null) || (this.pkUserId != null && !this.pkUserId.equals(other.pkUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ozmoPol.Users[ pkUserId=" + pkUserId + " ]";
    }
    
}
