package boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RemoteService {

  @RestController
  public static class MyController {

    @GetMapping("/service")
    public String rest(String req) throws InterruptedException {
      Thread.sleep(2000); // 외부 서비스 호출 시 2조 정도 소요
      return req + "/service";
    }
  }

  public static void main(String[] args) {
    System.setProperty("server.port", "8081");
    System.setProperty("server.tomcat.max-threads", "1000");

    SpringApplication.run(RemoteService.class, args);
  }
}
