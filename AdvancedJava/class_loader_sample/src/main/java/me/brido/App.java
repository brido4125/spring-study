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

    public static void main( String[] args )
    {
        ClassLoader classLoader = App.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());//native-code로 구현되어서 null 출력
    }
}
