/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv;

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
import ozmoPol.Xuserflwuser;

/**
 *
 * @author sav
 */
@Stateless
@Path("ozmopol.xuserflwuser")
public class XuserflwuserFacadeREST extends AbstractFacade<Xuserflwuser> {
    @PersistenceContext(unitName = "ozmoPol_WebSrvPU")
    private EntityManager em;

    public XuserflwuserFacadeREST() {
        super(Xuserflwuser.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Xuserflwuser entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Xuserflwuser entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Xuserflwuser find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Xuserflwuser> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Xuserflwuser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
}
