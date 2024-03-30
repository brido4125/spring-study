package brido.example.selector;

import org.apache.zookeeper.server.admin.JsonOutputter;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class RedisNioClient {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("localhost", 6379));
        channel.register(selector, SelectionKey.OP_CONNECT);

        long currentTime = System.currentTimeMillis();
        long pingCount = 0;

        while (System.currentTimeMillis() < currentTime + 1000) {
            // 연결된 상태라면 PING 명령을 보냄
            if (channel.isConnected() && !channel.socket().isClosed()) {
                channel.register(selector, SelectionKey.OP_WRITE);
            }

            selector.select(5000); // 5초 타임아웃 설정

            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                keyIterator.remove();

                if (key.isConnectable()) {
                    System.out.println("key = " + key);
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    if (socketChannel.finishConnect()) {
                        // 연결 성공 후, PING 명령을 위해 쓰기 상태 등록
                        socketChannel.register(selector, SelectionKey.OP_WRITE);
                    }
                } if (key.isReadable()) {
                    System.out.println("RedisNioClient.main - read");
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int bytesRead = socketChannel.read(buffer);
                    if (bytesRead > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.limit()];
                        buffer.get(bytes);
                        System.out.println("Received: " + new String(bytes));
                    }
                } else if (key.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // PING 명령 보내기
                    socketChannel.write(ByteBuffer.wrap("PING\r\n".getBytes()));
                    // 다시 읽기 상태로 전환
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
            }
        }
    }
}
