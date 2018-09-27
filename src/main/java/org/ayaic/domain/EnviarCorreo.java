package org.ayaic.domain;

import java.io.Serializable;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

public class EnviarCorreo implements Serializable {

    private static final long serialVersionUID = 6976420100286651993L;

    private String to;
    private String from;
    private String subject;
    private String body;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setEnviarCorreo() {
        try {
            String pieCorreo = "\n.\n Este mensaje es generado electronicamente, no lo responda!.";
            Properties props = System.getProperties();
            props.put("mail.smtp.host", "localhost"); //cambiar localhost por getSmtp_server() si utiliza esa variable
            Session session = Session.getDefaultInstance(props, null);

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("wayka@coimata.bo"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(getTo(), false));

            msg.setSubject(getSubject());
            msg.setText(getBody() + pieCorreo);

            msg.setHeader("X-Mailer", "LOTONtechEmail");
            msg.setSentDate(new Date());

            Transport.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
