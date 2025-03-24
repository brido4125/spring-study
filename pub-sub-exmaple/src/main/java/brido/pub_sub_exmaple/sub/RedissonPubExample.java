package brido.pub_sub_exmaple.sub;

import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonPubExample {
    public static void main(String[] args) {
        Config config = new Config();
        config.useClusterServers().addNodeAddress("redis://ncp-4c4-000:6379", "redis://ncp-4c4-001:6379", "redis://ncp-4c4-002:6379");
        RedissonClient client = Redisson.create(config);
        RTopic topic = client.getTopic("mytopic");
        topic.publish(new Message("Hello World"));
        client.shutdown();
    }
}
