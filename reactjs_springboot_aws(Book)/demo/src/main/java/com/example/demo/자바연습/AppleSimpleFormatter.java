package com.example.demo.자바연습;

public class AppleSimpleFormatter implements AppleFormatter{
    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}
