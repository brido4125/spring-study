package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LogRepository logRepository;

    /**
     * memberService        @Transactional: OFF
     * memberRepository     @Transactional: ON
     * logRepository        @Transactional: ON
     */
    @Test
    void outerTxOff_success() {
        String username = "outerTxOff_success";

        memberService.joinV1(username);

        Assertions.assertTrue(memberRepository.find(username).isPresent());
        Assertions.assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * memberService        @Transactional: OFF
     * memberRepository     @Transactional: ON
     * logRepository        @Transactional: ON Exception
     */
    @Test
    void outerTxOff_fail() {
        String username = "로그예외_outerTxOff_fail";

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            memberService.joinV1(username);
        }).isInstanceOf(RuntimeException.class);


        Assertions.assertTrue(memberRepository.find(username).isPresent());
        Assertions.assertFalse(logRepository.find(username).isPresent());
    }

    /**
     * memberService        @Transactional: On
     */
    @Test
    void singleTx() {
        String username = "outerTxOff_success";

        memberService.joinV1(username);

        Assertions.assertTrue(memberRepository.find(username).isPresent());
        Assertions.assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * memberService        @Transactional: ON
     * memberRepository     @Transactional: ON
     * logRepository        @Transactional: ON Exception
     */
    @Test
    void outerTxOn_fail() {
        String username = "로그예외_outerTxOn_fail";

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            memberService.joinV1(username);
        }).isInstanceOf(RuntimeException.class);


        Assertions.assertTrue(memberRepository.find(username).isEmpty());
        Assertions.assertTrue(logRepository.find(username).isEmpty());
    }
}