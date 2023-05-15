package edu.umb.cs681.hw14;

import java.util.*;
import java.nio.*;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsHandler implements Runnable {

	public AdmissionMonitor monitor;

        StatsHandler(AdmissionMonitor monitor) {
                this.monitor = monitor;
        }


	public void run() {
		System.out.println(monitor.countCurrentVisitors());
	}
}
	
