package brido.pub_sub_exmaple.sub;

import io.lettuce.core.RedisURI;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.pubsub.StatefulRedisClusterPubSubConnection;
import io.lettuce.core.cluster.pubsub.api.async.RedisClusterPubSubAsyncCommands;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import io.lettuce.core.resource.DefaultClientResources;
import io.lettuce.core.resource.DefaultEventLoopGroupProvider;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class PoolMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
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
//
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(5);
        config.setMaxTotal(5);
        config.setMinIdle(5);
        GenericObjectPool pool = ConnectionPoolSupport.createGenericObjectPool(client::connectPubSub, config);
        try {
            for (int i = 0; i < 10; i++) {
                StatefulRedisClusterPubSubConnection conn = (StatefulRedisClusterPubSubConnection) pool.borrowObject();
                System.out.println("conn = " + conn);
                RedisClusterPubSubAsyncCommands cmd = conn.async();
                cmd.set("a" + i, "result").whenComplete((r, e) -> {
                    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                });
                cmd.set("b" + i, "result").whenComplete((r, e) -> {
                    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                });
                cmd.set("c" + i, "result").whenComplete((r, e) -> {
                    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Thread.sleep(10000);

    }
}
