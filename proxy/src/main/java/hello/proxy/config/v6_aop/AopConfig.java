package hello.proxy.config.v6_aop;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v6_aop.aspect.LogTraceAspect;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/*
* AnnotationAwareAspectJAutoProxyCreator가 수행하는 2가지
* 1. (사용자가 Bean으로 등록한 advisor + @Aspect 어드 바이저 빌더의 adivisor) 를 찾아와 proxy를 적용할지 말지 판단하고 proxy를 빈 등록
* 2. @Aspect 어노테이션을 찾아 이것을 advisor로 만들고 @Aspect 어드 바이저 빌더에 해당 advisor 등록
* */
@Configuration
@Import({AppV1Config.class, AppV2Config.class}) // 수동 빈 등록 대상들
public class AopConfig {

    /*
    * @Aspect 어노테이션을 사용하면, 해당 객체가 advisor 역할을 수행
    * */
    @Bean
    public LogTraceAspect logTraceAspect(LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }
}
