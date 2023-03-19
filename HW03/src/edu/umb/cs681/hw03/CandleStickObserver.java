package edu.umb.cs681.hw03;
public class CandleStickObserver implements Observer<WkSummary>{
	public void update(Observable<WkSummary> sender, WkSummary event) {
		System.out.println("Weekly Summary - " + "Open: " + event.open  + " High: " + event.high + " Low: " + event.low +" Close: " + event.close);
	}
}
