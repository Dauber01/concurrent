package com.self.concurrent.dealLock;

import lombok.extern.slf4j.Slf4j;

/**
 * 死锁实例的测试类,通过两个线程的锁,对死锁现象进行实现,由于每个线程各持有一个资源,而且在处理的过程中申请对方的资源,同时不释放持有资源,导致死锁；
 * 解决死锁的三个方案分别是,①按顺序进行加锁,例如o1和o2的锁,可以在顺序上进行控制，让其同时获取对应的锁,例如同时获取o1的锁或者同时获取o2的锁；
 *  ②设置加锁等待时间,时间到了之后释放锁,使用lock的实现,③通过监控死锁的情况,通过对锁的操作过程,比如加锁,和死锁的数据用数据结构进行存储,当有过
 *  的阻塞现象的时候进行释放锁,让其重复竞争,同时在高并发的情况下,需要设置线程的优先级,如果优先级高则释放锁,低的则继续处于锁定状态
 *  在操作锁的附近要尽量详细的记录日志,这样如果发生死锁,则可通过日志查询对应的详细情况
 * @author Lucifer
 * @date 2018/10/10 14:15
 */
@Slf4j
public class DeadLock implements Runnable{

    public int flag = 1;

    //静态对象是类中的所有对象共享的
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        log.info("flag : {}", flag);
        if (flag == 1){
            synchronized (o1){
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (o2){
                    log.info(String.valueOf(1));
                }
            }
        }
        if (flag == 0){
            synchronized (o2){
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (o1){
                    log.info(String.valueOf(2));
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock dl1 = new DeadLock();
        DeadLock dl2 = new DeadLock();
        dl1.flag = 0;
        dl2.flag = 1;
        new Thread(dl1).start();
        new Thread(dl2).start();
    }

}
