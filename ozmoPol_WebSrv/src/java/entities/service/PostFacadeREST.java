/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

import OzClass.OzPost;
import entities.Post;
import entities.Vote;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    @Override
    @Consumes({  "application/json"})
    public void create(Post entity) {
        super.create(entity);
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
    @GET 
    @Path("getFrontPage") 
    @Produces({"application/json"}) 
    public List<OzPost> getFrontPage() {  
     
     List<OzPost> ozPosts=new ArrayList<>();
        
     List<Post> posts= em.createQuery("SELECT p FROM Post p WHERE p.postTitle IS NOT NULL").getResultList();
        for (Post post : posts) {
             List<Vote> votes= em.createNamedQuery("getVotesByPostId").setParameter("postId", post.getPkPostId()).getResultList();
             OzPost tmpPost=convertToOzPost(post);
             tmpPost.voteCount = votes.size();
             ozPosts.add(tmpPost);
             
        }
    
     
    
        for (int i = 0; i < posts.size(); i++) {
            
        }
        return ozPosts;
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
        for (int i = 0; i < comments.size(); i++) {
            oZcomments.add(convertToOzPost(comments.get(i)));
        }
        parentOzPost.comments= oZcomments;
        return parentOzPost;
    }  
    @GET 
    @Path("getRoomContents/{id}") 
    @Produces({"application/json"}) 

    public List<OzPost> getRoomContents(@PathParam("id") String id) { 
         
     List<Post> posts= em.createNamedQuery("getRoomContents").setParameter("roomId", id).getResultList();
     List<OzPost> oZposts=new ArrayList<>();
        for (int i = 0; i < posts.size(); i++) {
            oZposts.add(convertToOzPost(posts.get(i)));
        }
        
        return oZposts;
    }   
    OzPost convertToOzPost(Post post){
            OzPost tmp=new OzPost();
            tmp.pkPostId=post.getPkPostId();
            tmp.postTitle=post.getPostTitle();
            tmp.postContent=post.getPostContent();
            tmp.postRoomId=post.getFkPostRoomId().getPkRoomId();
            tmp.postUserName=post.getFkPostUserId().getUserName();
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
