/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmoPol.custom;

import com.ozmoPol.OzPost;
import com.ozmoPol.OzRoom;
import com.ozmoPol.OzUser;
import java.util.List;

/**
 *
 * @author sav
 */

public class CstResult {
    private String title;
    private String message;
    private List<CstUser> users;
    private List<CstPost> posts;
    private List<CstRoom> rooms;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsers(List<CstUser> users) {
        this.users = users;
    }

    public void setPosts(List<CstPost> posts) {
        this.posts = posts;
    }

    public void setRooms(List<CstRoom> rooms) {
        this.rooms = rooms;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public List<CstUser> getUsers() {
        return users;
    }

    public List<CstPost> getPosts() {
        return posts;
    }

    public List<CstRoom> getRooms() {
        return rooms;
    }
    
    
}
