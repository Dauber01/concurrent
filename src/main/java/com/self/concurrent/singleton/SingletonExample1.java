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

    private static SingletonExample1 singletonExample1 = null;

    public static SingletonExample1 getInstance(){
        if (singletonExample1 == null){
            synchronized (this){
                return new SingletonExample1();
            }
        }
        return singletonExample1;
    }

}
