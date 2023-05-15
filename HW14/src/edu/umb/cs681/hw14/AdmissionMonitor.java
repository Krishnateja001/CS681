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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AdmissionMonitor {
	
	private static int currentVisitors = 0;

        private static ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
	
	private static Condition condition = rwlock.writeLock().newCondition();

	private static ReentrantLock lock = new ReentrantLock();
	private boolean done = false;

	AdmissionMonitor(int a ) {
		currentVisitors = a;
	}	
	public void setDone() {
	
		lock.lock();
		try {
			done = true;
		}
		finally {
			lock.unlock();
		}
		

	}

	public void enter() {
		
		while(true) {
			if(done)
				break;
			rwlock.writeLock().lock();
			try {
				if(currentVisitors < 15) {
					currentVisitors++;
					try {	
						condition.await();
					}
					catch(Exception e) {
						System.out.println(e);
					}
				}
				else
					System.out.println("Too many people, will release the lock sleep and try again");
			}
			finally {
				rwlock.writeLock().unlock();
				try {
					Thread.sleep(2000);
					
				}
				catch(InterruptedException e) {
					System.out.println("Thread woke up from sleep for 2 step exit");
				}
			}
		}

	}

	public void exit() {

		while(true) {
			if(done)
				break;
			rwlock.writeLock().lock();
			try {
				if(currentVisitors > 0) {
					currentVisitors--;
					condition.signalAll();
				}
				else
					System.out.println("No people inside, will release the lock sleep and try again");
			}
			finally {
				rwlock.writeLock().unlock();
				try {
					Thread.sleep(2000);
					
				}
				catch(InterruptedException e) {
					System.out.println("Thread woke up from sleep for 2 step exit");
				}
			}
		}

	}

	public int countCurrentVisitors() {
	
		int ret = 0;

		while(true) {
			if(done)
				break;
			rwlock.readLock().lock();
			try {	
				ret = currentVisitors;
				return ret;
			}
			finally {
				rwlock.readLock().unlock();
				try {
					Thread.sleep(2000);
				}
				catch(InterruptedException e) {
					
					System.out.println("Thread woke up from sleep for 2 step exit");
				
				}

				return ret;
			}

		}
		return ret;
	}

	public static void main(String args[]) {
	
		AdmissionMonitor a = new AdmissionMonitor(7);	
		EntranceHandler eh = new EntranceHandler(a);
		ExitHandler exh = new ExitHandler(a);
		StatsHandler sth = new StatsHandler(a);

		Thread t[] = new Thread[3];
		
		t[0] = new Thread(eh);
		t[1] = new Thread(exh);
		t[2] = new Thread(sth);


		for(int i = 0 ; i < 3 ; ++i)
			t[i].start();
		try {
			Thread.sleep(200);
		}
		catch(Exception e ) {
			System.out.println(e);
		}
		
		a.setDone();
//		eh.monitor.setDone();
//		exh.monitor.setDone();
//		sth.monitor.setDone();
		for(int i = 0 ; i < 3 ; ++i) {
		
			t[i].interrupt();
		}
		for(int i = 0 ; i < 3 ; ++i) {
		
			try {
			t[i].join();
			}
			catch(Exception e92)
			{}
		}

		
		System.out.println("Main thread exiting");	
	}

}
