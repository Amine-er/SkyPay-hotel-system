package ma.errabi.booking.service;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendReservationConfirmation(String toEmail, UUID reference) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Booking Confirmation");
            helper.setText("Your reservation is confirmed. Reference: " + reference);
            mailSender.send(message);
            log.info("Confirmation email sent to {}", toEmail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

