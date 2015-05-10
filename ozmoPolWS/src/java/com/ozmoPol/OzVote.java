/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmoPol;

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
@Table(name = "ozVote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OzVote.findAll", query = "SELECT o FROM OzVote o"),
    @NamedQuery(name = "OzVote.findByPostId", query = "SELECT o FROM OzVote o WHERE o.fkVotePostId = :pkPostId"),
    @NamedQuery(name = "OzVote.findByPkVoteId", query = "SELECT o FROM OzVote o WHERE o.pkVoteId = :pkVoteId"),
    @NamedQuery(name = "OzVote.findByVoteValue", query = "SELECT o FROM OzVote o WHERE o.voteValue = :voteValue")})
public class OzVote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "pk_vote_id")
    private String pkVoteId;
    @Column(name = "vote_value")
    private Boolean voteValue;
    @JoinColumn(name = "fk_vote_user_id", referencedColumnName = "pk_user_id")
    @ManyToOne(optional = false)
    private OzUser fkVoteUserId;
    @JoinColumn(name = "fk_vote_post_id", referencedColumnName = "pk_post_id")
    @ManyToOne(optional = false)
    private OzPost fkVotePostId;

    public OzVote() {
    }

    public OzVote(String pkVoteId) {
        this.pkVoteId = pkVoteId;
    }

    public String getPkVoteId() {
        return pkVoteId;
    }

    public void setPkVoteId(String pkVoteId) {
        this.pkVoteId = pkVoteId;
    }

    public Boolean getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(Boolean voteValue) {
        this.voteValue = voteValue;
    }

    public OzUser getFkVoteUserId() {
        return fkVoteUserId;
    }

    public void setFkVoteUserId(OzUser fkVoteUserId) {
        this.fkVoteUserId = fkVoteUserId;
    }

    public OzPost getFkVotePostId() {
        return fkVotePostId;
    }

    public void setFkVotePostId(OzPost fkVotePostId) {
        this.fkVotePostId = fkVotePostId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkVoteId != null ? pkVoteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OzVote)) {
            return false;
        }
        OzVote other = (OzVote) object;
        if ((this.pkVoteId == null && other.pkVoteId != null) || (this.pkVoteId != null && !this.pkVoteId.equals(other.pkVoteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ozmoPol.OzVote[ pkVoteId=" + pkVoteId + " ]";
    }
    
}
