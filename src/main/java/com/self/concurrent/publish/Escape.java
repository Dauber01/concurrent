package com.self.concurrent.publish;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Lucifer
 * @do 对象的溢出,即在对象没有构建完毕的时候就已经被其它线程所见
 * @date 2018/09/17 19:41
 */
@Slf4j
public class Escape {

    //该字段在Escape对象被构建完毕之前就被内部对象(相当于其它线程所引用)
    private int count = 10;

    public Escape(){
        new InnerClass();
    }

    class InnerClass{
        InnerClass(){
            log.info("the escape filed count:{}", Escape.this.count);
        }
    }

    public static void main(String[] args) {
        Escape escape = new Escape();
    }

}
