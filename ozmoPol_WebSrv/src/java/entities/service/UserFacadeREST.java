/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

import OzClass.OzResult;
import entities.Post;
import entities.User;
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
@Path("entities.user")
public class UserFacadeREST extends AbstractFacade<User> {
    @PersistenceContext(unitName = "OzmopolPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Override
    @Consumes({  "application/json"})
    public void create(User entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({  "application/json"})
    public void edit(@PathParam("id") String id, User entity) {
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
    public User find(@PathParam("id") String id) {
        return super.find(id);
    }
    
     @GET
    @Path("getUserByUserName/{userName}")
    @Produces({  "application/json"})
    public User getUserByUserName(@PathParam("userName") String userName) {
        User user=(User) em.createNamedQuery("User.findByUserName").setParameter("userName", userName).getSingleResult();
        return user;
    }

    @GET
    @Path("checkLogin/{user}/pass/{pass}")
    @Produces({  "application/json"})
    
    public OzResult checkLogin(@PathParam("user") String user,@PathParam("pass") String pass) {
        
        List<User> users= em.createNamedQuery("checkLogin").setParameter("userName", user).setParameter("userPass", pass).getResultList();
        OzResult res=new OzResult();
        if (users.size()==0) {
            res.title="NOK";
                
        }else{
            res.title="OK";
        }
        return res;
    }
    
    @POST
    @Path("signUp")
    @Produces({"application/json"})
    @Consumes({  "application/json"})
    public OzResult signUp(User entity) {
        OzResult res=new OzResult();
        try {
             super.create(entity);
              res.title="OK";
        } catch (Exception e) {
            res.title="NOK";
            res.details=e.toString();
        }
       
        return res;
    }
    
    
    @GET
    @Override
    @Produces({"application/json"})
    public List<User> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({  "application/json"})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
