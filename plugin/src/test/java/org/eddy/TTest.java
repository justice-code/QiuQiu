package org.eddy;

import java.util.concurrent.*;

/**
 * Created by eddy on 2017/5/6.
 */
public class TTest {

    private final static ExecutorService pool = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Future<String> future = pool.submit(() -> {
            Thread.sleep(2_000);
            System.out.println(123);
//            throw new RuntimeException("t");
            pool.submit(() -> {
                System.out.println("this is another thread");
            });
            return "OK";
        });


        System.out.println("abc");
        System.out.println(future.isDone());
//        future.cancel(true);
        System.out.println(future.get());
        System.out.println(pool);
        pool.shutdown();
    }

}