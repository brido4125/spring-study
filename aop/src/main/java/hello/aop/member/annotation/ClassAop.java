package hello.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // 클래스에만 적용되는 어노테이션
@Retention(RetentionPolicy.RUNTIME)// 런타임까지 유지시키는 어노테이션
public @interface ClassAop  {
}
