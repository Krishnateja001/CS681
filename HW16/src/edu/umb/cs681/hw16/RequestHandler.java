package edu.umb.cs681.hw16;

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
	
	public static long price = 0;	

	public void run() {
		
		long threadid = Thread.currentThread().getId();
		
		threadid = threadid ;

		System.out.println("Adding the following number to price: " + threadid);
		price += threadid;

	}

	public static void main(String args[]) {
	
		RequestHandler r = new RequestHandler();

		Thread rethreads[] = new Thread[12];

		for(int i = 0 ; i < 12 ; ++i) {
			
			rethreads[i] = new Thread(r);
			rethreads[i].start();
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

