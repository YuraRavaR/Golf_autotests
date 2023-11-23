package golf.utilities;

import org.chi.tools.Receiver;
import org.chi.tools.dto.MessageDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.mail.*;

import static org.chi.tools.cfg.Config.*;

public class EmailReceiver {

    public static String getLastMailHtmlValue(String userName, String password) {
        Receiver receiver = new Receiver();
        MessageDto messageDto = null;
        try {
            messageDto = receiver.downloadLastEmail(IMAP_PROTOCOL, IMAP_HOST, IMAP_PORT, userName, password);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return messageDto.getMessage();
    }

    public static String getTemporaryPasswordValue(String html) {
        Document doc = Jsoup.parse(html);
        Element passwordElement = doc.select("p b").first();
        if (passwordElement != null) {
            return passwordElement.text();
        } else {
            return "e";
        }
    }
}