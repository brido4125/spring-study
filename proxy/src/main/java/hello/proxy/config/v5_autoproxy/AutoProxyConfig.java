package hello.proxy.config.v5_autoproxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {


    // Advisor를 빈 등록하면 끝남
    // 자동 프록시 생성기 : AnnotationAwareAspectJAutoProxyCreator 빈 후처리기는 이미 빈 등록 되어 있음

    /*
    * PostProcessor를 빈으로 등록할 필요가 없어짐
    * */

    // point cut은 아래 두 가지 상황에서 사용됨
    // 1. 프록시 적용 여부 판단 -> 생성단에서
    // package를 통해 적용 여부 or 메서드 이름이 match하는지 검사를 통해 적용 여부 판단
    // 판단 대상이면 Bean 등록 시 실제 인스턴스가 아닌 proxy 인스턴스를 등록
    // 2. 실제 proxy가 호출 되었을 때, 호출된 메서드에 advice를 적용할지 말지 판단

    //@Bean
    public Advisor advisor1(LogTrace logTrace) {
        NameMatchMethodPointcut pointCut = new NameMatchMethodPointcut();
        pointCut.setMappedNames("request*", "order*", "save*");// 적용 대상의 범위가 너무 넓게 설정됨 -> 기대하지 않은 클래스들도 proxy를 가짐
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointCut, advice);
    }

    //@Bean
    public Advisor advisor2(LogTrace logTrace) {
        AspectJExpressionPointcut pointCut = new AspectJExpressionPointcut();
        pointCut.setExpression("execution(* hello.proxy.app..*(..))");//AspectJExpressionPointcut 문법을 사용 -> 단순히 패키지를 기준으로 매칭해서
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointCut, advice);
    }

    @Bean
    public Advisor advisor3(LogTrace logTrace) {
        AspectJExpressionPointcut pointCut = new AspectJExpressionPointcut();
        pointCut.setExpression("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))"); // noLog 메서드 제외하기 위한 표현
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointCut, advice);
    }
}
