/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OzClass;

import com.ozmo.ent.Post;
import com.ozmo.ent.Vote;
import java.util.List;

/**
 *
 * @author amind
 */
public class OzPost extends Post{
    private List<OzPost> comments;
    private  int voteCount;
    public Vote vote;
    
    public List<OzPost> getComments(){
        return this.comments;
    }
    
    public void setComments(List<OzPost> comments){
        this.comments=comments;
    }
    
    public int getVoteCount(){
        return voteCount;
    }
    public void setVoteCount(int voteCount){
        this.voteCount=voteCount;
    }
    
    public Vote getVote(){
        return this.vote;
    }
    public void setVote(Vote vote){
        this.vote=vote;
    }
   
}
