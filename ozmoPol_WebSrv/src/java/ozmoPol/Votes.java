/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ozmoPol;

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
@Table(name = "Votes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Votes.findAll", query = "SELECT v FROM Votes v"),
    @NamedQuery(name = "Votes.findByPkVoteId", query = "SELECT v FROM Votes v WHERE v.pkVoteId = :pkVoteId"),
    @NamedQuery(name = "Votes.findByVoteValue", query = "SELECT v FROM Votes v WHERE v.voteValue = :voteValue")})
public class Votes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "pk_vote_id")
    private String pkVoteId;
    @Column(name = "vote_value")
    private Boolean voteValue;
    @JoinColumn(name = "fk_vote_post_id", referencedColumnName = "pk_post_id")
    @ManyToOne(optional = false)
    private Posts fkVotePostId;
    @JoinColumn(name = "fk_vote_user_id", referencedColumnName = "pk_user_id")
    @ManyToOne(optional = false)
    private Users fkVoteUserId;

    public Votes() {
    }

    public Votes(String pkVoteId) {
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

    public Posts getFkVotePostId() {
        return fkVotePostId;
    }

    public void setFkVotePostId(Posts fkVotePostId) {
        this.fkVotePostId = fkVotePostId;
    }

    public Users getFkVoteUserId() {
        return fkVoteUserId;
    }

    public void setFkVoteUserId(Users fkVoteUserId) {
        this.fkVoteUserId = fkVoteUserId;
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
        if (!(object instanceof Votes)) {
            return false;
        }
        Votes other = (Votes) object;
        if ((this.pkVoteId == null && other.pkVoteId != null) || (this.pkVoteId != null && !this.pkVoteId.equals(other.pkVoteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ozmoPol.Votes[ pkVoteId=" + pkVoteId + " ]";
    }
    
}
