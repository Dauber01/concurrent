package com.self.concurrent.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Lucifer
 * @do 单例的例子, 利用传统两种方式之一的懒汉式加载
 * @date 2018/09/17 20:55
 */
@Slf4j
public class SingletonExample1 {

    private SingletonExample1() {}

    //在此变量的位置增加volatile修饰,可以其下的顺序按顺序执行,这样可以保证达到安全的单例
    private static volatile SingletonExample1 singletonExample1 = null;

    public static SingletonExample1 getInstance(){
        if (singletonExample1 == null){//②
            synchronized (SingletonExample1.class){
                if (singletonExample1 == null){
                    //new 一个对象的顺序是
                    //1,在栈中创建一个内存空间
                    //2,在堆中初始化对象
                    //3,将对象的内存地址,指向栈中
                    //其中1肯定是最想执行的，但是2和3的顺序,没有符合happens-before原则,所以,它的发生时乱序的,
                    //  假设有两个线程,其中线程1执行到①的位置,在new的过程中先执行了3,然后执行了2,这是对象在栈中
                    //  的存储已经不是空了,所以会执行return,但是由于堆中的对象还没有初始化完毕,则返回的是null
                    return new SingletonExample1();//① 3,2
                }
            }
        }
        return singletonExample1;
    }

}
