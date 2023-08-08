package hello.proxy.pureproxy.decorator.code;


/*
* proxy 역할을 수행할 클래스
* */
public abstract class Decorator {
  protected final Component component; // 프록시에서 실제 서버의 역할, 항상 호출되어야함

  public Decorator(Component component) {
    this.component = component;
  }
}
