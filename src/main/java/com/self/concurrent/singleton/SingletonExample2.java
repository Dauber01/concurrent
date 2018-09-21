package com.self.concurrent.singleton;

import com.self.concurrent.annotations.Recommend;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Lucifer
 * @do 单例的例子, 利用enum的唯一初始化过程保证初始化唯一对象
 * @date 2018/09/18 9:24
 */
@Slf4j
@Recommend
public class SingletonExample2 {

    private SingletonExample2(){}

    private static SingletonExample2 getInstace(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample2 singletonExample2;

        //该方法利用jvm确保只能执行一次
        private Singleton(){
            singletonExample2 = new SingletonExample2();
        }

        public SingletonExample2 getInstance(){
            return singletonExample2;
        }

    }

}
