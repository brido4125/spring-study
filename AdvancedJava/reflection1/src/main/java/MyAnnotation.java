import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// @Retention(RetentionPolicy.CLASS) -> 디폴트 설정 클래스 로더가 읽을 때 어노테이션이 사라짐
@Retention(RetentionPolicy.RUNTIME) // RUNTIME으로 설정 시, 리플렉션으로 Class 타입을 통해 조회 가능해짐
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited // 상속이 되는 어노테이션으로 설정
public @interface MyAnnotation {
  String name() default "adsb";
  int number() default 11;
}
