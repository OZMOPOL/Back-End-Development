/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClass;

import com.ozmo.ent.OzPost;
import com.ozmo.ent.OzVote;
import java.util.List;

/**
 *
 * @author amind
 */
public class UIPost extends OzPost{
    private List<UIPost> comments;
    private  int voteCount;
    public OzVote vote;
    
    public List<UIPost> getComments(){
        return this.comments;
    }
    
    public void setComments(List<UIPost> comments){
        this.comments=comments;
    }
    
    public int getVoteCount(){
        return voteCount;
    }
    public void setVoteCount(int voteCount){
        this.voteCount=voteCount;
    }
    
    public OzVote getVote(){
        return this.vote;
    }
    public void setVote(OzVote vote){
        this.vote=vote;
    }
    
    
   
    
    
     
    
}
