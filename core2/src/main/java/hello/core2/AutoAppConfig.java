package hello.core2;

import hello.core2.member.MemberRepository;
import hello.core2.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*
* 자동으로 스프링 빈 등록하는 Config
* type = FilterType.ANNOTATION, classes = Configuration.class => 수동 빈 등록인 AppConfig Class 제외시킴
* default range => 현재 Config 패키지의 하위 전부 패키지
* */

@Configuration
@ComponentScan(
        basePackages = {"hello.core2.member", "hello.core2.order", "hello.core2.discount"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    /* spring bean 수동 등록 => 자동 등록과 함께 충돌 발생 in SpringbootTest
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    */
}
