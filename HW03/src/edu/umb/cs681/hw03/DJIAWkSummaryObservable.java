package edu.umb.cs681.hw03;

import java.util.*;
//import javafx.util.*;
import java.util.concurrent.locks.ReentrantLock;
public class DJIAWkSummaryObservable extends Observable<WkSummary> {


	List<DSummary> week_data = new ArrayList<>();

	public void addSummary(DSummary obj) {

		week_data.add(obj);

		if(week_data.size() == 5) {

			double open = week_data.stream()
					.map( (DSummary te)-> te.getOpen())
					.findFirst()
					.get();

			double close = week_data.stream()
					.map( (DSummary te)-> te.getClose())
					.reduce((first, second) -> second)
					.get();

			double high = week_data.stream()
					.map( (DSummary te)-> te.getHigh())
					.max( Comparator.comparing((Double price)-> price ) )
					.get();

			double low = week_data.stream()
					.map( (DSummary te)-> te.getLow())
					.min( Comparator.comparing((Double price)-> price ) )
					.get();

			WkSummary p = new WkSummary(open, close, high, low);
			notifyObservers(p);

			week_data.clear();
		}

	}

}
