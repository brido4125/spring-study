package com.example.designpattern.singleton;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MultiThreadTest {
    @Test
    void test() {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                ChocolateBoiler boiler = ChocolateBoiler.getInstance();
                boiler.fill();
                boiler.boil();
                boiler.drain();
            });
            threads[i] = thread;
        }

        Arrays.stream(threads).forEach(Thread::start);
    }

    @Test
    void syncTest() {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                SyncChocolateBoiler boiler = SyncChocolateBoiler.getInstance();
                boiler.fill();
                boiler.boil();
                boiler.drain();
            });
            threads[i] = thread;
        }

        Arrays.stream(threads).forEach(Thread::start);
    }

}
