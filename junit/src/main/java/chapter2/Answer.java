package chapter2;


/*
* 구직자에 대한 질무의 답을 가지는 객체
* Profile 객체에 추가가 가능하다
* */
public class Answer {
    private int i;
    private Question question;

    public Answer(Question question, int i) {
        this.question = question;
        this.i = i;
    }

    public Answer(Question question, String matchingValue) {
        this.question = question;
        this.i = question.indexOf(matchingValue);
    }

    public String getQuestionText() {
        return question.getText();
    }

    @Override
    public String toString() {
        return String.format("%s %s", question.getText(), question.getAnswerChoice(i));
    }

    public boolean match(int expected) {
        return question.match(expected, i);
    }

    public boolean match(Answer otherAnswer) {
        return question.match(i, otherAnswer.i);
    }

    public Question getCharacteristic() {
        return question;
    }
}
