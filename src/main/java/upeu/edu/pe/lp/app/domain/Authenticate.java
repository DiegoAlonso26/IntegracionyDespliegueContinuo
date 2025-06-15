package upeu.edu.pe.lp.app.domain;

import org.springframework.stereotype.Service;
import upeu.edu.pe.lp.infrastructure.email.MailManager;


@Service
public class Authenticate {

    MailManager mailManager;

    public Authenticate(MailManager mailManager) {
        this.mailManager = mailManager;
    }

    public void sendMessageByUser(String email, String message){
        mailManager.sendMesage(email, message);

    }
}
