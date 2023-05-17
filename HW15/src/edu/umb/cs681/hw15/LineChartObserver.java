package edu.umb.cs681.hw15;

public class LineChartObserver implements Observer<StockEvent> {

    	public void update(Observable<StockEvent> sender, StockEvent event) {
	
		System.out.println(event.getTicker() + " " + event.getQuote());
	}

}
