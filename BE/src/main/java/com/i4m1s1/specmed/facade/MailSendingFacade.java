package com.i4m1s1.specmed.facade;

import com.i4m1s1.specmed.persistence.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
//TODO poprawić strukturę tekstu maila i moze przenisc do pliku :).
@Component
public class MailSendingFacade {

    private static final String REGISTER_SUBJECT = "Rejestracja w portalu SPECMED";
    private static final String REGISTER_TEXT_CUSTOMER = "<h1>Witaj w portalu SPECMED!</h1>\n" +
            "<div>\n" +
            "\tTen adres email został użyty do rejestracji w portalu %s<br/>\n" +
            "\tAby dokończyc rejestrację, wykonaj poniższe kroki:<br/>\n" +
            "\t<ul>\n" +
            "\t<li>przejdź na %s</li>\n" +
            "\t<li>potwierdź swoje dane osobowe podając PESEL</li>\n" +
            "\t</ul>\n" +
            "\tJeżeli nie posiadasz konta ze swoimi danymi, powinieneś:<br/>\n" +
            "\t<ul>\n" +
            "\t<li>odwiedzić najbliższą placówkę z naszej listy (%s)</li>\n" +
            "\t<li>wziąć ze sobą dowód osobisty aby potwierdzić tożsamość</li>\n" +
            "\t</ul>\n" +
            "</div>";
    private static final String HOST_PATH = "http://localhost:9000";
    private static final String UNITS_LIST_PATH = HOST_PATH + "/unit/list";

    @Autowired
    private JavaMailSender emailSender;

    public void sendTestEmailToMe() {
        String to = ""; //tu wpisz swoj email
        String subject = "test subject";
        String text = " this is text : ) ";
        sendEmail(to, subject, text);
    }

    /**
     * Wysyłanie maila od razu po rejestracji pcjenta (Utworzeniu nowego customera)
     * @param customer nowy pacjent
     */
    public void sendRegisterEmailForCustomer(Customer customer) {
        String to = customer.getContactData().getEmail();
        String customerId = customer.getId();

        String subject = REGISTER_SUBJECT;
        //todo przeniesc do metody lub w pliku html
        String text = String.format(
                REGISTER_TEXT_CUSTOMER,
                buildAnchor(HOST_PATH, "specmed-stub.pl"),
                buildAnchor(buildBindPath(customerId), "stronę dokończenia rejestracji"),
                buildAnchor(UNITS_LIST_PATH, "lista placówek")
        );
        sendEmail(to, subject, text);
    }

    /**
     * Podstawowa metoda do wysyłania maili
     *
     * @param to      do kogo mail
     * @param subject tytuł
     * @param text    zawartość maila - jako html
     */
    private void sendEmail(String to, String subject, String text) {
        try {

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false);

            message.setHeader("Content-type", "text/html; charset=UTF-16");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            //todo zrobic SMException jakies
        }
    }


    private String buildAnchor(String link, String name) {
        return String.format("<a href='%s'>%s</a>", link, name);
    }

    private String buildBindPath(String customerId) {
        return HOST_PATH + "/bind-account/" + customerId;

    }
}
