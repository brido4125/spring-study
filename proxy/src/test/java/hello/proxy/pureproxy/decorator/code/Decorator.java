package hello.proxy.pureproxy.decorator.code;


/*
* proxy 역할을 수행할 클래스들이 extends 해서 사용하면 됨
* */
public abstract class Decorator {
  protected final Component component; // 프록시에서 실제 서버의 역할, 항상 호출되어야함 (호출 대상)

  public Decorator(Component component) {
    this.component = component;
  }
}
