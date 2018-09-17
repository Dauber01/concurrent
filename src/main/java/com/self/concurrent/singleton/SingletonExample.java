package com.self.concurrent.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Lucifer
 * @do 单例的例子, 利用传统的两种方式之一的饿汉式加载
 * @date 2018/09/17 20:41
 */
@Slf4j
public class SingletonExample {

    //私有构造器
    private SingletonExample(){}

    //单例对象
    private static SingletonExample singletonExample = null;

    //静态代码块和静态属性的执行顺序是根据代码中的先后顺序,也就是说如果静态代码块写在前面的时候
    //  ,赋值的结果会被创建语句中的赋值所覆盖,所以一定要注意顺序我自己的感受是在写静态属性的时候
    //  ,如果有静态代码块专门为其赋值,则在没有特殊场景需要的情况下,可以只初始化变量不赋值
    static {
        singletonExample = new SingletonExample();
    }

    //共有静态的访问方法
    public static SingletonExample getInstance(){
        return singletonExample;
    }

    public static void main(String[] args) {
        SingletonExample singletonExample1 = SingletonExample.getInstance();
        log.info("singletonExample1 : {}", singletonExample1.hashCode());
        SingletonExample singletonExample2 = SingletonExample.getInstance();
        log.info("singletonExample2 : {}", singletonExample2.hashCode());
    }

}
