import java.util.List;

/*Todo : 고객은 요리사에게 요리를 주문 할 수 있다, 메뉴를 통해 주문해야 한다.
* */
public class Client {

    private Menu menu;
    private Long id; // 고객 식별자

    public Client() {
        this.id = IdGenerator.generate();
    }

    /*
    * 주문 후, Client는 반환 값으로 생성된 요리 객체 Dish를 받는다.
    * */
    private Dish makeOrder(String dishName) {
        System.out.println(this.id + "번 고객은 " + dishName + "를 주문했다.");
        this.menu = new Menu(dishName);
        return this.menu.getShef().getDish();
    }

    private List<TableWare> getTableWareList() {
        return this.menu.getTableWareList();
    }

    private Long getId() {
        return this.id;
    }

    public static void main(String[] args) {
        orderSeq("김치찌개");
        orderSeq("짜장면");
    }

    private static void orderSeq(String name) {
        Client client = new Client();
        Dish dish = client.makeOrder(name);
        assert dish.getDishName().equals(name);
        System.out.println(client.getId() + "번 고객은 주문한 " + dish.getDishName() + "를 받았다.");

        List<TableWare> tableWareList = client.getTableWareList();
        for (TableWare tableWare : tableWareList) {
            System.out.println("고객은 " + tableWare + "를 사용 가능하다.");
        }
    }
}
