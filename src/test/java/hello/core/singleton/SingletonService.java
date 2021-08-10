package hello.core.singleton;

public class SingletonService {
    //static 객체라서 클래스레벨로 딱 한개만 생성된다.
    //Lazy-init 방식은 아니다 => static 으로 바로 생성해버림
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    //Using Private Constructor to prevent making new instance
    private SingletonService() {}

    public void logic() {
        System.out.println("singleton logic");
    }

}
