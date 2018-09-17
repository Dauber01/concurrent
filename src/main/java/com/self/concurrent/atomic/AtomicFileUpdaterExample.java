package com.self.concurrent.atomic;

import com.self.concurrent.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author Lucifer
 * @do atomic file updater的例子
 * @date 2018/09/17 14:21
 */
@Slf4j
@ThreadSafe
public class AtomicFileUpdaterExample {

    private static AtomicIntegerFieldUpdater<AtomicFileUpdaterExample> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicFileUpdaterExample.class, "count");

    @Getter
    public volatile int count = 100;    //AtomicFieldUpdater所用的字段只能是签名中为volatile的非static变量

    public static void main(String[] args) {
        //AtomicFieldUpdater只在符合期望结果的时候对字段进行操作
        AtomicFileUpdaterExample atomicFileUpdaterExample = new AtomicFileUpdaterExample();
        if (updater.compareAndSet(atomicFileUpdaterExample, 100, 150)){
            log.info("first update success, the count:{}", atomicFileUpdaterExample.getCount());
        }
        if (updater.compareAndSet(atomicFileUpdaterExample, 100, 200)){
            log.info("second update success, the count:{}", atomicFileUpdaterExample.getCount());
        }else {
            log.info("second update fail, the count:{}", atomicFileUpdaterExample.getCount());
        }
    }

}
