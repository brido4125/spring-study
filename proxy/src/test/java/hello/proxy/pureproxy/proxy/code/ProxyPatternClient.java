package hello.proxy.pureproxy.proxy.code;

public class ProxyPatternClient {

    private Subject server;

    public ProxyPatternClient(Subject server) {
        this.server = server;
    }

    public void excute() {
        server.operation();
    }
}
