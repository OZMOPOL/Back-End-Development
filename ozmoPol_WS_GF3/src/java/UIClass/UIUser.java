/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClass;

import com.ozmo.ent.OzUser;
import java.util.List;

/**
 *
 * @author amind
 */
public class UIUser extends OzUser{
    
    private List<UIPost> posts; // includes comments. an String type argument to the setter/getter functions should be enough to differentiate.
    private List<UIPost> voted; // includes upvotes and downvotes on both comments and posts
    private int karma;
    private List<UIUser> flwUser; 
    private List<UIRoom> flwRoom;
    private List<UIUser> followedBy;

    public List<UIRoom> getFlwRoom(UIUser user) {
        return flwRoom;
    }

    public List<UIUser> getFlwUser(UIUser user) {
        return flwUser;
    }

    public List<UIUser> getFollowedBy(UIUser user) {
        return followedBy;
    }

    public int getKarma(UIUser user) {
        return karma;
    }

    public List<UIPost> getPosts(UIUser user) {
        return posts;
    }

    public List<UIPost> getVoted(UIUser user) {
        return voted;
    }

    public void setFlwRoom(List<UIRoom> flwRoom) {
        this.flwRoom = flwRoom;
    }

    public void setFlwUser(List<UIUser> flwUser) {
        this.flwUser = flwUser;
    }

    public void setFollowedBy(List<UIUser> followedBy) {
        this.followedBy = followedBy;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public void setPosts(List<UIPost> posts) {
        this.posts = posts;
    }

    public void setVoted(List<UIPost> voted) {
        this.voted = voted;
    }
    
    
}
