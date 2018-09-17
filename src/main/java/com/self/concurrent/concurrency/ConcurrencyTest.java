package com.self.concurrent.concurrency;

import com.self.concurrent.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Lucifer
 * @do 用模拟并发的测试类,该类当前的实现运行时会导致本地的idea的jvm内存溢出,怀疑为线程创建之后并没有关闭,???
 * @date 2018/09/13 16:05
 */
@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    //请求的总数
    public static int clientTotal = 5000;

    //线程的总数
    public static int threadTotal = 200;

    //用来观看并发结果的计数
    public static int count = 0;

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

    private static synchronized void add(){
        count++;
    }

}
