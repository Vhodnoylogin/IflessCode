package com.demo;

import com.demo.ifless.router.Router;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");

        new Router<Main>().init(Main.class.getPackage().getName());
    }
}