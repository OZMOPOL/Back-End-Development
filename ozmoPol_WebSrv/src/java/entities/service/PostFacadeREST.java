/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

import OzClass.OzPost;
import OzClass.OzResult;
import entities.Post;
import entities.User;
import entities.Vote;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author amind
 */
@Stateless
@Path("entities.post")
public class PostFacadeREST extends AbstractFacade<Post> {
    @PersistenceContext(unitName = "OzmopolPU")
    private EntityManager em;

    public PostFacadeREST() {
        super(Post.class);
    }

    @POST
    @Path("createPost")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public OzResult createPost(Post entity) {
        
        OzResult res=new OzResult();
        
        try {
            super.create(entity);
            res.title="OK";
            
        } catch (ConstraintViolationException e) {
            res.title="NOK";
            res.details=e.getConstraintViolations().toString();
        }
        
        return res;
    }

    @PUT
    @Path("{id}")
    @Consumes({  "application/json"})
    public void edit(@PathParam("id") String id, Post entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({  "application/json"})
    public Post find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({  "application/json"})
    public List<Post> findAll() {
        return super.findAll();
    }
    @POST 
    @Path("getFrontPage") 
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public List<OzPost> getFrontPage(User entity) {  
     List<OzPost> ozposts=new ArrayList<>();
     List<Post> posts= em.createQuery("SELECT p FROM Post p WHERE p.postTitle IS NOT NULL").getResultList();
        for (Post post : posts) {
            OzPost ozPost=convertToOzPost(post);
             List<Vote> votes= em.createNamedQuery("getVotesByPostId").setParameter("postId", post.getPkPostId()).getResultList();
              for (Vote vote : votes) {
                     if (vote.getFkVoteUserId().getPkUserId().equalsIgnoreCase(entity.getPkUserId()) ){
                         ozPost.setVote(vote);
                     }
                 }
             
             ozPost.setVoteCount(votes.size());
             ozposts.add(ozPost);
        }
        return ozposts;
    }   
    @GET 
    @Path("getPostContents/{id}") 
    @Produces({"application/json"}) 

    public OzPost getPostContents(@PathParam("id") String id) {        
//        return (Post) em.createNamedQuery("findByMyID").setParameter("pkPostId", 
//          email).getSingleResult(); 
     Post parentPost = super.find(id);
     
     List<Post> comments= em.createNamedQuery("getPostContents").setParameter("parentId", id).getResultList();
     List<OzPost> oZcomments=new ArrayList<>();
     OzPost parentOzPost = convertToOzPost(parentPost);
      for (Post comment : comments) {
            List<Vote> votes= em.createNamedQuery("getVotesByPostId").setParameter("postId", comment.getPkPostId()).getResultList();
            OzPost ozComment=convertToOzPost(comment);
            ozComment.setVoteCount(votes.size());
            oZcomments.add(ozComment);
         }
     
        parentOzPost.setComments(oZcomments);
        return parentOzPost;
    }  
// 
    
    @GET 
    @Path("getRoomContents/{id}/user/{userId}") 
    @Produces({"application/json"}) 

    public List<OzPost> getRoomContents(@PathParam("id") String id,@PathParam("userId") String userId) { 
         
     List<Post> posts= em.createNamedQuery("getRoomContents").setParameter("roomId", id).getResultList();
     List<OzPost> ozposts=new ArrayList<>();
     
      for (Post post : posts) {
          OzPost ozPost=convertToOzPost(post);
             List<Vote> votes= em.createNamedQuery("getVotesByPostId").setParameter("postId", post.getPkPostId()).getResultList();
              for (Vote vote : votes) {
                     if (vote.getFkVoteUserId().getPkUserId().equalsIgnoreCase(userId) ){
                         ozPost.setVote(vote);
                     }
                 }
             
             ozPost.setVoteCount(votes.size());
             ozposts.add(ozPost);
         }
     
        
        return ozposts;
    }   
    OzPost convertToOzPost(Post post){
            OzPost tmp=new OzPost();
            tmp.setPkPostId(post.getPkPostId());
            tmp.setFkPostPrntId(post.getFkPostPrntId());
            tmp.setFkPostRoomId(post.getFkPostRoomId());
            tmp.setFkPostUserId(post.getFkPostUserId());
            tmp.setPostCDate(post.getPostCDate());
            tmp.setPostContent(post.getPostContent());
            tmp.setPostEDate(post.getPostEDate());
            tmp.setPostTitle(post.getPostTitle());
            return tmp;
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({  "application/json"})
    public List<Post> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
