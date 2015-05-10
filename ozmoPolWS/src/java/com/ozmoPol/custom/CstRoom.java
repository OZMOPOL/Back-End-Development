/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmoPol.custom;
import com.ozmoPol.OzPost;
import java.util.List;

/**
 *
 * @author sav
 */
public class CstRoom extends com.ozmoPol.OzRoom{
    
    private List<CstPost> posts;
    private Boolean follows;

    public void setPosts(List<CstPost> posts) {
        this.posts = posts;
    }

    public List<CstPost> getPosts() {
        return posts;
    }

    public void setFollows(Boolean follows) {
        this.follows = follows;
    }

    public Boolean getFollows() {
        return follows;
    }
    
}
