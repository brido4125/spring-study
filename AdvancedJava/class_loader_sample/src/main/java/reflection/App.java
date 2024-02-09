package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
  public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
    Class<?> aClass = Class.forName("reflection.Book");
    Constructor<?> constructor = aClass.getConstructor(String.class);
    Book o = (Book) constructor.newInstance("hello");
    o.printA();

    Field b = Book.class.getDeclaredField("b");
    b.setAccessible(true);
    Object o1 = b.get(o); // 미리 생성된 오브젝트의 field를 얻어오고 싶을 경우 인자로 생성된 오브젝트의 레퍼런스 넣어주면 됨
    System.out.println("o1 = " + o1);


    Method printA = Book.class.getDeclaredMethod("printA");
    printA.invoke(o); // invoke 시에도 실제로 메서드를 호출할 대상 오브젝트가 필요함

    //primitive / wrapper 클래스 구별함
    Method sum = Book.class.getDeclaredMethod("sum", int.class, int.class);
    Object res = sum.invoke(o, 1, 2);
    System.out.println("res = " + res);
  }
}
