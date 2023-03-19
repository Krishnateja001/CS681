package edu.umb.cs681.hw03;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class DataHandler {

	private static DJIAWkSummaryObservable st = new DJIAWkSummaryObservable();


	private static void te(DSummary obj) {

		st.addSummary(obj);
	}

	public static void main(String ... args) {

		st.addObserver(new CandleStickObserver());
		Path path = Path.of("src/edu/umb/cs681/hw03/HistoricalPrices.csv");

		try {
			Files.lines(path)
					.skip(1)
					.map( line -> {
						String[] fields = line.split(",");
						return new DSummary(Double.parseDouble(fields[1]), Double.parseDouble(fields[2]), Double.parseDouble(fields[3]), Double.parseDouble(fields[4]) );
					}).forEach(DataHandler::te);



		} catch (IOException ex) {
			System.out.println("problem");
		}

	}
}
