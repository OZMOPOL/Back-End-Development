/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmoPol.custom;

import com.ozmoPol.OzUser;

/**
 *
 * @author sav
 */
public class CstSession {
    
    private String title;
    private String message;
    private OzUser user;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(OzUser user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public OzUser getUser() {
        return user;
    }
    
    
}
