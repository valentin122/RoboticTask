package ru.vzhigalov.mail;

//import com.sun.tools.javac.util.StringUtils;

import ru.vzhigalov.servise.Config;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Mail {
    public List<String> getUnReadMessagesSubjects() throws Exception {
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
        properties.put("mail.store.protocol", protocol);

        properties.put("mail.imap.ssl.enable", "true");
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
        List<File> attachments = new ArrayList<File>();
        for (Message message : unreadMessages) {
            Object content = message.getContent();
            if (content instanceof Multipart) {
                Multipart multipart = (Multipart) message.getContent();

                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart bodyPart = multipart.getBodyPart(i);
                    if (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                        continue; // dealing with attachments only
                    }
                    //InputStream is = bodyPart.getInputStream();
                    File f = new File("C:/" + bodyPart.getFileName());
                    saveFile(f, bodyPart);
                    attachments.add(f);
                }
            }
        }
        List<String> subjects = new ArrayList<>();
        for (int i = 0; i < unreadMessages.length; i++) {
            subjects.add(unreadMessages[i].getSubject());
            unreadMessages[i].setFlag(Flags.Flag.SEEN, false); //true - пометить прочитанным
        }

        // Закрыть соединение
        folder.close(false);
        store.close();
        System.out.println(subjects);
        return subjects;
    }

    private void saveFile(File saveFile, Part part) throws Exception {
      /*  byte[] buff = new byte[204800];
        int buffer = 48;
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(saveFile), buffer);

        InputStream is = part.getInputStream();
        int ret = 0, count = 0;
        while ((ret = is.read(buff)) > 0) {
            bos.write(buff, 0, ret);
            count += ret;
        }
        bos.close();
        is.close();
        return count;
*/
        File file = new File("src/main/resources/" + part.getFileName());
        try(InputStream inputStream = part.getInputStream()) {
            java.nio.file.Files.copy(
                    inputStream,
                    file.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
