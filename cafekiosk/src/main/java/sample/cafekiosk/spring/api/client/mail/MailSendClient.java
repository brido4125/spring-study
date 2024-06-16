package sample.cafekiosk.spring.api.client.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MailSendClient {

    public boolean sendEmail(String fromEmail, String toEmail, String subject, String content) {
        // mail logic
        log.info("메일 전송");
        return true;
    }
}
