import chapter2.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

public class ProfileTest {

    @Test
    void test() {
        Profile profile = new Profile("Bull Hockey, Inc.");
        Question question = new BooleanQuestion(1, "Got bonuses?");
        Criteria criteria = new Criteria();
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);

        criteria.add(criterion);
    }
}
