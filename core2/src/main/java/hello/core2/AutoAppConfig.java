package hello.core2;

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
        basePackages = {"hello.core2.member","hello.core2.order","hello.core2.discount"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
