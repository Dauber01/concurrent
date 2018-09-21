package com.self.concurrent.threadlocal;

/**
 * @author Lucifer
 * @do 做线程间的公共存储
 * @date 2018/09/18 16:55
 */
public class RequestHolder {

    //在声明变量的时候将final修饰符放在static前面是标准的规范
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<Long>();

    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }

}
