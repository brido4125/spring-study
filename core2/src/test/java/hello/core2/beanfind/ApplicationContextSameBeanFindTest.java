package hello.core2.beanfind;

import hello.core2.member.MemberRepository;
import hello.core2.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("Duplicated Type Bean error")
    void findBeanByTypeDuplicatedFailureTest() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("If Duplicated Bean Type Found, Set the Bean Name With getBean Method")
    void findBeanByTypeDuplicatedTest() {
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("find All Duplicated Type Bean")
    void findAllBeanTypeDuplicatedTest() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String s : beansOfType.keySet()) {
            System.out.println("key = " + s + "value = " + beansOfType.get(s));
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    //Configuration for Test
    @Configuration
    static class SameBeanConfig{
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
