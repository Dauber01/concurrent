package com.self.concurrent.commonunsafe;

import com.self.concurrent.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Lucifer
 * @do 用模拟并发的测试类,用来测试Collections在高并发中的线程安全
 * @date 2018/09/13 16:05
 */
@Slf4j
@NotThreadSafe
public class CollectionsExample {

    //请求的总数
    public static int clientTotal = 5000;

    //线程的总数
    public static int threadTotal = 200;

    //用Collections.synchronizedList处理过的List在concurrency环境下,转换为同步容器
    private static List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());

    //用Collections.synchronizedSet处理过的set在concurrency环境下,转换为同步容器
    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());

    //用Collections.synchronizedMap处理过的，map在concurrency环境下,转换为同步容器
    private static Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<Integer, Integer>());

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
                    append(j);
                    semaphore.release();
                }catch (InterruptedException ex){
                    log.error("exception : ", ex);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("list.size:{}", list.size());
        log.info("set.size:{}", set.size());
        log.info("map.size:{}", map.size());
    }

    private static void append(int i){
        list.add(i);
        set.add(i);
        map.put(i, i);
    }

}
