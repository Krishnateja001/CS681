package edu.umb.cs681.hw05;

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;


public class D2 implements Runnable {

	private Double price_max_th;
	private Double price_min_th;
	private Double price_count_th;
	private Double price_sum_th;
	
	private Double nox_max_th;
	private Double nox_min_th;
	private Double nox_count_th;
	private Double nox_sum_th;
	
	private Double room_max_th;
	private Double room_min_th;
	private Double room_count_th;
	private Double room_sum_th;
	
	public void printResults() {
	
	
		System.out.println("2) Top 10% crime rate and pupil to student ratio houses ");
		System.out.println("Max Price : " + price_max_th);
		System.out.println("Min Price : " + price_min_th);
		System.out.println("Avg Price : " + price_sum_th/price_count_th);
		
		System.out.println("Max Nox : " + nox_max_th);
		System.out.println("Min Nox : " + nox_min_th);
		System.out.println("Avg Nox : " + nox_sum_th/nox_count_th);
		
		System.out.println("Max Rooms : " + room_max_th);
		System.out.println("Min Rooms : " + room_min_th);
		System.out.println("Avg Number of Rooms : " + room_sum_th/room_count_th);
	}

	public void run() {
		
		System.out.println();

		Path path = Path.of("/Users/kt/IdeaProjects/HW05/src/edu/umb/cs681/hw05/bos-housing.csv");
		
		try {
		
//---------------------Data processing 2 below------------
		List<Double>  crime_rates = Files.lines(path)	
			.skip(1)
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[0]);
			              }).collect(Collectors.toList());
					
		Collections.sort(crime_rates);
		double point = 50;
		double crime_threshold = crime_rates.get((int)point);
		

		List<Double>  pt_ratios = Files.lines(path)	
			.skip(1)
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[10]);
			              }).collect(Collectors.toList());
					
		Collections.sort(pt_ratios);
		point = 50;
		double pt_threshold = pt_ratios.get((int)point);
	
//&& Double.parseDouble(line.split(",")[10]) <= pt_threshold
		price_max_th = (double)Files.lines(path)	
			.skip(1)
			.filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold && Double.parseDouble(line.split(",")[10]) <= pt_threshold )
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[13]);
			              })
			.max(Comparator.comparing((Double price)-> price ))
			.get();	
	
			//.forEach(System.out::println);

		
		price_min_th = (double)Files.lines(path)	
			.skip(1)
			.filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[13]);
			              })
			.min(Comparator.comparing((Double price)-> price ))
			.get();	


	
		price_count_th = (double)Files.lines(path)	
			.skip(1)
			.filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
			.count();

		price_sum_th = (double)Files.lines(path)	
			.skip(1)
			.filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[13]);
			              })

			
			.reduce(0.0, (Double result, Double price) -> (
                                                                                result+price ));
						

		
//--------------------------------2) NOX----------------
		 nox_max_th = (double)Files.lines(path)	
			.skip(1)
			.filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[4]);
			              })
			.max(Comparator.comparing((Double price)-> price ))
			.get();	
	
			//.forEach(System.out::println);


		
		nox_min_th = (double)Files.lines(path)	
			.skip(1)
			.filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[4]);
			              })
			.min(Comparator.comparing((Double price)-> price ))
			.get();	


	
		nox_count_th = (double)Files.lines(path)	
			.skip(1)
			.filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
			.count();

		nox_sum_th = (double)Files.lines(path)	
			.skip(1)
			.filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[4]);
			              })

			
			.reduce(0.0, (Double result, Double price) -> (
                                                                                result+price ));
						

//-------------------------------------------------# of rooms---------------------
			room_max_th = (double)Files.lines(path)	
			.skip(1)
			.filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[5]);
			              })
			.max(Comparator.comparing((Double price)-> price ))
			.get();	
	
			//.forEach(System.out::println);


		
		 room_min_th = (double)Files.lines(path)	
			.skip(1)
			.filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[5]);
			              })
			.min(Comparator.comparing((Double price)-> price ))
			.get();	


	
		 room_count_th = (double)Files.lines(path)	
			.skip(1)
			.filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
			.count();

		 room_sum_th = (double)Files.lines(path)	
			.skip(1)
			.filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[5]);
			              })

			
			.reduce(0.0, (Double result, Double price) -> (
                                                                                result+price ));
						

	
		} catch (IOException ex) {
			System.out.println("problem");
		}

		


	/*	dh.st.addSummary( new DSummary(1.0,2.0,3.0,4.0) );
		dh.st.addSummary( new DSummary(10.0,2.0,3.0,4.0) );
		dh.st.addSummary( new DSummary(10.0,2.0,10.0,4.0) );
		dh.st.addSummary( new DSummary(10.0,2.0,3.0,4.0) );
		dh.st.addSummary( new DSummary(10.0,2.99,3.0,4.99) );
	*/
	}
}
