package com.self.concurrent.commonunsafe;

import com.self.concurrent.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Lucifer
 * @do 用模拟并发的测试类,用来测试Vector在高并发中的线程安全
 * @date 2018/09/13 16:05
 */
@Slf4j
@NotThreadSafe
public class VectorExample2 {

    /**
     * vector在操作的时候,多线程中,获取的执行到①的位置,删除执行到②的位置,则在继续获取的时候会报数组下标越界的问题
     *  所以要主要,在用多线程操作同步容器的时候要注意原子性的保持.
     */
    private static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[] args) throws Exception{
       while (true){
           for (int i = 0; i < 10; i++) {
               vector.add(i);
           }
           Thread thread1 = new Thread(){
               @Override
               public void run(){
                   for (int i = 0; i < vector.size(); i++) {   //①,执行get i的操作,为之后的做准备
                       vector.get(i);
                   }
               }
           };
           Thread thread2 = new Thread(){
             @Override
             public void run(){
                 for (int i = 0; i < vector.size(); i++) {
                     vector.remove(i);  //②,执行remove操作
                 }
             }
           };
           thread1.start();
           thread2.start();
       }
    }

}
