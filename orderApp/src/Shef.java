import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Shef {

    private FoodType foodType;
    private Dish dish;
    private String dishName;

    //Todo : Shef Pool을 만들어 매번 Shef 객체를 생성하지 않도록 구현하기
    public static HashMap<FoodType, Queue<Shef>> shefPoolMap = new HashMap<>();

    public static Shef getShef(FoodType foodType) {
        //Pool Table에 해당 FoodType이 있는지 확인
        if(shefPoolMap.containsKey(foodType)) {
            /*Pool 내의 Worker가 모두 소진된 경우 -> 새롭게 하나 생성*/
            if(shefPoolMap.get(foodType).isEmpty()) {
                return new Shef(foodType);
            }
        } else {
            /*
            * 각 FoodType 별 PoolSize는 3으로 설정
            * */
            Queue<Shef> shefPool = new LinkedList<>();
            for (int i = 0; i < 3; i++) {
                shefPool.offer(new Shef(foodType));
            }
            shefPoolMap.put(foodType,shefPool);
        }
        return shefPoolMap.get(foodType).poll();
    }

    public Shef(FoodType foodType) {
        this.foodType = foodType;
    }

    public void startMakingDish(String dishName) {
        this.dishName = dishName;
        this.dish = new Dish(dishName);
        System.out.println(foodType + "요리사가 " + this.dishName + " 주문을 받았다.");
    }

    public Dish getDish() {
        System.out.println(foodType + "요리사가 " + this.dishName + "를 만들었습니다.");
        return this.dish;
    }
}
