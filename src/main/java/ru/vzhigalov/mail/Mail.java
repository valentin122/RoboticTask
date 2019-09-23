package ru.vzhigalov.mail;

import com.sun.mail.util.MailSSLSocketFactory;
import ru.vzhigalov.servise.Config;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.search.FlagTerm;

public class Mail {
    public List<String> getUnReadMessagesSubjects() throws javax.mail.MessagingException {
        Config config = new Config();
        config.init();
        String user = config.get("mail.login");
        String pass = config.get("mail.password");
        String protocol = config.get("mail.protocol");
        String host = config.get("mail.server");
        String port = config.get("mail.port");
        String debug = config.get("mail.debug");

        // Получить системные свойства
        Properties properties = System.getProperties();
        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", port);
        properties.put("mail.debug", debug);
        properties.put("mail.store.protocol" , protocol );

        properties.put("mail.imap.ssl.enable", "true"   );
        properties.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.imap.socketFactory.fallback", "false");
        properties.put("mail.imap.ssl.trust", "*");


        // Настроить аутентификацию, получить session

        Authenticator auth = new EmailAuthenticator(user, pass);
        Session session = Session.getDefaultInstance(properties, auth);

        // Получить store
        Store store = session.getStore(protocol);
        try {
            store.connect();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }

        // Получить папку входящие
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);

        // Получить каталог
        Message[] unreadMessages = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        List<String> subjects = new ArrayList<>();
        for (int i = 0; i < unreadMessages.length; i++) {
            subjects.add(unreadMessages[i].getSubject());
            //unreadMessages[i].setFlag(Flags.Flag.SEEN, true); //true - пометить прочитанным
        }

        // Закрыть соединение
        folder.close(false);
        store.close();
        System.out.println(subjects);
        return subjects;
    }
}
