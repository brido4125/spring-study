package hello.core2.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void singletonTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        assertThat(bean1).isSameAs(bean2);

        ac.close();
    }


    @Scope("singleton")
    static class SingletonBean{
        @PostConstruct
        public void init() {
            System.out.println("singletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("singletonBean.destroy");
        }
    }
}
