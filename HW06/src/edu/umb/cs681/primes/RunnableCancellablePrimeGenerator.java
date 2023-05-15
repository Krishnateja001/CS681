package edu.umb.cs681.primes;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
	
	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;
	
	public void setDone() {
	
		lock.lock();
		try {
			done = true;
		}
		finally {
			lock.unlock();
		}
	}
	public void generatePrimes() {
		
		for( long n = from ; n <= to ; ++n) {
		
			lock.lock();
			try {
				if(done) 
					break;
				if(isPrime(n))
					this.primes.add(n);

			}
			finally {
				lock.unlock();
			}
		
		
		}
	
	
	}	
	public void run() {	
		generatePrimes();
	}
	RunnableCancellablePrimeGenerator(long a, long b){ from = a; to = b;};


	public static void main(String ... args) {
	
		RunnableCancellablePrimeGenerator child_object = new RunnableCancellablePrimeGenerator(1,100000);

		Thread child_thread = new Thread(child_object);
		
		child_thread.start();
		try {
			Thread.sleep(2);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		child_object.setDone();

		child_object.printPrimes();

	
	}
}

