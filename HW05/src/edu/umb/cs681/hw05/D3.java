package edu.umb.cs681.hw05;

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;


public class D3 implements Runnable {

	private Double d1;
	private Double d2;
	private Double d3;
	private Double d4;
	
	public void printResults() {
	
	

			System.out.println("3) Tax analysis of houses next to Charles river : ");
			System.out.println("Max Tax : " + d2);
			System.out.println("Min Tax : " + d1);	
			System.out.println("Average Tax : " + d4/d3);
	
	
	}

	public void run() {
		
		System.out.println();

		Path path = Path.of("/Users/kt/IdeaProjects/HW05/src/edu/umb/cs681/hw05/bos-housing.csv");
		
		try {
		
	//-------------------------------------------Data processing 3 below Tax rate of charles river houses
		
		 d1 = Files.lines(path)	
			.skip(1)
			.filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
			.map( line -> {
					String[] fields = line.split(",");
					return Double.parseDouble(fields[9]);
			              }).min(Comparator.comparing((Double price)-> price ))
					.get();


		
		 d2 = Files.lines(path)	
			.skip(1)
			.filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
			.map( line -> {
                                        String[] fields = line.split(",");
                                        return Double.parseDouble(fields[9]);
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
					return Double.parseDouble(fields[9]);
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
