package modern.java.in.action.examples.chapter4;

public class Dish {
    private int calories;

    private String name;

    private Type type;

    private boolean vegetarian;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.calories = calories;
        this.type = type;
        this.vegetarian = vegetarian;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public static boolean isVegetarian(Dish dish) {
        return dish.vegetarian;
    }

    public enum Type {
        MEAT, FISH, OTHER
    }

}
