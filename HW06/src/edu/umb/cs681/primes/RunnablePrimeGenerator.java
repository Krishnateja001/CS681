package edu.umb.cs681.primes;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
	
	public void run() {	
		generatePrimes();
	}
	public	RunnablePrimeGenerator(long a, long b){};
	public	RunnablePrimeGenerator(){};
}

