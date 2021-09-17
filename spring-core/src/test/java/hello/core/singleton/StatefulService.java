package hello.core.singleton;

public class StatefulService {
    private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name);
        System.out.println("price = " + price);
        this.price = price;
    }

    /* 아래와 같은 방식으로 stateless 로 설계해야한다
    public int order(String name, int price) {
        return price;
    }
    * */

    public int getPrice() {
        return price;
    }
}
