package brido.pub_sub_exmaple.sub;

import io.lettuce.core.RedisURI;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;

import java.time.Duration;
import java.util.Arrays;

public class SubMain {

    public static void main(String[] args) throws InterruptedException {
        RedisURI uri1 = RedisURI.Builder
                .redis("ncp-4c4-000", 6379)
                .build();

        RedisURI uri2 = RedisURI.Builder
                .redis("ncp-4c4-001", 6379)
                .build();

        RedisURI uri3 = RedisURI.Builder
                .redis("ncp-4c4-002", 6379)
                .build();


        RedisClusterClient client = RedisClusterClient.create(Arrays.asList(uri1, uri2, uri3));

        ClusterTopologyRefreshOptions options = ClusterTopologyRefreshOptions.builder()
                .enablePeriodicRefresh()
                .build();
        client.setOptions(ClusterClientOptions.builder()
                .topologyRefreshOptions(options)
                .autoReconnect(true)
                .socketOptions(SocketOptions.builder().connectTimeout(Duration.ofSeconds(10)).build())
                .build());

        StatefulRedisPubSubConnection<String, String> conn = client.connectPubSub();

        RedisPubSubAdapter<String, String> adapter = new RedisPubSubAdapter<>() {
            @Override
            public void message(String channel, String message) {
                System.out.println(message);
            }
        };

        conn.addListener(adapter);

        RedisPubSubAsyncCommands<String, String> cmd = conn.async();
        cmd.subscribe("test");

        Thread.sleep(10000);
    }
}
