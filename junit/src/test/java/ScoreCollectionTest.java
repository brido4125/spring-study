import chapter2.ScoreCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreCollectionTest {
    @Test
    void answerArithmeticMeanOfTwoNumbers() {
        // arrange
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        // act
        double actualResult = collection.arithmeticMean();

        // assert
        assertEquals(6, actualResult);
    }
}