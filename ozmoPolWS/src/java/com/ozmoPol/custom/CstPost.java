/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmoPol.custom;

import com.ozmoPol.OzVote;
import java.util.List;

/**
 *
 * @author sav
 */
public class CstPost extends com.ozmoPol.OzPost {

    private List<com.ozmoPol.OzPost> comments;
    private OzVote vote;
    private int voteCount;

    public void setComments(List<com.ozmoPol.OzPost> comments) {
        this.comments = comments;
    }

    public void setVote(OzVote vote) {
        this.vote = vote;
    }

    public List<com.ozmoPol.OzPost> getComments() {
        return comments;
    }

    public OzVote getVote() {
        return vote;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getVoteCount() {
        return voteCount;
    }

    
}
