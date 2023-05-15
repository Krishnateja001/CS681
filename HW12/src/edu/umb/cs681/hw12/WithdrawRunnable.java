package edu.umb.cs681.hw12;

import java.time.Duration;
import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable implements Runnable{
	private BankAccount account;
	
	private ReentrantLock lock = new ReentrantLock();
	private volatile boolean done = false;

	public void setDone() {
	
		lock.lock();
		try {
			done = true;
		}
		finally {
			lock.unlock();
		}
	}


	public WithdrawRunnable(BankAccount account) {
		this.account = account;
	}
	
	public void run(){
		for(int i = 0; i < 10; i++){
			lock.lock();
			try{

				if(done)
					break;
				account.withdraw(100);
			}
			finally {
				lock.unlock();
				try{
					Thread.sleep( 2000 );
				}
				catch(InterruptedException exception){
					System.out.println("2 Step termination");
				}
			}

		}
	}


	
}
