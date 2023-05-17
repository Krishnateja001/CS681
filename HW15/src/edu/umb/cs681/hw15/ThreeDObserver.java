package edu.umb.cs681.hw15;

public class ThreeDObserver implements Observer<StockEvent> {

    public void update(Observable<StockEvent> sender, StockEvent event) {
        System.out.println("3DObserver update: " + event.getTicker() + " " + event.getQuote());
    }

}

