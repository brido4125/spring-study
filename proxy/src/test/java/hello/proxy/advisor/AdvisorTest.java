package hello.proxy.advisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Method;


/*
* pointcut : proxy를 적용할 메서드 또는 클래스
* advice : 적용할 proxy 기능
* advisor : pointcut + advice
* */
@Slf4j
public class AdvisorTest {

    @Test
    void advisorTest1() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        // DefaultPointcutAdvisor -> 가장 기본적인 구현체
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice()); //항상 참인 PointCut 사용
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        proxy.save();
        proxy.find();
    }

    @Test
    @DisplayName("직접 만든 PointCut")
    void advisorTest2() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointCut(), new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        proxy.save();
        proxy.find();
    }

    @Test
    @DisplayName("스프링이 제공하는 PointCut")
    void advisorTest3() {
        ServiceInterface  target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        /*
        * Spring이 제공하는 NameMatchMethodPointcut을 사용
        * AspectJExpressionPointcut을 주로 사용함
        * */
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("save");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        proxy.save();
        proxy.find();
    }

    static class MyPointCut implements Pointcut {
        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }


    /*
    * Method와 target Class 정보를 통해 어느 메서드에 proxy 적용할지 결정
    * */
    static class MyMethodMatcher implements MethodMatcher {

        private final static String matchName = "save";


        /*
        * 정적인 정보가 parameter로 들어옴 -> caching 가능
        * */
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            boolean result = method.getName().equals(matchName); //save인 경우에만 Advice 적용
            log.info("포인터 컷 호출 method = {}, targetClass = {} ", method.getName(), targetClass);
            log.info("포인터 컷 결과 result = {} ", result);
            return result;
        }


        /*
        * isRuntime이 True를 리턴하면 matches(Method method, Class<?> targetClass, Object... args)를 호출함
        * */
        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }


}
