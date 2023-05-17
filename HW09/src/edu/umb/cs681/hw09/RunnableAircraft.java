package edu.umb.cs681.hw09;

import java.util.*;
import java.util.function.Supplier;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableAircraft extends Aircraft implements Runnable{

        private ReentrantLock lock = new ReentrantLock();
	
	public void run() {
		
		lock.lock();
		try {
			
			System.out.println("ThreadId: " + Thread.currentThread().getId() + " the current position is: " + getPosition()); 
			System.out.println("ThreadId: " + Thread.currentThread().getId() + " setting the position to "+Thread.currentThread().getId()+","+Thread.currentThread().getId()+","+Thread.currentThread().getId());
		       setPosition(Thread.currentThread().getId(), Thread.currentThread().getId(), Thread.currentThread().getId());	
		}
		finally {
			
			lock.unlock();
		}
		
	}



	public RunnableAircraft(Position pos) {super(pos);}

	public static void main(String...args) {
	
		Position p1 = new Position(9,9,9);

		RunnableAircraft f1 = new RunnableAircraft(p1);

                Thread pilot1 = new Thread(f1);
                Thread pilot2 = new Thread(f1);
		
                pilot1.start();
                pilot2.start();
                try {
                        pilot1.join();
                        pilot2.join();
                }
                catch(Exception e) {
                        System.out.println(e);
                }


	}
}
	
