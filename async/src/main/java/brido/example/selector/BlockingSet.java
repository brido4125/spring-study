package brido.example.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class BlockingSet {

    static Integer count = 0;
    private static final String SET_BASE = "*3\r\n$3\r\nSET\r\n";
    private static final String SET_KEY = "$43\r\ntoLongestMyKey123456789year2024month04day02\r\n";
    private static final String SET_VALUE = "$7\r\nmyValue\r\n";

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        sb.append(SET_BASE + SET_KEY + SET_VALUE);

        try {
            // Redis 서버에 연결할 SocketChannel 생성 및 Blocking 모드로 설정
            SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 6379));
            channel.configureBlocking(true);

            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 1000) { // 1초 동안 반복
                // Redis 서버에 PING 명령 전송
                channel.write(ByteBuffer.wrap(sb.toString().getBytes()));

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
