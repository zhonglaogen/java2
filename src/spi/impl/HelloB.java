package spi.impl;


import spi.HelloService;

/**
 * @author zlx
 * @date 2019-12-17 14:31
 */
public class HelloB implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("B say hello");
    }
}
