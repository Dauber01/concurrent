package com.self.concurrent.immutable;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lucifer
 * @do 利用Collections的unmodifiable进行修饰,以保证类的不可变性
 * @date 2018/09/18 10:59
 */
@Slf4j
public class ImmutableExample1 {

    private static final Integer integer = 1;

    private static final String str = "2";

    private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    static {
        map.put(1, 3);
        map.put(2, 5);
        map.put(4, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        //在该操作中证明integer和str的值不可更改,map指向的对象地址不可更改
        //integer = 3;
        //str = "4";
        //map = new HashMap<>();
        //但是map属性的值可以更改,这会带来隐式的安全问题
        map.put(1, 4);
        log.info("the map[0] ={}", map.get(1));
    }

    public void test(final int num){
        //由final定义的方法参数无法变更
        //num = 3;
    }

}
