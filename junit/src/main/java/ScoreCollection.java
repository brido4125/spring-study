import java.util.ArrayList;
import java.util.List;

public class ScoreCollection {
    private List<Scoreable> scores = new ArrayList<>();

    public void add(Scoreable scoreable) {
        scores.add(scoreable);
    }

    public double arithmeticMean() {
        double total = scores.stream()
                .mapToDouble(Scoreable::getScore)
                .sum();
        return total / scores.size();
    }
}
