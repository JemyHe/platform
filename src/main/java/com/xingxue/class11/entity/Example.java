package com.xingxue.class11.entity;

/**
 * Created by Administrator on 2017/7/25.
 */
public class Example {

    String str = new String("good");
    char[] cn = {'a','b','c'};

    public static void main(String[] args) {
        Example example = new Example();
        example.change(example.str,example.cn);
        System.out.println(example.str+"and");
        System.out.println(example.cn);
    }

    public void change(String str,char cn[]){
        str = "test ok";
        cn[0] = 'g';
    }
}
