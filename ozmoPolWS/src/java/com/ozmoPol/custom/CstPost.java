/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmoPol.custom;

import com.ozmoPol.OzPost;
import com.ozmoPol.OzVote;
import java.util.List;

/**
 *
 * @author sav
 */
public class CstPost extends OzPost {

    private List<OzPost> comments;
    private List<OzVote> vote;

    public void setComments(List<OzPost> comments) {
        this.comments = comments;
    }

    public void setVote(List<OzVote> vote) {
        this.vote = vote;
    }

    public List<OzPost> getComments() {
        return comments;
    }

    public List<OzVote> getVote() {
        return vote;
    }

}
