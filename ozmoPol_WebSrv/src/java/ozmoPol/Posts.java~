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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "Posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p"),
    @NamedQuery(name = "Posts.findByPkPostId", query = "SELECT p FROM Posts p WHERE p.pkPostId = :pkPostId"),
    @NamedQuery(name = "Posts.findByPostTitle", query = "SELECT p FROM Posts p WHERE p.postTitle = :postTitle"),
    @NamedQuery(name = "Posts.findByPostCDate", query = "SELECT p FROM Posts p WHERE p.postCDate = :postCDate"),
    @NamedQuery(name = "Posts.findByPostEDate", query = "SELECT p FROM Posts p WHERE p.postEDate = :postEDate")})
public class Posts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "pk_post_id")
    private String pkPostId;
    @Size(max = 256)
    @Column(name = "post_title")
    private String postTitle;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "post_content")
    private String postContent;
    @Column(name = "post_c_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postCDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "post_e_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postEDate;
    @OneToMany(mappedBy = "fkPostPrntId")
    private Collection<Posts> postsCollection;
    @JoinColumn(name = "fk_post_prnt_id", referencedColumnName = "pk_post_id")
    @ManyToOne
    private Posts fkPostPrntId;
    @JoinColumn(name = "fk_post_room_id", referencedColumnName = "pk_room_id")
    @ManyToOne(optional = false)
    private Rooms fkPostRoomId;
    @JoinColumn(name = "fk_post_user_id", referencedColumnName = "pk_user_id")
    @ManyToOne(optional = false)
    private Users fkPostUserId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fkVotePostId")
    private Votes votes;

    public Posts() {
    }

    public Posts(String pkPostId) {
        this.pkPostId = pkPostId;
    }

    public Posts(String pkPostId, String postContent, Date postEDate) {
        this.pkPostId = pkPostId;
        this.postContent = postContent;
        this.postEDate = postEDate;
    }

    public String getPkPostId() {
        return pkPostId;
    }

    public void setPkPostId(String pkPostId) {
        this.pkPostId = pkPostId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getPostCDate() {
        return postCDate;
    }

    public void setPostCDate(Date postCDate) {
        this.postCDate = postCDate;
    }

    public Date getPostEDate() {
        return postEDate;
    }

    public void setPostEDate(Date postEDate) {
        this.postEDate = postEDate;
    }

    @XmlTransient
    public Collection<Posts> getPostsCollection() {
        return postsCollection;
    }

    public void setPostsCollection(Collection<Posts> postsCollection) {
        this.postsCollection = postsCollection;
    }

    public Posts getFkPostPrntId() {
        return fkPostPrntId;
    }

    public void setFkPostPrntId(Posts fkPostPrntId) {
        this.fkPostPrntId = fkPostPrntId;
    }

    public Rooms getFkPostRoomId() {
        return fkPostRoomId;
    }

    public void setFkPostRoomId(Rooms fkPostRoomId) {
        this.fkPostRoomId = fkPostRoomId;
    }

    public Users getFkPostUserId() {
        return fkPostUserId;
    }

    public void setFkPostUserId(Users fkPostUserId) {
        this.fkPostUserId = fkPostUserId;
    }

    public Votes getVotes() {
        return votes;
    }

    public void setVotes(Votes votes) {
        this.votes = votes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPostId != null ? pkPostId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.pkPostId == null && other.pkPostId != null) || (this.pkPostId != null && !this.pkPostId.equals(other.pkPostId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ozmoPol.Posts[ pkPostId=" + pkPostId + " ]";
    }
    
}
