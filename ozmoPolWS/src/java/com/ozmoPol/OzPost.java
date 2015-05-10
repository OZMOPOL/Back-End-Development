/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmoPol;

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
@Table(name = "ozPost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OzPost.findAll", query = "SELECT o FROM OzPost o"),
    @NamedQuery(name = "OzPost.findAllPosts", query = "SELECT o FROM OzPost o WHERE o.fkPostPrntId IS NULL"),
    @NamedQuery(name = "OzPost.findByPkPostId", query = "SELECT o FROM OzPost o WHERE o.pkPostId = :pkPostId"),
    @NamedQuery(name = "OzPost.findByPostTitle", query = "SELECT o FROM OzPost o WHERE o.postTitle = :postTitle"),
    @NamedQuery(name = "OzPost.findByPostCDate", query = "SELECT o FROM OzPost o WHERE o.postCDate = :postCDate"),
    @NamedQuery(name = "OzPost.findByPostEDate", query = "SELECT o FROM OzPost o WHERE o.postEDate = :postEDate"),
    @NamedQuery(name = "OzPost.findByPostStatus", query = "SELECT o FROM OzPost o WHERE o.postStatus = :postStatus")})
public class OzPost implements Serializable {

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
    @Column(name = "post_status")
    private Boolean postStatus;
    @JoinColumn(name = "fk_post_user_id", referencedColumnName = "pk_user_id")
    @ManyToOne(optional = false)
    private OzUser fkPostUserId;
    @JoinColumn(name = "fk_post_room_id", referencedColumnName = "pk_room_id")
    @ManyToOne(optional = false)
    private OzRoom fkPostRoomId;
    @OneToMany(mappedBy = "fkPostPrntId")
    private Collection<OzPost> ozPostCollection;
    @JoinColumn(name = "fk_post_prnt_id", referencedColumnName = "pk_post_id")
    @ManyToOne
    private OzPost fkPostPrntId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkVotePostId")
    private Collection<OzVote> ozVoteCollection;

    public OzPost() {
    }

    public OzPost(String pkPostId) {
        this.pkPostId = pkPostId;
    }

    public OzPost(String pkPostId, String postContent, Date postEDate) {
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

    public Boolean getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Boolean postStatus) {
        this.postStatus = postStatus;
    }

    public OzUser getFkPostUserId() {
        return fkPostUserId;
    }

    public void setFkPostUserId(OzUser fkPostUserId) {
        this.fkPostUserId = fkPostUserId;
    }

    public OzRoom getFkPostRoomId() {
        return fkPostRoomId;
    }

    public void setFkPostRoomId(OzRoom fkPostRoomId) {
        this.fkPostRoomId = fkPostRoomId;
    }

    @XmlTransient
    public Collection<OzPost> getOzPostCollection() {
        return ozPostCollection;
    }

    public void setOzPostCollection(Collection<OzPost> ozPostCollection) {
        this.ozPostCollection = ozPostCollection;
    }

    public OzPost getFkPostPrntId() {
        return fkPostPrntId;
    }

    public void setFkPostPrntId(OzPost fkPostPrntId) {
        this.fkPostPrntId = fkPostPrntId;
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
        hash += (pkPostId != null ? pkPostId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OzPost)) {
            return false;
        }
        OzPost other = (OzPost) object;
        if ((this.pkPostId == null && other.pkPostId != null) || (this.pkPostId != null && !this.pkPostId.equals(other.pkPostId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ozmoPol.OzPost[ pkPostId=" + pkPostId + " ]";
    }

    public OzPost cstConverter() {

        OzPost tmp = new OzPost();
        
        tmp.setPkPostId(this.getPkPostId());
        tmp.setFkPostPrntId(this.getFkPostPrntId());
        tmp.setFkPostRoomId(this.getFkPostRoomId());
        tmp.setFkPostUserId(this.getFkPostUserId());
        tmp.setPostCDate(this.getPostCDate());
        tmp.setPostContent(this.getPostContent());
        tmp.setPostEDate(this.getPostEDate());
        tmp.setPostTitle(this.getPostTitle());
        
        return tmp;
    }

}
