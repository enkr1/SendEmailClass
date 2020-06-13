/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
  
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Pang Jing Hui
 * A utility class for sending e-mail messages
 * Jing Hui's msg for members: First step, import a new lib from https://github.com/javaee/javamail/releases
 * add into Libraries folder 
 * 
 * ref: https://www.youtube.com/watch?v=A7HAB5whD6I
 *      https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
 */

public class EmailUtility {
    
    public static void sendEmail(String recipientEmail) {
        try {
            System.out.println("Before sending..");
            
            // sets SMTP server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true"); // defines if auth is needed in this method
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            
            // sender's properties
            String myAccountEmail = ""; // your email
            String myPassword = ""; // your email's password 
            
            
            // creates a new session with an authenticator
            Session session = Session.getInstance(properties, new  Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, myPassword);
                }
            });
            
            Message message = prepareMessage(session, myAccountEmail, recipientEmail);
            Transport.send(message);
            System.out.println("Email sent!");
        } catch (MessagingException ex) {
            Logger.getLogger(EmailUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipientEmail) {
        try {
            // creates a new e-mail message
            Message msg = new MimeMessage(session);
            
            // Set the email format
            msg.setFrom(new InternetAddress(myAccountEmail));
            InternetAddress[] toAddresses = { new InternetAddress(recipientEmail) };
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject("My First Send Email Class");
            msg.setSentDate(new Date());
            msg.setText("Hey, \n\n"
                        + "This email is sent using this web application." +
                        "\n\nBest regards, \nPang Jing Hui");
            
            return msg;
            
        } catch (MessagingException ex) {
            Logger.getLogger(EmailUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}