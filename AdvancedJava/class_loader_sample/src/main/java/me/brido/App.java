package me.brido;

public class App 
{
    /**
     * Class Loader System
     * 1) setting static value => Initialization
     * 2) read the class file => Loading
     * 3) Linking the reference => Linking
     */

    static String myName = "changsub";
    static {
        int num = Integer.MIN_VALUE;
    }

    public static void main( String[] args ) throws ClassNotFoundException {
        ClassLoader classLoader = App.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());//native-code로 구현되어서 null 출력

        //loadClass 메서드는 부모가 먼저 수행하고 자신이 수행하고 그래도 없으면 ClassNotFoundException(Checked) 발생
        Class<?> aClass = classLoader.loadClass("me.brido.App");
        System.out.println("aClass.getName() = " + aClass.getName());
    }
}
