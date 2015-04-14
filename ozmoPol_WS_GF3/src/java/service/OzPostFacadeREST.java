/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.ozmo.ent.OzPost;
import com.ozmo.ent.OzUser;
import com.ozmo.ent.OzVote;
import UIClass.UIPost;
import UIClass.UIResult;
import java.util.List;
import java.util.ArrayList;
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
 * @author sav
 */
@Stateless
@Path("com.ozmo.ent.ozpost")
public class OzPostFacadeREST extends AbstractFacade<OzPost> {

    @PersistenceContext(unitName = "ozmoPol_WS_GF3PU")
    private EntityManager em;

    public OzPostFacadeREST() {
        super(OzPost.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(OzPost entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    public void edit(OzPost entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public OzPost find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<OzPost> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<OzPost> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @java.lang.Override
    protected EntityManager getEntityManager() {
        return em;
    }

    //////////////////////
    /// Custom Methods ///
    //////////////////////
    UIPost convertToOzPost(OzPost post) {
        UIPost tmp = new UIPost();
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

    @POST
    @Path("getFrontPage")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public UIResult getFrontPage(OzUser entity) {

        UIResult res = new UIResult();

        try {

            List<UIPost> ozposts = new ArrayList<UIPost>();
            List<OzPost> posts = em.createQuery("SELECT p FROM OzPost p WHERE p.postTitle IS NOT NULL").getResultList();
            for (OzPost post : posts) {
                UIPost uiPost = convertToOzPost(post);
                List<OzVote> votes = em.createNamedQuery("getVotesByPostId").setParameter("postId", post.getPkPostId()).getResultList();
                for (OzVote vote : votes) {
                    if (vote.getFkVoteUserId().getPkUserId().equals(entity.getPkUserId())) {
                        uiPost.setVote(vote);
                    }
                }

                uiPost.setVoteCount(votes.size());
                ozposts.add(uiPost);

            }
            
            res.title = "OK";
            res.body = ozposts;
                
        } catch (Exception e) {
            
            res.title = "NOK";
            res.message = e.getLocalizedMessage();
        }

        return res;
    }
}
