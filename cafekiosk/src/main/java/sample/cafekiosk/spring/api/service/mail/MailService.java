package sample.cafekiosk.spring.api.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

@RequiredArgsConstructor
@Service
public class MailService {

    private final MailSendClient mailSendClient;

    private final MailSendHistoryRepository mailSendHistoryRepository;

    public boolean sendMail(String fromEmail, String toEmail, String subject, String content) {
        if (mailSendClient.sendEmail(fromEmail, toEmail, subject, content)) {
            mailSendHistoryRepository.save(MailSendHistory.builder()
                .content(content)
                .fromEmail(fromEmail)
                .subject(subject)
                .toEmail(toEmail)
                .build());
            return true;
        }
        return false;
    }
}
