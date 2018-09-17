package com.self.concurrent.sync;

import com.self.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Lucifer
 * @do 利用Synchronized关键字进行同步的操作
 * @date 2018/09/17 15:31
 */
@ThreadSafe
@Slf4j
public class SynchronizedExample {

    //利用synchronized锁代码
    public void test1(int j){
        synchronized (this){
            for (int i = 0; i < 10; i++) {
                log.info("the object :{} num is :{}", j, i);
            }
        }
    }

    //利用synchronized锁方法
    public synchronized  void test2(int j){
        for (int i = 0; i < 10; i++) {
            log.info("tne object :{} num is :{}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample synchronizedExample1 = new SynchronizedExample();
        SynchronizedExample synchronizedExample2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test1(1);
        });
        executorService.execute(() -> {
            synchronizedExample2.test1(2);
        });
        executorService.shutdown();
    }

}
