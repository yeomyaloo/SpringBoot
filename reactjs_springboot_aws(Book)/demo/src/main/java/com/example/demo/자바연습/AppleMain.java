package com.example.demo.자바연습;

import java.util.Comparator;
import java.util.List;

public class AppleMain {
    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter){
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

}

