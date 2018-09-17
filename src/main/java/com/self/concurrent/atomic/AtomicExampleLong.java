package com.self.concurrent.atomic;

import com.self.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Lucifer
 * @do 基于acs实现的线程安全
 * @date 2018/09/17 10:29
 */
@Slf4j
@ThreadSafe
public class AtomicExampleLong {

    //请求的总数
    public static int clientTotal = 5000;

    //线程的总数
    public static int threadTotal = 200;

    //用来观看并发结果的计数
    public static AtomicLong count = new AtomicLong();

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
                    add();
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

    private static void add(){
        //表示先增加然后在返回,同a++
        //count.incrementAndGet();
        //表示先返回然后再增加,同int操作中的++a
        //count.getAndIncrement();
    }

}
