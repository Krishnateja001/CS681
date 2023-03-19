package edu.umb.cs681.hw05;

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.concurrent.locks.ReentrantLock;

public class DataHandler {

    public static void main(String ... args) {

        D1 dp1 = new D1();
        D2 dp2 = new D2();
        D3 dp3 = new D3();
        D4 dp4 = new D4();

        Thread t1 = new Thread(dp1);
        Thread t2 = new Thread(dp2);
        Thread t3 = new Thread(dp3);
        Thread t4 = new Thread(dp4);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch(InterruptedException e) {}

        dp1.printResults();
        dp2.printResults();
        dp3.printResults();
        dp4.printResults();
    }
}
