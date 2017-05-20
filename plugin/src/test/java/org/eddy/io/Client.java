package org.eddy.io;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by eddy on 2017/5/20.
 */
@Getter
@Setter
public class Client implements Runnable{

    private Selector selector;
    private SocketChannel sc;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    {
        try {
            selector = Selector.open();
            sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress("127.0.0.1", 8081));
            sc.register(selector, SelectionKey.OP_CONNECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (atomicInteger.get() >= 3) {
                    System.out.println("end client");
                    break;
                }
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isConnectable()) {
                        handConnect(key);
                    } else if (key.isWritable()) {
                        handWrite(key);
                    } else if (key.isReadable()) {
                        handRead(key);
                        atomicInteger.incrementAndGet();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handWrite(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        sc.write(ByteBuffer.wrap("send to server".getBytes("UTF-8")));
        key.interestOps(SelectionKey.OP_READ);
    }

    private void handRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024); // 1K
        sc.read(byteBuffer);
        byteBuffer.flip();
        byte[] data = new byte[byteBuffer.limit()];
        byteBuffer.get(data);
        key.interestOps(SelectionKey.OP_WRITE);
        System.out.println("client read");
        System.out.println(new String(data, "UTF-8"));
    }

    private void handConnect(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        sc.finishConnect();
        sc.write(ByteBuffer.wrap("client send to server\n".getBytes("UTF-8")));
        sc.register(selector, SelectionKey.OP_WRITE);
        System.out.println("connect to server");
    }

    public static void main(String[] args) {
        Client client = new Client();
        Executors.newSingleThreadExecutor().submit(client);
    }
}
