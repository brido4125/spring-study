package hello.proxy.config.v6_aop;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v6_aop.aspect.LogTraceAspect;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


//AnnotationAwareAspectJAutoProxyCreator(빈 후처리기)가 @Aspect가 붙은 Advisor를 판단하고 pointcut을 통해 proxy 생성 여부 결정
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AopConfig {

    /*
    * @Aspect 어노테이션을 사용하면, 해당 객체가 advisor 역할을 수행
    * 스프링 AOP의 joinPoint는 메서드 실행으로 제한된다.
    * 메서드 호출이 일어나야 proxy 객체 호출이 가능하기 때문
    * */
    @Bean
    public LogTraceAspect logTraceAspect(LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }
}
