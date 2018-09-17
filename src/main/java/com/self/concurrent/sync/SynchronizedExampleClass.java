package com.self.concurrent.sync;

import com.self.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Lucifer
 * @do 用synchronized进行处理线程安全
 * @date 2018/09/17 15:42
 */
@Slf4j
@ThreadSafe
public class SynchronizedExampleClass {

    //利用synchronized锁代码
    public void test1(int j){
        synchronized (SynchronizedExampleClass.class){
            for (int i = 0; i < 10; i++) {
                log.info("the object :{} num is :{}", j, i);
            }
        }
    }

    //利用synchronized锁方法
    public static synchronized void test2(int j){
        for (int i = 0; i < 10; i++) {
            log.info("tne object :{} num is :{}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample synchronizedExample1 = new SynchronizedExample();
        SynchronizedExample synchronizedExample2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test2(1);
        });
        executorService.execute(() -> {
            synchronizedExample2.test2(2);
        });
        executorService.shutdown();
    }

}
