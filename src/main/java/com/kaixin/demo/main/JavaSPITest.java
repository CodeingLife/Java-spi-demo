package com.kaixin.demo.main;

import com.kaixin.demo.service.Robot;

import java.util.ServiceLoader;
public class JavaSPITest {

    public static void main(String[] args) {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }

}
