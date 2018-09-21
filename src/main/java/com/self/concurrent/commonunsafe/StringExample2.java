package com.self.concurrent.commonunsafe;

import com.self.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Lucifer
 * @do 用模拟并发的测试类,用来测试stringBuffer在高并发中的线程安全
 * @date 2018/09/13 16:05
 */
@Slf4j
@ThreadSafe
public class StringExample2 {

    //请求的总数
    public static int clientTotal = 5000;

    //线程的总数
    public static int threadTotal = 200;

    //用来观看并发结果的计数,因为simpleDateFormat为非线程安全的类，所以使用和引用该类的时候,一定要进行线程保护措施,比如将其
    //  的作用域固定在方法域中,即将其声明在方法内,利用局部变量的线程保护策略来实现线程安全
    //private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

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
        //log.info("stringBuilder.length:{}", stringBuffer.length());
    }

    private static void append(String i){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = simpleDateFormat.parse("20180905");
            log.info("the date time is {}: {}", i, date);
        }catch (ParseException pe){
            log.error("the exception is : ", pe);
        }
    }

}
