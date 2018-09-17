package com.self.concurrent.atomic;

import com.self.concurrent.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Lucifer
 * @do atomic的refrence方法
 * @date 2018/09/17 14:14
 */
@Slf4j
@ThreadSafe
public class AtomicRefrenceExample {

    private static AtomicReference<Integer> count = new AtomicReference<Integer>(0);

    public static void main(String[] args) {
        //只有在预期值的时候才会进行指定的赋值
        count.compareAndSet(0, 2);  //2
        count.compareAndSet(1, 3);  //no
        count.compareAndSet(2, 4);  //4
        count.compareAndSet(3, 5);  //no
        count.compareAndSet(5, 7);  //no
        log.info("count:{}", count.get());
    }

}
