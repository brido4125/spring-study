package hello.core2.scan;

import hello.core2.AutoAppConfig;
import hello.core2.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {
    @Test
    @DisplayName("Using AutoAppConfig")
    void autoAppConfigTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        int beanCount = 0;
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                beanCount++;
            }
        }
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
