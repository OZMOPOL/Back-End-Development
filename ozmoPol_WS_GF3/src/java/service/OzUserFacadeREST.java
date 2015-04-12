/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import UIClass.UIResult;
import com.ozmo.ent.OzUser;
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
 * @author sav
 */
@Stateless
@Path("com.ozmo.ent.ozuser")
public class OzUserFacadeREST extends AbstractFacade<OzUser> {
    @PersistenceContext(unitName = "ozmoPol_WS_GF3PU")
    private EntityManager em;

    public OzUserFacadeREST() {
        super(OzUser.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(OzUser entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    public void edit(OzUser entity) {
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
    public OzUser find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<OzUser> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<OzUser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    ////////////////////
    // CUSTOM METHODS //
    ////////////////////
    
    @GET
    @Path("checkLogin/{user}/{pass}")
    @Produces({"application/json"})
    
    public UIResult checkLogin(@PathParam("user") String user,@PathParam("pass") String pass) {
        
        List<OzUser> users= em.createNamedQuery("User.checkAuthStatus").setParameter("userName", user).setParameter("userPass", pass).getResultList();
        UIResult res=new UIResult();

        
        try {
        if (users.isEmpty()) { //NO USER FOUND
            res.title="NOK";
            res.message="Username-Password combination returned no result";
                
        }
        else{
            
            if(users.size()==1){ //USER FOUND
                
                if (users.get(0).getUserStatus() == false){ //USER NOT ACTIVATED YET 
                    res.title="NOK";
                    res.message="User account not active";
                }
                else if(users.get(0).getUserStatus() == true){ //BINGO!
                    res.title="OK";
                    res.message="Active User Found";
                                        
                } else {
                    res.title="NOK";
                    res.message="Inconsistent Database, User Status flag corrupted";
                }
                }
            
            else{
            res.title="NOK";
            res.message="Inconsistent Database, More than 1 users found. Check Database Enteries";
            }
            
        }
        
        } catch (Exception e) {
            res.title = "NOK";
            res.message=e.getMessage();
        }
        return res;
        

    }
}
