package hello.core2.lifecycle;

import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Setter
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 url = " + url);
        connect();
        call("초기화 연결 메세지 from constructor");
    }

    //Starting Service Method
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + "message" + message);
    }

    //Terminating Service Method
    public void disconnect() {
        System.out.println("close :" + url);
    }

    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지 from constructor");
    }

    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }

    /* @Bean (initMethod = "init", destroyMethod = "close"), Bean Method를 사용하는 방법
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지 from constructor");
    }

    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }
    */

    /*
    implements InitializingBean, DisposableBean 를 사용하는 방법 => 구식
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메세지 from constructor");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }
    */
}
