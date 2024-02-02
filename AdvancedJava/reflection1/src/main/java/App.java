import java.util.Arrays;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<Book> bookClass = Book.class;//Class 로딩만해도 생기는 Instance

        //Class<?> 타입의 인스턴스를 가져오는 법
        /**
         * 1. Book.class
         * 2. book.getClass()
         * 3. Class.forName("src.main.java.Book") -> FQCN
         */

        // getFields -> public한 필드만 가져옴
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);


        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);

        System.out.println(MyBook.class.getSuperclass());

        Book book = new Book();
        Class<? extends Book> bookClass1 = book.getClass();
        Class<?> forName = Class.forName("Book");
    }
}
