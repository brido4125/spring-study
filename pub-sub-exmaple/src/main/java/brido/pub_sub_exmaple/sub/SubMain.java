package brido.pub_sub_exmaple.sub;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.cluster.pubsub.StatefulRedisClusterPubSubConnection;
import io.lettuce.core.cluster.pubsub.api.async.RedisClusterPubSubAsyncCommands;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import io.lettuce.core.resource.*;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorGroup;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class SubMain {

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

        RedisClusterClient client1 = RedisClusterClient.create(Arrays.asList(uri1, uri2, uri3));
        StatefulRedisClusterPubSubConnection<String, String> conn1 = client1.connectPubSub();
        RedisClusterPubSubAsyncCommands<String, String> cmd1 = conn1.async();
        cmd1.set("a", "world");
        cmd1.set("b", "world");
        cmd1.set("c", "world");

        RedisClusterClient client2 = RedisClusterClient.create(Arrays.asList(uri1, uri2, uri3));
        StatefulRedisClusterPubSubConnection<String, String> conn2 = client2.connectPubSub();
        RedisClusterPubSubAsyncCommands<String, String> cmd2 = conn2.async();
        cmd2.set("a", "world");
        cmd2.set("b", "world");
        cmd2.set("c", "world");

//        ClusterTopologyRefreshOptions options = ClusterTopologyRefreshOptions.builder()
//                .enablePeriodicRefresh()
//                .build();
//        client.setOptions(ClusterClientOptions.builder()
//                .topologyRefreshOptions(options)
//                .autoReconnect(true)
//                .socketOptions(SocketOptions.builder().connectTimeout(Duration.ofSeconds(10)).build())
//                .build());
////
//        StatefulRedisClusterPubSubConnection<String, String> conn = client.connectPubSub();
//
//        RedisPubSubAdapter<String, String> adapter = new RedisPubSubAdapter<>() {
//            @Override
//            public void message(String channel, String message) {
//                System.out.println("MSG Thread.currentThread().getName() = " + Thread.currentThread().getName());
//                System.out.println(message);
//            }
//        };
//
//        conn.addListener(adapter);
//
//        RedisPubSubAsyncCommands<String, String> cmd = conn.async();
//        cmd.set("a", "result").whenComplete((r, e) -> {
//            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
//        });
//        cmd.set("b", "result").whenComplete((r, e) -> {
//            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
//        });
//        cmd.set("c", "result").whenComplete((r, e) -> {
//            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
//        });
//
//        cmd.subscribe("a");
//        cmd.subscribe("b");
//        cmd.subscribe("c");
//
//        cmd.publish("a", "result");

        Thread.sleep(10000);

//        StatefulRedisClusterPubSubConnection<String, String> conn2 = client.connectPubSub();
//        conn2.addListener(adapter);
//        RedisClusterPubSubAsyncCommands<String, String> cmd2 = conn2.async();
//
//        cmd2.set("a", "result");
//        cmd2.set("b", "result");
//        cmd2.set("c", "result");
//        cmd2.subscribe("a");
//        cmd2.subscribe("b");
//        cmd2.subscribe("c");
//        Thread.sleep(5000);
    }
}
