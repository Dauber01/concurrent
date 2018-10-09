package com.self.concurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Lucifer
 * @do 关于线程池的测试类,.newSingleThreadExecutor()用固定的单个线程进行处理,可以保证线程的执行顺序
 * @date 2018/10/09 20:07
 */
@Slf4j
public class ThreadPoolExample3 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                log.info("the threadNum is : {}", threadNum);
            });
        }
        executorService.shutdown();
    }

}
