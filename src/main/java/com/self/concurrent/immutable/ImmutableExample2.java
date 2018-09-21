package com.self.concurrent.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * @author Lucifer
 * @do 利用guava的immutable的类型进行修饰, 以保证类的不可变性
 * @date 2018/09/18 14:45
 */
public class ImmutableExample2 {

    private static final ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);

    private static final ImmutableSet set = ImmutableSet.copyOf(list);

    private static final ImmutableMap<Integer, Integer> map1 = ImmutableMap.of(1, 2, 3, 4);

    private static final ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4).build();

    public static void main(String[] args) {
        //底层实现原理和unmodify基本等同
        map1.put(1,2);
    }

}
