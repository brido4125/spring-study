package brido.me.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    public static final String ePw = createKey();

    private MimeMessage createMessage(String to)throws Exception{
        log.info("Send To = {}",to);
        log.info("Key Number = {}", ePw);
        MimeMessage  message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);//보내는 대상
        message.setSubject("DevU 회원가입 이메일 인증");//제목

        String msgg="";
        msgg+= "<div style='margin:100px;'>";
        msgg+= "<h1> 안녕하세요 devU입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("ynudev@gmail.com","DevU"));//보내는 사람

        return message;
    }

    private MimeMessage createLinkMessage(String to)throws Exception {
        log.info("Send To = {}",to);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);//보내는 대상
        message.setSubject("DevU 회원가입 이메일 인증");//제목

        String msg = "";
        msg+= "<div style='margin:100px;'>";
        msg+= "<h1> 안녕하세요 devU입니다. </h1>";
        msg+= "<br>";
        msg+= "<p>아래 링크에 접속하여 회원가입을 완료해주세요!<p>";
        msg+= "<br>";
        msg+= "<p>감사합니다!<p>";
        msg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msg += "<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>";
        msg += "<a href='http://localhost:8080/auth/signup/confirm?email=";
        msg += to;
        msg += "&emailAuthKey=";
        msg += ePw;
        msg += "' target='_blenk'>이메일 인증 확인</a>";

        message.setText(msg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("ynudev@gmail.com","DevU"));//보내는 사람

        return message;
    }

    //6자리 랜덤 Key
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }


    @Override
    public String sendSimpleMSG(String to) throws Exception {
        MimeMessage message = createLinkMessage(to);
        try {
            emailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            log.error("Mail error :  ",e);
            throw new IllegalArgumentException();
        }
        return ePw;
    }
}
