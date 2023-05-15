package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount implements BankAccount {
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();



	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.print("Current balance (d): " + balance);
			balance = balance + amount;
			System.out.println(", New balance (d): " + balance);
		}finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.print("Current balance (w): " + balance);
			balance = balance - amount;
			System.out.println(", New balance (w): " + balance);
		}finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public double getBalance() { return this.balance; }
	
	public static void main(String[] args){
		ThreadSafeBankAccount bankAccount = new ThreadSafeBankAccount();		
		
		//new Thread( new DepositRunnable(bankAccount) ).start();
		//new Thread( new WithdrawRunnable(bankAccount) ).start();
	
		Thread rethreads[] = new Thread[24];

		DepositRunnable dr = new DepositRunnable(bankAccount);
		WithdrawRunnable wr = new WithdrawRunnable(bankAccount);

		for(int i = 0 ; i < 12 ; ++i) {
			
			rethreads[i] = new Thread(dr);
			rethreads[i].start();
		}
		for(int i = 12 ; i < 24 ; ++i) {
			
			rethreads[i] = new Thread(wr);
			rethreads[i].start();
		}
		dr.setDone();
		wr.setDone();
		
		for(int i = 0 ; i < 24 ; ++i) {
			
			rethreads[i].interrupt();
		}
		
		for(int i = 0 ; i < 24 ; ++i) {
			
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
