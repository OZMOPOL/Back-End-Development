/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmoPol.custom;
import com.ozmoPol.OzPost;
import com.ozmoPol.OzRoom;
import java.util.List;

/**
 *
 * @author sav
 */
public class CstRoom extends OzRoom{
    
    private List<OzPost> posts;

    public void setPosts(List<OzPost> posts) {
        this.posts = posts;
    }

    public List<OzPost> getPosts() {
        return posts;
    }
    
}
