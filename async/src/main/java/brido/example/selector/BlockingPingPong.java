package brido.example.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class BlockingPingPong {

    static Integer count = 0;

    public static void main(String[] args) {
        try {
            // Redis 서버에 연결할 SocketChannel 생성 및 Blocking 모드로 설정
            SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 6379));
            channel.configureBlocking(true);

            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 10000) { // 1초 동안 반복
                // Redis 서버에 PING 명령 전송
                channel.write(ByteBuffer.wrap("PING\r\n".getBytes()));

                // 데이터 읽기 처리
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (channel.read(buffer) > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);
//                    System.out.println("Received: " + new String(bytes));
                    count += 1;
                }
            }

            // 작업 완료 후 자원 해제
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("count = " + count);
    }
}
