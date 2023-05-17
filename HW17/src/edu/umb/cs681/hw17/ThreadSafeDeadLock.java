package edu.umb.cs681.hw17;

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

public class ThreadSafeDeadLock implements Runnable{
	
	private ReentrantLock done_lock = new ReentrantLock();
	private ReentrantLock lock = new ReentrantLock();
	
	private volatile boolean done = false;
	
	public static long price = 0;	

	public void setDone() {
	
		done_lock.lock();
		try {
			done = true;
		}
		finally {
			done_lock.unlock();
		}
		

	}

	public void run() {
		
		long threadid = Thread.currentThread().getId();
		
		threadid = threadid ;

		while(true) {

			if(done)
				break;

			System.out.println("Adding the following number to price: " + threadid);

			try {
				lock.lock();
				price += threadid;
			}
			finally {
				lock.unlock();
				try {
					Thread.sleep(5000);
				}
				catch(InterruptedException e) {
					System.out.println("I am threadno: " + threadid + ". I was sleeping, my work was done, now I was woken up to perform 2 step exit. Thank you:)");
				}
			}
		}
	}

	public static void main(String args[]) {
	
		ThreadSafeDeadLock r = new ThreadSafeDeadLock();

		Thread rethreads[] = new Thread[12];

		for(int i = 0 ; i < 12 ; ++i) {
			
			rethreads[i] = new Thread(r);
			rethreads[i].start();
		}
		
		r.setDone();
		
		for(int i = 0 ; i < 12 ; ++i) {
			
			rethreads[i].interrupt();
		}
		
		for(int i = 0 ; i < 12 ; ++i) {
			
			try {
				rethreads[i].join();
			}
			catch(Exception p) { 
				System.out.println(p);
			}
		}
		
			
		System.out.println("price before Main thread exiting " + price);	
	}
}

