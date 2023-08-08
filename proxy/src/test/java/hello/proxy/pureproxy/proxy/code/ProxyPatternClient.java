package hello.proxy.pureproxy.proxy.code;

public class ProxyPatternClient {

    private Subject server; //cient는 해당 인스턴스가 proxy인지 real 서버인지 모름

    public ProxyPatternClient(Subject server) {
        this.server = server;
    }

    public void excute() {
        server.operation();
    }
}
