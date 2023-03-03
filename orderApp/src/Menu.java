import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/*
* Menu를 부모 클래스로 만들고 자식 클래스를 한식,중식,양식 등으로 두지 않은 이유 : 메뉴가 추가될 때마다 자식 클래스를 추가해야 하기 때문입니다.
* Map을 통해 한번에 관리하면 성능적인 면에서 효율적이라 생각했습니다.
* */
public class Menu {
    private static List<TableWare> tableWareList;

    private String dishName;
    private FoodType foodType;
    private Shef shef;


    static ConcurrentHashMap<String,FoodType> foodTypeCache = new ConcurrentHashMap<>();

    private void setFoodType(String target) {
        if(foodTypeCache.containsKey(target)) {
            this.foodType = foodTypeCache.get(target);
        } else {
            switch (target) {
                case "김치찌개":
                case "불고기":
                case "된장찌개":
                    this.foodType = FoodType.KOREAN;
                    foodTypeCache.put(target, foodType);
                    break;
                case "짜장면":
                case "짬뽕":
                case "탕수육":
                    this.foodType = FoodType.CHINESE;
                    foodTypeCache.put(target, foodType);
                    break;
                case "스테이크":
                case "햄버거":
                case "샐러드":
                    this.foodType = FoodType.WESTERN;
                    foodTypeCache.put(target, foodType);
                    break;
                case "피자":
                case "파스타":
                case "스파게티":
                    this.foodType = FoodType.ITALIAN;
                    foodTypeCache.put(target, foodType);
                    break;
                case "초밥":
                case "우동":
                case "라멘":
                    this.foodType = FoodType.JAPANESE;
                    foodTypeCache.put(target, foodType);
                    break;
                case "타코":
                case "데리야끼":
                    this.foodType = FoodType.MEXICAN;
                    foodTypeCache.put(target, foodType);
                    break;
                case "냉면":
                case "냉모밀":
                case "냉국수":
                    this.foodType = FoodType.ASIAN;
                    foodTypeCache.put(target, foodType);
                    break;
                case "커리":
                case "카레":
                    this.foodType = FoodType.INDIAN;
                    foodTypeCache.put(target, foodType);
                    break;
                default:
                    this.foodType = FoodType.OTHER;
                    break;
            }
        }
    }

    private void setTableWareList() {
        if (this.foodType == FoodType.KOREAN) {
            tableWareList = List.of(TableWare.CHOPSTICKS,TableWare.SPOON);
        } else if (this.foodType == FoodType.CHINESE) {
            tableWareList = List.of(TableWare.CHOPSTICKS);
        } else if (this.foodType == FoodType.JAPANESE) {
            tableWareList = List.of(TableWare.CHOPSTICKS,TableWare.SPOON);
        } else if (this.foodType == FoodType.WESTERN) {
            tableWareList = List.of(TableWare.FORK, TableWare.KNIFE);
        } else if (this.foodType == FoodType.ITALIAN) {
            tableWareList = List.of(TableWare.FORK, TableWare.KNIFE);
        }
    }

    public Menu(String dishName) {
        this.dishName = dishName;
        setFoodType(dishName);
        setTableWareList();
        this.shef = Shef.getShef(this.foodType);
        shef.startMakingDish(this.dishName);
    }

    public List<TableWare> getTableWareList() {
        return tableWareList;
    }

    public Shef getShef() {
        return shef;
    }
}
