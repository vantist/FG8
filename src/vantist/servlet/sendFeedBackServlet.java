package vantist.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  

@SuppressWarnings("serial")
public class sendFeedBackServlet  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        final String username = "s7790428@gmail.com";  
        final String password = "ilovefrog";  
  
        Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
  
        Session session = Session.getInstance(props, new Authenticator() {  
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication(username, password);  
            }  
        }); 
  
        try {  
        	String title = req.getParameter("title");
        	if (title == null)
        		title = "無標題";
        	
        	String content = req.getParameter("content");
        	if (content == null)
        		return;
            Message message = new MimeMessage(session);  
            message.setFrom(new InternetAddress("s7790428@gmail.com"));  
            message.setRecipients(Message.RecipientType.TO, InternetAddress  
                    .parse("s7790428+parse8comic.feedback@gmail.com"));
            message.setSubject(title);
            
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            message.setText(content.replaceAll("<br>", "\n")+"\n\n\n"+sdf.format(date));
  
            Transport.send(message);
        } catch (MessagingException e) {  
            throw new RuntimeException(e);  
        }  
	}
}
