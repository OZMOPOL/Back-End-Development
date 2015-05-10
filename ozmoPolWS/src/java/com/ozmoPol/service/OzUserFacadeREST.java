/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmoPol.service;

import com.ozmoPol.OzUser;
import com.ozmoPol.custom.ActivationEmail;
import com.ozmoPol.custom.CstResult;
import com.ozmoPol.custom.CstSession;
import com.ozmoPol.custom.RandomString;
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

import javax.validation.ConstraintViolationException;

/**
 *
 * @author sav
 */
@Stateless
@Path("com.ozmopol.ozuser")
public class OzUserFacadeREST extends AbstractFacade<OzUser> {

    @PersistenceContext(unitName = "ozmoPolWSPU")
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
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") String id, OzUser entity) {
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    ////////////////////
    // CUSTOM METHODS //
    ////////////////////
    /*
     *
     */
    //FUNCTION TO CHECK IF A USERNAME OR EMAIL ALREADY EXISTS
    @POST
    @Path("evalUser")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public CstResult evalUser(OzUser user) {
        CstResult res = new CstResult();

        List<OzUser> authUser = new ArrayList<>();
        List<OzUser> exUser = new ArrayList<>();
        List<OzUser> exUname = new ArrayList<>();
        List<OzUser> exUmail = new ArrayList<>();

        if (user.getUserName() != null) {
            if (user.getUserName().equalsIgnoreCase("")) {
                res.setTitle("NOK");
                res.setMessage("Empty parameters!");
                return res;
            }
        }

        if (user.getUserEmail() != null) {
            if (user.getUserEmail().equalsIgnoreCase("")) {
                res.setTitle("NOK");
                res.setMessage("Empty parameters!");
                return res;
            }
        }

        if (user.getUserPass() != null) {
            if (user.getUserPass().equalsIgnoreCase("")) {
                res.setTitle("NOK");
                res.setMessage("Empty parameters!");
                return res;
            }
        }

        try {

            if ((user.getUserName() != null) && (user.getUserPass() != null)) {
                authUser = em.createNamedQuery("OzUser.findByUserName&userPass").setParameter("userName", user.getUserName()).setParameter("userPass", user.getUserPass()).getResultList();
            }
            if ((user.getUserName() != null) && (user.getUserEmail() != null)) {
                exUser = em.createNamedQuery("OzUser.findByUserName&userEmail").setParameter("userName", user.getUserName()).setParameter("userEmail", user.getUserEmail()).getResultList();
            }
            if (user.getUserName() != null) {
                exUname = em.createNamedQuery("OzUser.findByUserName").setParameter("userName", user.getUserName()).getResultList();
            }
            if (user.getUserEmail() != null) {
                exUmail = em.createNamedQuery("OzUser.findByUserEmail").setParameter("userEmail", user.getUserEmail()).getResultList();
            }

            if (authUser.size() == 1) {
                if (authUser.get(0).getUserStatus() == true) {
                    res.setTitle("OK");
                    res.setMessage("Authentic Active User Found!");
                } else {
                    res.setTitle("OK");
                    res.setMessage("Authentic Inactive User Found!");
                }
                res.setUsers(authUser);

            } else if (exUser.size() == 1) {
                if (exUser.get(0).getUserStatus() == true) {
                    res.setTitle("OK");
                    res.setMessage("Existing Active User Found!");
                } else {
                    res.setTitle("OK");
                    res.setMessage("Existing Inactive User Found!");
                }
                res.setUsers(exUser);

            } else if (exUmail.size() == 1) {
                res.setTitle("OK");
                res.setMessage("Existing Email Found!");

            } else if (exUname.size() == 1) {
                res.setTitle("OK");
                res.setMessage("Existing UserName Found!");

            } else {
                res.setTitle("OK");
                res.setMessage("User Not Found!");
            }

        } catch (Exception e) {
            res.setTitle("NOK");
            res.setMessage(e.getLocalizedMessage());
        }

        return res;
    }

    // LOGIN REGISTERED USER
    @POST
    @Path("logIn")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public CstSession logIn(OzUser user) {
        CstSession res = new CstSession();
        CstResult loginRes = new CstResult();
        
        try {
            loginRes = evalUser(user);
            if (loginRes.getMessage().equalsIgnoreCase("Authentic Active User Found!")) {
                res.setTitle("OK");
                res.setMessage("User \'" + user.getUserName() + "\' Logged In Successfully!");
                res.setUser(loginRes.getUsers().get(0));
                
            } else {
                res.setTitle("NOK");
                res.setMessage("Login Unsuccessful! Reason: " + evalUser(user).getMessage());
            }

        } catch (Exception e) {
            res.setTitle("NOK");
            res.setMessage(e.getMessage());
        }
        return res;
    }

// SEND ACTIVATION CODE FOR EXISTING USERS
    @POST
    @Path("sendActCode")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public CstResult sendActCode(OzUser user) {
        CstResult res = new CstResult();
        
        if (evalUser(user).getMessage().equals("Existing Inactive User Found!") || evalUser(user).getMessage().equals("Authentic Inactive User Found!")) {
            try {
                ActivationEmail.sendEmail(user.getUserEmail(), user.getUseractHash());
                res.setTitle("OK");
                res.setMessage("Verification Email Sent!");
            } catch (Exception e) {

                res.setTitle("NOK");
                res.setMessage(e.getLocalizedMessage());

            }

        } else {
            res.setTitle("NOK");
            res.setMessage("User non-existent or already active!");
        }

        return res;
    }
//

//    // Send Activation Code For New Users
    private CstResult sendNewActCode(OzUser user) {
        CstResult res = new CstResult();

        try {
            ActivationEmail.sendEmail(user.getUserEmail(), user.getUseractHash());
            res.setTitle("OK");
            res.setMessage("Verification Email Sent!");
        } catch (Exception e) {

            res.setTitle("NOK");
            res.setMessage(e.getLocalizedMessage());

        }

        return res;
    }

    // SIGNUP NEW USER
    @POST
    @Path("signUp")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public CstResult signUp(OzUser user) {
        CstResult res = new CstResult();

        if (evalUser(user).getMessage().equalsIgnoreCase("User Not Found!")) {
            RandomString randString = new RandomString(32);
            RandomString smallRandString = new RandomString(6);
            user.setUserName(user.getUserName());
            user.setUserEmail(user.getUserEmail());
            user.setUserPass(user.getUserPass());
            user.setUseractHash(smallRandString.nextString());
            user.setPkUserId(randString.nextString());
            user.setUserStatus(Boolean.FALSE);

            try {
                if (sendNewActCode(user).getTitle().equals("OK")) {
                    super.create(user);
                    res.setTitle("OK");
                    res.setMessage("User created. Please check the registered e-mail address for activation code.");
                } else {
                    res.setTitle("NOK");
                    res.setMessage("User activation code could not be sent.");
                }
            } catch (Exception e) {
                res.setTitle("NOK");
                res.setMessage(e.getMessage());
            }

        } else {
            res.setTitle("NOK");
            res.setMessage("userName or userEmail already exists OR Required fields are not filled properly.");
        }

        return res;

    }
}
