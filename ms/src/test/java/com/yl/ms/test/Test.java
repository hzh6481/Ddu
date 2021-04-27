package com.yl.ms.test;

import cn.hutool.system.SystemUtil;

import java.io.PrintStream;

/**
 * @author 001 on 2021/3/22
 */
public class Test {

    @org.junit.Test
    public void test1() {
        System.out.println(SystemUtil.getHostInfo());
    }

    @org.junit.Test
    public void test2() {
        if (System.out.printf("true") == null){

        }else{
            System.out.println("");
            System.out.println("false");
        }
    }
}
