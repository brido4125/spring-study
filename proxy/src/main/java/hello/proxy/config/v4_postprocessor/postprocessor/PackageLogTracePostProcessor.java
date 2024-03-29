package hello.proxy.config.v4_postprocessor.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


// 특정 패키지 하위의 클래스들에 프록시를 적용하기 위한 포스트 프로세서
@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {
    private final String basePackage; // 로그를 적용할 패키지 범위

    private final Advisor advisor; // pointcut + advice

    public PackageLogTracePostProcessor(String basePackage, Advisor advisor) {
        this.basePackage = basePackage;
        this.advisor = advisor;
    }


    /*
    * Bean 초기화 이후에 후처리기 로직 적용
    * */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("beanName = {}, bean = {}", beanName, bean.getClass());

        //프록시 대상 여부 체크
        //프록시 대상이 아니면 원본을 그대로 bean 등록
        String packageName = bean.getClass().getPackageName();
        if (!packageName.startsWith(basePackage)) {
            return bean;
        }

        //프록시 대상이면 프록시 생성, 원래 등록하려던 Bean이 Target
        //후처리기도 내부적으로는 ProxyFactory를 사용한다.
        ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvisor(advisor);

        Object proxy = proxyFactory.getProxy();
        log.info("create proxy = {}", proxy.getClass());
        return proxy;
    }
}
