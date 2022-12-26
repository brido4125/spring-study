package hello.core3;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
/*
* basePackages를 지정하지 않는 경우 -> hello.core3 패키지 하위 디렉토리로 Scan 진행
* */
@ComponentScan(
        basePackages = "hello.core3",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //기존의 AppConfig Bean 제외
)
public class AutoAppConfig {

}
