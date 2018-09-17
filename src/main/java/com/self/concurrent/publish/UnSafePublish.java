package com.self.concurrent.publish;

import com.self.concurrent.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author Lucifer
 * @do 不安全的对象发布
 * @date 2018/09/17 19:23
 */
@Slf4j
@NotThreadSafe
public class UnSafePublish {

    private String[] status = new String[]{"a", "b", "c"};

    public String[] getStatus(){
        return status;
    }

    public static void main(String[] args) {
        //该对象发布之后,属性的共有方法作用域过广,其它线程也可以访问到,并进行更改,导致线程不安全
        UnSafePublish unSafePublish = new UnSafePublish();
        log.info("unSafePublish :{}", Arrays.toString(unSafePublish.getStatus()));
        unSafePublish.getStatus()[0] = "d";
        log.info("unSafePublish :{}", Arrays.toString(unSafePublish.getStatus()));
    }

}
