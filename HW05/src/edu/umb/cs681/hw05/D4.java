package edu.umb.cs681.hw05;

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;


public class D4 implements Runnable {

	private Double d1;
	private Double d2;
	private Double d3;
	private Double d4;
	
	public void printResults() {
	
			System.out.println("4) Age analysis of houses next to Charles river : ");
			System.out.println("Max Age : " + d2);
			System.out.println("Min Age : " + d1);	
			System.out.println("Average Age : " + d4/d3);
	
	
	}

	public void run() {
		
		System.out.println();

		Path path = Path.of("/Users/kt/IdeaProjects/HW05/src/edu/umb/cs681/hw05/bos-housing.csv");
		
		try {
		
//-------------------------------------------Data processing 4 below Age rate of charles river houses
		
		d1 = Files.lines(path)	
			.skip(1)
			.filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[6]);
			              }).min(Comparator.comparing((Double price)-> price ))
					.get();


		
		 d2 = Files.lines(path)	
			.skip(1)
			.filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
			.map( line -> {
                                        String[] fields = line.split(",");
                                        return Double.parseDouble(fields[6]);
                                      })
			.max(Comparator.comparing((Double price)-> price ))
			.get();
			

	
		d3 = (double)Files.lines(path)	
			.skip(1)
			.filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
			.count(); 
					//String[] fields = line.split(",");
//					Integer.parseInt(line.split(",")[3].substring(1,2)) == 1
						//return Double.parseDouble(fields[13]);
				//	)
				//	.filter((Double p)->
				//			p == 1)
			//		.count();
				//	.get();

			//System.out.println(d3);
		d4 = Files.lines(path)	
			.skip(1)
			.filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[6]);
			              }).reduce(0.0, (Double result, Double price) -> (
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
