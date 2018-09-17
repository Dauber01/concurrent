package com.self.concurrent.atomic;

import com.self.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lucifer
 * @do 基于acs实现的线程安全,AtomicBoolean的实现
 * @date 2018/09/17 10:29
 */
@Slf4j
@ThreadSafe
public class AtomicBooleanExample {

    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    //请求的总数
    public static int clientTotal = 5000;

    //线程的总数
    public static int threadTotal = 200;

    //用来观看并发结果的计数
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception{
        //创建对应的线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //建立对应的信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try{
                    semaphore.acquire();
                    test();
                    semaphore.release();
                }catch (InterruptedException ex){
                    log.error("exception : ", ex);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void test(){
        //其操作为线程安全的,只执行一次
        if (atomicBoolean.compareAndSet(false, true)){
            log.info("更新成功：boolean ,{}", atomicBoolean.get());
        }
    }

}
