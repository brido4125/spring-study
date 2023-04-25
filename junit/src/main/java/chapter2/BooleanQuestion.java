package chapter2;

public class BooleanQuestion extends Question{

    public BooleanQuestion(int id, String questionText) {
        super(questionText, new String[]{"No", "Yes"}, id);
    }

    @Override
    public boolean match(int expected, int actual) {
        return false;
    }
}
