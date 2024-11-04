/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legalnod.serviceimpl;

import java.io.File;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;



public class EmailSending {

	private static final String SMTP_HOST_NAME = "mail.legalnod.com";
	private static final String SMTP_PORT = "25"; // default port is 465 !
	private static final String SMTP_AUTH_USER = "info@legalnod.com";
	private static final String SMTP_AUTH_PWD = "legalnod123*";
	private static final String SMTP_FROM_ADDRESS = "info@legalnod.com";
	
	//Send to a single recipient
	
	public void sendEmail(String email, String subject, String text,String type) throws Exception {
	String[] emailList = new String[1];
	emailList[0] = email;
	sendEmail(emailList, subject, text,type);
	}
	
	//Send to a single recipient from mail is dynamic
	
	public void sendEmails(String email, String subject, String text,String type,String fromAdd)
		throws Exception
	    {
	String[] emailsList = new String[1];
	emailsList[0] = email;
	sendEmails(emailsList, subject, text,type,fromAdd);
	}
	
	//Send to multiple recipients
	
	public void sendEmail(String[] emailList, String subject, String text,String type) throws Exception {
	boolean debug = true;
	
	Properties props = System.getProperties();
	
	props.put("mail.smtp.starttls.enable", "true"); // added this line
	props.put("mail.smtp.host", SMTP_HOST_NAME);
	props.put("mail.smtp.auth", "true");
	props.put("mail.debug", "true");
	props.put("mail.smtp.user", SMTP_AUTH_USER);
	props.put("mail.smtp.password", SMTP_AUTH_PWD);
	props.put("mail.smtp.port", SMTP_PORT );
	
	Authenticator auth = new SMTPAuthenticator();
	    Session session = Session.getDefaultInstance(props, auth);
	session.setDebug(debug);
	    Message msg = new MimeMessage(session);
	
	    InternetAddress addressFrom = new InternetAddress(SMTP_FROM_ADDRESS);
	msg.setFrom(addressFrom);
	    InternetAddress[] addressTo = new InternetAddress[emailList.length];
	
	    for (int i = 0; i < emailList.length; i++){
	addressTo[i] = new InternetAddress(emailList[i]);
	}
	
	msg.setRecipients(Message.RecipientType.TO, addressTo);
	msg.setSubject(subject);
	msg.setContent(text, type);
	
	    Transport transport = session.getTransport("smtp");
	    transport.connect(SMTP_HOST_NAME, SMTP_AUTH_USER, SMTP_AUTH_PWD);
	    transport.sendMessage(msg, msg.getAllRecipients());
	    transport.close();
	}
	
	//Send to a multiple recipient from mail is dynamic
	
	public void sendEmails(String[] emailsList, String subject, String text,String type,String fromAdd) throws Exception {
	boolean debug = true;
	
	Properties props = System.getProperties();
	
	props.put("mail.smtp.starttls.enable", "true"); // added this line
	props.put("mail.smtp.host", SMTP_HOST_NAME);
	props.put("mail.smtp.auth", "true");
	props.put("mail.debug", "true");
	props.put("mail.smtp.user", SMTP_AUTH_USER);
	props.put("mail.smtp.password", SMTP_AUTH_PWD);
	props.put("mail.smtp.port", SMTP_PORT );
	
	Authenticator auth = new SMTPAuthenticator();
	Session session = Session.getDefaultInstance(props, auth);
	session.setDebug(debug);
	Message msg = new MimeMessage(session);
	InternetAddress addressFrom = new InternetAddress(fromAdd);
	msg.setFrom(addressFrom);
	InternetAddress[] addressTo = new InternetAddress[emailsList.length];
	
	for (int i = 0; i < emailsList.length; i++){
	addressTo[i] = new InternetAddress(emailsList[i]);
	}
	
	msg.setRecipients(Message.RecipientType.TO, addressTo);
	msg.setSubject(subject);
	msg.setContent(text, type);
	
	    Transport transport = session.getTransport("smtp");
	    transport.connect(SMTP_HOST_NAME, SMTP_AUTH_USER, SMTP_AUTH_PWD);
	    transport.sendMessage(msg, msg.getAllRecipients());
	    transport.close();
	}
	
	
	public void sendEmailWithAttachment(String email, String subject, String text,String type,File fileAtta,String fileName) throws Exception {
	String[] emailList = new String[1];
	emailList[0] = email;
	sendEmailWithAttachment(emailList, subject, text,type,fileAtta,fileName);
	}
	
	public void sendEmailWithAttachment(String[] emailList, String subject, String text,String type,File fileAttachment,String fileName) throws Exception {
	boolean debug = true;
	
	Properties props = System.getProperties();
	
	props.put("mail.smtp.starttls.enable", "true"); // added this line
	props.put("mail.smtp.host", SMTP_HOST_NAME);
	props.put("mail.smtp.auth", "true");
	props.put("mail.debug", "true");
	props.put("mail.smtp.user", SMTP_AUTH_USER);
	props.put("mail.smtp.password", SMTP_AUTH_PWD);
	props.put("mail.smtp.port", SMTP_PORT );
	
	Authenticator auth = new SMTPAuthenticator();
	    Session session = Session.getDefaultInstance(props, auth);
	session.setDebug(debug);
	    MimeMessage msg = new MimeMessage(session);
	
	InternetAddress addressFrom = new InternetAddress(SMTP_FROM_ADDRESS);
	msg.setFrom(addressFrom);
	InternetAddress[] addressTo = new InternetAddress[emailList.length];
	
	for (int i = 0; i < emailList.length; i++){
	addressTo[i] = new InternetAddress(emailList[i]);
	}
	
		msg.setRecipients(Message.RecipientType.TO, addressTo);
		msg.setSubject(subject);
	    BodyPart bodyPart = new MimeBodyPart();
	    bodyPart.setContent(text, type);
	    MimeBodyPart messageBodyPart = new MimeBodyPart();
	    DataSource source =  new FileDataSource(fileAttachment);
	    messageBodyPart.setDataHandler( new DataHandler(source));
	    messageBodyPart.setFileName(fileName);
	
	    Multipart multipart = new MimeMultipart();
	    multipart.addBodyPart(bodyPart);
	    multipart.addBodyPart(messageBodyPart);
	    msg.setContent(multipart);
	
	    Transport transport = session.getTransport("smtp");
	    transport.connect(SMTP_HOST_NAME, SMTP_AUTH_USER, SMTP_AUTH_PWD);
	    transport.sendMessage(msg, msg.getAllRecipients());
	    transport.close();
	}
	
	
	    private class SMTPAuthenticator extends javax.mail.Authenticator{
	    public PasswordAuthentication getPasswordAuthentication(){
	        String username = SMTP_AUTH_USER;
	        String password = SMTP_AUTH_PWD;
	        return new PasswordAuthentication(username, password);
	    }
	}
}

