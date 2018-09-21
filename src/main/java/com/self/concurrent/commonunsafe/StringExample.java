package com.self.concurrent.commonunsafe;

import com.self.concurrent.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Lucifer
 * @do 用模拟并发的测试类,用来测试stringBuilder在高并发中的线程安全
 * @date 2018/09/13 16:05
 */
@Slf4j
@NotThreadSafe
public class StringExample {

    //请求的总数
    public static int clientTotal = 5000;

    //线程的总数
    public static int threadTotal = 200;

    //用来观看并发结果的计数
    public static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws Exception{
        //创建对应的线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //建立对应的信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int j = i;
            executorService.execute(() -> {
                try{
                    semaphore.acquire();
                    append(String.valueOf(j));
                    semaphore.release();
                }catch (InterruptedException ex){
                    log.error("exception : ", ex);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("stringBuilder.length:{}", stringBuilder.length());
    }

    private static void append(String i){
        stringBuilder.append("1");
    }

}
