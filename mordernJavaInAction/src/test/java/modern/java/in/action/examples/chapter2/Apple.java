package modern.java.in.action.examples.chapter2;

public class Apple {
    private Color color;

    private Integer weight;

    public Apple() {

    }

    public Apple(Integer integer) {
        this.weight = integer;
    }

    public Color getColor() {
        return color;
    }

    public Integer getWeight() {
        return weight;
    }

    public Apple(Color color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }
}
