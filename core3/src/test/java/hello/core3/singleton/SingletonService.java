package hello.core3.singleton;

/*
* Singleton Pattern
* */
public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {}

    public void logic() {
        System.out.println("싱글톤 객첼 로직 호출");
    }
}
