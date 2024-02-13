package me.brido;

import org.junit.Test;
import reflection.Book;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BookServiceTest {

  @Test
  public void proxy() {
    /*
    * JDK 다이나믹 프록시 -> InvocationHandler 구현체가 필요함
    * 또한 구체 클래스 타입의 프록시를 생성하지 못하는 단점
    * -> 이를 Spring AOP에서는 CGLIB을 채택해서 보완
    * */
    BookService bookService
            = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class}, new InvocationHandler() {

      BookService target = new BookService();
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("=============");
        Object invoke = method.invoke(target, args);
        System.out.println("=============");
        return invoke;
      }
    });
  }

  @Test
  public void cglib() {
    // CGLIB은 InvocationHandler가 아닌 MethodInterceptor를 구현해줘야 프록시 객체 생성 가능
  }

}
