package hello.core2.singleton;

//객체를 미리 생성해두는 pattern
public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    //외부 호출 금지
    private SingletonService() {
    }

    public void logic() {
        System.out.println("call singleton instance");
    }
}
