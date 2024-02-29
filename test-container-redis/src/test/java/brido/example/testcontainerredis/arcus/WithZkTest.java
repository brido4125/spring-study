package brido.example.testcontainerredis.arcus;

import net.spy.memcached.ArcusClient;
import net.spy.memcached.ArcusClientPool;
import net.spy.memcached.ConnectionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;
import java.net.SocketAddress;
import java.util.Map;

public class WithZkTest {
  static DockerComposeContainer arcus
          = new DockerComposeContainer(new File("docker-compose.yaml"));

  private ArcusClientPool arcusClient;

  @BeforeAll
  static void beforeAll() {
    arcus.start();
  }

  @AfterAll
  static void afterAll() {
    arcus.stop();
  }

  @BeforeEach
  void beforeEach() {
    arcusClient = ArcusClient.createArcusClientPool("test", new ConnectionFactoryBuilder(), 3);
  }

  @Test
  void test() {
    Map<SocketAddress, Map<String, String>> stats = arcusClient.getStats();
    System.out.println("stats = " + stats);
  }

}
