package brido.pub_sub_exmaple.sub;

import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonSubExample {
    public static void main(String[] args) {
        Config config = new Config();
        config.useClusterServers().addNodeAddress("redis://ncp-4c4-000:6379", "redis://ncp-4c4-001:6379", "redis://ncp-4c4-002:6379");
        RedissonClient client = Redisson.create(config);
        RTopic topic = client.getTopic("mytopic");
        topic.addListener(Message.class,((charSequence, message) -> {
            System.out.println("message.getContent() = " + message.getContent());
        }));
        try {
            Thread.sleep(30000); // 10초 동안 실행 유지
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.shutdown();
    }

}
