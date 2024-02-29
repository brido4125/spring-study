package brido.example.testcontainerredis.arcus;


import net.spy.memcached.ArcusClient;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.DefaultConnectionFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;
import java.util.Map;

public class NodeTest {
  static GenericContainer arcus
          = new GenericContainer("jam2in/arcus-memcached").withExposedPorts(11211);


  private ArcusClient arcusClient;

  @BeforeAll
  static void beforeAll() {
    arcus.start();
  }

  @AfterAll
  static void afterAll() {
    arcus.stop();
  }

  @BeforeEach
  void beforeEach() throws IOException {
    arcusClient = new ArcusClient(new ConnectionFactoryBuilder().build(),
            List.of(new InetSocketAddress(arcus.getHost(), arcus.getFirstMappedPort())));
  }

  @Test
  void test() {
    Assertions.assertThat(arcus.isRunning()).isTrue();
    Map<SocketAddress, String> versions = arcusClient.getVersions();
    System.out.println("versions = " + versions);
  }
}
