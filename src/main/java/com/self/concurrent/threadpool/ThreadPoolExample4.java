package com.self.concurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Lucifer
 * @do 关于线程池的测试类,.newScheduledThreadPool(5)
 * @date 2018/10/09 20:07
 */
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
       /* for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                log.info("the threadNum is : {}", threadNum);
            });
        }*/
       log.info("the time is start");
       /*executorService.schedule(new Runnable() {
           @Override
           public void run() {
               log.info("the task is done");
           }
       }, 3, TimeUnit.SECONDS); //该方法使线程延迟执行*/
    /*   executorService.scheduleAtFixedRate(new Runnable() {
           @Override
           public void run() {
               log.info("the task is done");
           }
       }, 1, 3, TimeUnit.SECONDS);  //表示再单位时间执行任务之后,按固定频率无限执行任务*/
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("the task is done");
            }
        }, new Date(), 5*1000); //  timer可同样实现对应的任务功能,但是jdk将其标记为不推荐,望谨慎使用

        executorService.shutdown();
    }

}
