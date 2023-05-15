package edu.umb.cs681.hw08;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer{
	
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
	
	public  RunnableCancellablePrimeFactorizer(long dividend) {
		if(dividend >= 2){
			this.dividend = dividend;
			this.from = 2;
			this.to = dividend;
		}else{
			throw new RuntimeException("dividend must be >= 2. dividend==" + dividend);
		}
	}
 
 	protected boolean isEven(long n){
                if(n%2 == 0){ return true; }
                else{ return false; }
        }

	public void generatePrimeFactors() {
		long divisor = from;
	    while( dividend != 1 && divisor <= to ){

		    lock.lock();

		    try {
			if( divisor > 2 && isEven(divisor)) {
				divisor++;
				continue;
			}
			    if(done) break;
			    if(dividend % divisor == 0) {
				factors.add(divisor);
				dividend /= divisor;
			    }else {
				if(divisor==2){ divisor++; }
				else{ divisor += 2; }
			    }
		    }
		    finally {
		    	lock.unlock();
		    }
		}
	}

	public static void main(String ... args) {
	
		RunnableCancellablePrimeFactorizer child_object = new RunnableCancellablePrimeFactorizer(100000);

		Thread child_thread = new Thread(child_object);
		
		child_thread.start();
		try {
			Thread.sleep(2);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		child_object.setDone();

		child_object.printFactors();

	
	}



}


