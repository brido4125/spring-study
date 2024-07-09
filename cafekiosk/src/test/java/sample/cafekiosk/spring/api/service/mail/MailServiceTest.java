package sample.cafekiosk.spring.api.service.mail;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.api.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

//@Mock 어노테이션 사용 시 아래 어노테이션 사용해야함
@ExtendWith(MockitoExtension.class)
class MailServiceTest {

//  @Spy -> 한 객체의 메서드에서 일부는 stub 일부는 원래 로직으로 사용하려 할 때
  @Mock
  private MailSendClient mailSendClient;

  @Mock
  private MailSendHistoryRepository mailSendHistoryRepository;

  @InjectMocks
  private MailService mailService;

  @DisplayName("메일 전송 테스트")
  @Test
  void  sendMail() {
      //given
    when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(true);

    BDDMockito.given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
            .willReturn(true);

    // @spy 사용시 아래와 같이 문법 사용
    /**
    doReturn(true).when(mailSendClient)
            .sendEmail(anyString(), anyString(), anyString(), anyString());
    */

    //when
    boolean result = mailService.sendMail("", "", "", "");

    //then
    // save 라는 메서드가 1회만 호출되었는지 검증하는 구문
    verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    assertThat(result).isTrue();
  }
  
}