/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmoPol.service;

import com.ozmoPol.OzVote;
import com.ozmoPol.custom.CstResult;
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
@Path("com.ozmopol.ozvote")
public class OzVoteFacadeREST extends AbstractFacade<OzVote> {

    @PersistenceContext(unitName = "ozmoPolWSPU")
    private EntityManager em;

    public OzVoteFacadeREST() {
        super(OzVote.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(OzVote entity) {
        super.create(entity);
    }

    @POST
    @Path("createVote")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public CstResult createVote(OzVote entity) {
        CstResult res = new CstResult();
        try {
            this.create(entity);
            res.setTitle("OK");
            res.setMessage("Vote Created !");
            return res;
        } catch (Exception e) {
            res.setTitle("NOK");
            res.setMessage(e.getLocalizedMessage());
            return res;
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") String id, OzVote entity) {
        super.edit(entity);
    }

    @POST
    @Path("editVote")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public CstResult editVote(@PathParam("id") String id, OzVote entity) {
        CstResult res = new CstResult();
        try {
            this.edit(entity);
            res.setTitle("OK");
            res.setMessage("Vote Updated");

        } catch (Exception e) {
            res.setTitle("NOK");
            res.setMessage(e.getLocalizedMessage());
        }

        return res;

    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @POST
    @Path("deleteVote")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public CstResult deleteVote(OzVote vote) {
        CstResult res = new CstResult();
        try {
            this.remove(vote.getPkVoteId());
            res.setTitle("OK");
            res.setMessage("Vote Updated");

        } catch (Exception e) {
            res.setTitle("NOK");
            res.setMessage(e.getLocalizedMessage());
        }

        return res;

    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public OzVote find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<OzVote> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<OzVote> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
