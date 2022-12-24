package hello.core3.beanfind;

import hello.core3.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] definitionNames = ac.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            Object bean = ac.getBean(definitionName);
            System.out.println("bean = " + bean);
            System.out.println("definitionName = " + definitionName);
        }
    }

    @Test
    @DisplayName("사용자 설정 Bean만 출력")
    void findAllApplicationBean() {
        String[] definitionNames = ac.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(definitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(definitionName);
                System.out.println("bean = " + bean);

            }
        }
    }
}
