/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import UIClass.GoogleMail;
import UIClass.RandomString;
import UIClass.UIResult;
import com.ozmo.ent.OzUser;
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
import java.util.List;
import javax.validation.ConstraintViolationException;

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
    private UIResult listEval(List list) {
        UIResult res = new UIResult();

        try {
            if (list.isEmpty()) { //NO USER FOUND
                res.title = "NOK";
                res.message = "No entity found";

                return res;

            } else {

                if (list.size() == 1) { //USER FOUND
                    res.title = "OK";
                    res.message = "Entity found!";
                    res.body = list.get(0);

                    return res;

                } else {
                    res.title = "NOK";
                    res.message = "Inconsistent Database, More than 1 results found. Check Database Enteries";

                    return res;
                }

            }

        } catch (Exception e) {
            res.title = "NOK";
            res.message = e.getMessage();

            return res;
        }

    }

    private UIResult userExists(String property, String value) {
        UIResult res = new UIResult();
        List<OzUser> users = new ArrayList<OzUser>();


        if (property.equals("UserName") || property.equals("User Name") || property.equals("user name") || property.equals("username")) {
            users = em.createNamedQuery("OzUser.findByUserName").setParameter("userName", value).getResultList();
        } else if (property.equals("UserEmail") || property.equals("Email") || property.equals("email") || property.equals("userEmail")) {
            users = em.createNamedQuery("OzUser.findByUserEmail").setParameter("userEmail", value).getResultList();
        } else if (property.equals("UserID") || property.equals("Userid") || property.equals("UserId") || property.equals("id") || property.equals("ID")) {
            users = em.createNamedQuery("OzUser.findByPkUserId").setParameter("pkUserId", value).getResultList();
        } else {
            res.title = "NOK";
            res.message = "Wrong property requested. Use either UserName, UserEmail or UserID property names.";

        }

        try {
            if (users.isEmpty()) { //NO USER FOUND
                res.title = "NOK";
                res.message = "No users found to have the indicated property value";

            } else {

                if (users.size() == 1) { //USER FOUND

                    if (users.get(0).getUserStatus() == false) { //USER NOT ACTIVATED YET 
                        res.title = "NOK";
                        res.message = "User account not active";
                    } else if (users.get(0).getUserStatus() == true) { //BINGO!
                        res.title = "OK";
                        res.message = "Active User Found!";

                    } else {
                        res.title = "NOK";
                        res.message = "Inconsistent Database, User Status flag corrupted";
                    }
                } else {
                    res.title = "NOK";
                    res.message = "Inconsistent Database, More than 1 users found. Check Database Enteries";
                }

            }

        } catch (Exception e) {
            res.title = "NOK";
            res.message = e.getMessage();
        }

        return res;
    }

    @POST
    @Path("checkLogin")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public UIResult checkLogin(OzUser user) {
        UIResult res = new UIResult();
        try {
            List<OzUser> users = em.createNamedQuery("OzUser.checkAuthStatus").setParameter("userName", user.getUserName()).setParameter("userPass", user.getUserPass()).getResultList();

            if (listEval(users).title.toString().equals("OK")) {
                OzUser okUser = (OzUser) listEval(users).body;
                res.body = okUser;

                if (okUser.getUserStatus() == false) { //USER NOT ACTIVATED YET 
                    res.title = "NOK";
                    res.message = "User account not active";
                } else if (okUser.getUserStatus() == true) { //BINGO!
                    res.title = "OK";
                    res.message = "Active User Found";

                } else {
                    res.title = "NOK";
                    res.message = "User account de-activated";
                }

            } else {
                res = listEval(users);
            }



        } catch (Exception e) {
            res.title = "NOK";
            res.message = e.getLocalizedMessage();
        }

        return res;

    }

    @POST
    @Path("signUpVal/newUser")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public UIResult signUpVal(OzUser newUser) {
        UIResult res = new UIResult();

        List<OzUser> exUname = em.createNamedQuery("OzUser.findByUserName").setParameter("userName", newUser.getUserName()).getResultList();
        List<OzUser> exEmail = em.createNamedQuery("OzUser.findByUserEmail").setParameter("userEmail", newUser.getUserEmail()).getResultList();

        if (exUname.isEmpty() && exEmail.isEmpty()) {
            try {
                //create an ozmoPolitan
                RandomString randString = new RandomString(32);
                RandomString smallRandString = new RandomString(4);
                String actHash = smallRandString.nextString();
                String randID = randString.nextString();
                newUser.setUseractHash(actHash);
                newUser.setUserName(newUser.getUserName());
                newUser.setUserEmail(newUser.getUserEmail());
                newUser.setPkUserId(randID);
                newUser.setUserPass(newUser.getUserPass());
                newUser.setUserStatus(Boolean.FALSE);
                GoogleMail.Send(newUser.getUserEmail().toString(), actHash);

                super.create(newUser);

                res.title = "OK";
                res.message = "User created, please check the registered email for activation code.";
            } catch (ConstraintViolationException e) {
                res.title = "NOK";
                res.message = e.getConstraintViolations().toString();
            } catch (Exception e) {
                res.title = "NOK";
                res.message = e.toString();
            }

        } else {
            if (exEmail.size() == 1) {
                res.title = "NOK";
                res.message = "Email address already exists";
            } else if (exUname.size() == 1) {
                res.title = "NOK";
                res.message = "Username already exists";
            } else if (exUname.size() == 1 || exEmail.size() == 1) {
                res.title = "NOK";
                res.message = "This Username-Email combination is already registered";
            } else {
                res.title = "NOK";
                res.message = "Database Corrupted, More than one users already exist with this username-email";
            }
        }

        return res;
    }

    @POST
    @Path("verifyUser")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public UIResult verifyUser(OzUser user) {
        UIResult res = new UIResult();
        String actCode = user.getUseractHash();

        try {
            if (checkLogin(user).message.equals("User account not active")) {
                OzUser verUser = (OzUser) checkLogin(user).body;
                if (verUser.getUseractHash().equals(actCode)) {

                    verUser.setUserStatus(Boolean.TRUE);
                    super.edit(verUser);
                    res.title = "OK";
                    res.message = "User Activated!";
                    res.body = verUser;
                } else {

                    res.title = "NOK";
                    res.message = "Wrong Activation Code";
                    res.body = verUser;

                }
            } else {
                if (checkLogin(user).message.equals("Active User Found!")) {
                    res.title = "NOK";
                    res.message = "User already Active!";

                } else {
                    res = checkLogin(user);

                }
            }

        } catch (Exception e) {
            res.title = "NOK";
            res.message = e.getLocalizedMessage();

        }
        return res;
    }

    @POST
    @Path("uprofile")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public UIResult getOtherUserProfile(OzUser user) {
        UIResult res = new UIResult();

        res = userExists("UserID", user.getPkUserId());

        if (res.title.equals("OK")) {

            try {
                // construct user profile from UIUser class
                //return UIUser profile json object
            } catch (Exception e) {
                res.title = "NOK";
                res.message = e.toString();
            }

        }
        return res;

    }
}
