package org.eddy.io;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by eddy on 2017/5/20.
 */
@Getter
@Setter
public class Server implements Runnable {

    private ServerSocketChannel ssc;
    private Selector selector;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    {
        try {
            selector = Selector.open();
            ssc = ServerSocketChannel.open().bind(new InetSocketAddress(8081), 500);
            ssc.configureBlocking(false);
            ssc.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (atomicInteger.get() >= 3) {
                    System.out.println("end server");
                    break;
                }
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        handAccept(key);
                    } else if (key.isWritable()) {
                        handWrite(key);
                        atomicInteger.incrementAndGet();
                    } else if (key.isReadable()) {
                        handRead(key);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024); // 1k
        sc.read(byteBuffer);
        byteBuffer.flip();
        byte[] data = new byte[byteBuffer.limit()];
        byteBuffer.get(data);
        key.interestOps(SelectionKey.OP_WRITE);
        System.out.println("server read");
        System.out.println(new String(data, "UTF-8"));

    }

    private void handWrite(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        sc.write(ByteBuffer.wrap("server send to client\n".getBytes("UTF-8")));
        key.interestOps(SelectionKey.OP_READ);
    }

    private void handAccept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);
        System.out.println("accept client");
    }

    public static void main(String[] args) {
        Server server = new Server();
        Executors.newSingleThreadExecutor().submit(server);
    }
}
