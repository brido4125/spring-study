package hello.jdbc.connection;

//추상 클래스 -> 해당 객체 생성 방지
public abstract class ConnectionConst {
    public static final String URL = "jdbc:h2:tcp://localhost/~/jdbc";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";
}
