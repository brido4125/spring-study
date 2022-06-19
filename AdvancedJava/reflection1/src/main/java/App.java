import java.util.Arrays;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<Book> bookClass = Book.class;//Class 로딩만해도 생기는 Instance

        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);

        System.out.println(MyBook.class.getSuperclass());

        Book book = new Book();
        Class<? extends Book> bookClass1 = book.getClass();
        Class<?> forName = Class.forName("Book");
    }
}
