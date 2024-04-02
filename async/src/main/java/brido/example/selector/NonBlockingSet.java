package brido.example.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NonBlockingSet {
    static Integer count = 0;
    private static final String SET_BASE = "*3\r\n$3\r\nSET\r\n";
    private static final String SET_KEY = "$43\r\ntoLongestMyKey123456789year2024month04day02\r\n";
    private static final String SET_VALUE = "$7\r\nmyValue\r\n";
    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        sb.append(SET_BASE + SET_KEY + SET_VALUE);
        String cmd = sb.toString();
        // Selector 생성
        Selector selector = Selector.open();

        // Redis 서버에 연결할 SocketChannel 생성
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false); // non-blocking 모드로 설정
        channel.connect(new InetSocketAddress("localhost", 6379)); // Redis 서버 주소

        // 연결 작업을 위한 SelectionKey 등록
        channel.register(selector, SelectionKey.OP_CONNECT);

        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + 1000) {
            selector.select(); // 준비된 이벤트가 있는지 확인

            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();

                if (key.isConnectable()) {
                    // 연결 완료 처리
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    if (socketChannel.finishConnect()) {
                        // 연결 성공 후, 읽기 작업을 위한 SelectionKey 등록
                        socketChannel.register(selector, SelectionKey.OP_WRITE);
                        // Redis 서버에 PING 명령 전송
                        socketChannel.write(ByteBuffer.wrap(cmd.getBytes()));
                    }
                } else if (key.isReadable()) {
                    // 데이터 읽기 처리
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int bytesRead = socketChannel.read(buffer);
                    if (bytesRead > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.limit()];
                        buffer.get(bytes);
//                        System.out.println("Received: " + new String(bytes));
                        count += 1;
                        socketChannel.register(selector, SelectionKey.OP_WRITE);
                    }
                } else if (key.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // PING 명령 보내기
                    socketChannel.write(ByteBuffer.wrap(cmd.getBytes()));
                    // 다시 읽기 상태로 전환
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
            }
        }
        System.out.println("count = " + count);
    }
}
