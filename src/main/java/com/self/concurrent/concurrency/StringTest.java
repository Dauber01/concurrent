package com.self.concurrent.concurrency;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Lucifer
 * @do 测试String创建的内存分配
 * @date 2018/09/20 11:06
 */
@Slf4j
public class StringTest {

    public static void main(String[] args) {
        String str1 = new String("abc");
        String str2 = "abc";
        String str3 = new String("abc");
        log.info(String.valueOf(str1 == str2));
        //.intern()方法先行查询对应的静态常量池,如果静态
        log.info(String.valueOf(str1 == str3.intern()));
        log.info(String.valueOf(str2 == str3.intern()));
    }

}
