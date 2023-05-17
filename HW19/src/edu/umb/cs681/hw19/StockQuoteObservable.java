package edu.umb.cs681.hw19;

import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable<StockEvent> implements Runnable{


	private static ReentrantLock lock = new ReentrantLock();
	
	HashMap<String, Double> map = new HashMap<>();
	
	private static StockQuoteObservable obj = new StockQuoteObservable();

	public void changeQuote(String t, Double d) {
	
		lock.lock();
		map.put(t,d);
		lock.unlock();
		StockEvent noti = new StockEvent(t,d);
	//	noti.ticker = t;
	//	noti.quote = d;
		notifyObservers(noti);

	}

	public void run() {
		
		Double a = Math.random();

		obj.changeQuote("val",a);

	}

	public static void main(String args[]) {
	
		obj.addObserver(new LineChartObserver());		
		obj.addObserver(new TableObserver());		
		obj.addObserver(new ThreeDObserver());	

		Thread rThread[] = new Thread[12];
		
		for(int i = 0 ; i < 12 ; ++i) {
			rThread[i] = new Thread(obj);
			rThread[i].start();
		}
		try {	
			for(int i = 0 ; i < 12 ; ++i) {
				rThread[i].join();
			}
		}
		catch(Exception z) {
			System.out.println(z);
		}	
	
	}

} 
