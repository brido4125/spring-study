package reflection;

public class Book {
  public static String a = "A";
  private String b = "B";

  public Book(String b) {
    this.b = b;
  }

  public void printA() {
    System.out.println("a = " + a);
  }

  public int sum(int a, int b) {
    return a + b;
  }
}
