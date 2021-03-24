package com.kaixin.demo.service.impl;

import com.kaixin.demo.service.Robot;

public class Bumblebee implements Robot {
    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
