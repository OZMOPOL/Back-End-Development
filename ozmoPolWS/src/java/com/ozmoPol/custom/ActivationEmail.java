/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozmoPol.custom;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author sav
 */
public class ActivationEmail {

    public static void sendEmail(String email, String actCode) {

        final String username = "ozmopol@artificog.com";
        final String password = "Ozmopol1";

        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.artificog.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "26");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("ozmoPol Activation Code");
            message.setText("Hi,\n"
                    + "Please use the code below for ozmoPol account activation:\n\n"
                    + actCode
                    + "\n\nThank you!\n ozmoPol team."
            );

            Transport.send(message);
        } catch (MessagingException e) {
           
            
        }

    }
}
