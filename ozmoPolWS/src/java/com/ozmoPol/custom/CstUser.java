/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmoPol.custom;

import com.ozmoPol.OzPost;
import com.ozmoPol.OzRoom;
import com.ozmoPol.OzUser;
import com.ozmoPol.OzVote;
import java.util.List;

/**
 *
 * @author sav
 */
public class CstUser extends OzUser{
    
    private List<OzPost> posts;
    private List<OzPost> comments;
    private List<OzVote> votes;
    private List<OzUser> flwdUsers;
    private List<OzRoom> flwdRooms;

    public void setPosts(List<OzPost> posts) {
        this.posts = posts;
    }

    public void setComments(List<OzPost> comments) {
        this.comments = comments;
    }

    public void setVotes(List<OzVote> votes) {
        this.votes = votes;
    }

    public void setFlwdUsers(List<OzUser> flwdUsers) {
        this.flwdUsers = flwdUsers;
    }

    public void setFlwdRooms(List<OzRoom> flwdRooms) {
        this.flwdRooms = flwdRooms;
    }

    public List<OzPost> getPosts() {
        return posts;
    }

    public List<OzPost> getComments() {
        return comments;
    }

    public List<OzVote> getVotes() {
        return votes;
    }

    public List<OzUser> getFlwdUsers() {
        return flwdUsers;
    }

    public List<OzRoom> getFlwdRooms() {
        return flwdRooms;
    }
    
}
