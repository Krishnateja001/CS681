package edu.umb.cs681.hw11;

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

public class RequestHandler implements Runnable{
	
	private ReentrantLock done_lock = new ReentrantLock();
	
	private boolean done = false;
	
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
		
		AccessCounter sv = AccessCounter.getInstance();
			
		while(true) {
			done_lock.lock();	
			try{
			if(!done) {
				
				double t = Math.random();

				t = t % 2;
				int s = (int)t;
				switch(s) {
				
					case 0: 
							System.out.println(sv.getCount(Paths.get("a.html")));
							sv.increment(Paths.get("a.html"));
													break;	
				
					case 1: 
							System.out.println(sv.getCount(Paths.get("b.html")));
							sv.increment(Paths.get("b.html"));

												break;
				}

			}
			else
				break;	
			}
			finally{
				
				done_lock.unlock();
				System.out.println("child going to sleep");
				
				try{
					Thread.sleep(1000);
				}
				catch(InterruptedException ie) {
				//	System.out.println(ie);
					System.out.println("child thread woke up and 2 step exits");	
				}

			}
		
		}	

	}

	public static void main(String args[]) {
	
		RequestHandler r = new RequestHandler();

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
		
		System.out.println("Main thread exiting");	
	}
}

