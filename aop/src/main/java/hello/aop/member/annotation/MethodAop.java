package hello.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 메소드에만 붙일 수 있는 어노테이션 설정
@Retention(RetentionPolicy.RUNTIME) //실행 시점에도 해당 에노테이션을 읽을 수 있음
public @interface MethodAop {
    String value(); // 어노테이션의 인자 String으로 설정
}
