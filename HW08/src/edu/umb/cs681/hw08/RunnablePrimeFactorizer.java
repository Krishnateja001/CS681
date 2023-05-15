package edu.umb.cs681.hw08;

import java.util.LinkedList;

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable{

	public void run() {
		generatePrimeFactors();
	}
	public  RunnablePrimeFactorizer(long a){};
        public  RunnablePrimeFactorizer(){};

}

