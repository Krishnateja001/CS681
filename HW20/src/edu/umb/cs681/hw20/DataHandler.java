package edu.umb.cs681.hw20;

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
public class DataHandler {


    public static void main(String[] args) {

        Path path = Path.of("bos-housing.csv");


        try {

            Double d1 = Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[13]);
                    }).min(Comparator.comparing((Double price)-> price ))
                    .get();



            Double d2 = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[13]);
                    })
                    .max(Comparator.comparing((Double price)-> price ))
                    .get();

            System.out.println("1) Houses next to Charles river : ");
            System.out.println("Max Price : " + d2);
            System.out.println("Min Price : " + d1);

            long d3 = Files.lines(path)
                    .skip(1)
		    .parallel()
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
            Double d4 = Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[13]);
                    }).reduce(0.0, (Double result, Double price) -> (
                            result+price ));

            System.out.println("Average Price : " + d4/d3);
//---------------------Data processing 2 below------------
            List<Double>  crime_rates = Files.lines(path)
                    .skip(1)
		    .parallel()
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[0]);
                    }).collect(Collectors.toList());

            Collections.sort(crime_rates);
            double point = 50;
            double crime_threshold = crime_rates.get((int)point);


            List<Double>  pt_ratios = Files.lines(path)
                    .skip(1)
		    .parallel()
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[10]);
                    }).collect(Collectors.toList());

            Collections.sort(pt_ratios);
            point = 50;
            double pt_threshold = pt_ratios.get((int)point);

//&& Double.parseDouble(line.split(",")[10]) <= pt_threshold
            Double price_max_th = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold && Double.parseDouble(line.split(",")[10]) <= pt_threshold )
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[13]);
                    })
                    .max(Comparator.comparing((Double price)-> price ))
                    .get();

            //.forEach(System.out::println);
            System.out.println("2) Top 10% crime rate and pupil to student ratio houses ");
            System.out.println("Max Price : " + price_max_th);

            Double price_min_th = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[13]);
                    })
                    .min(Comparator.comparing((Double price)-> price ))
                    .get();

            System.out.println("Min Price : " + price_min_th);

            Double price_count_th = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
                    .count();

            Double price_sum_th = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[13]);
                    })


                    .reduce(0.0, (Double result, Double price) -> (
                            result+price ));

            System.out.println("Avg Price : " + price_sum_th/price_count_th);

//--------------------------------2) NOX----------------
            Double nox_max_th = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[4]);
                    })
                    .max(Comparator.comparing((Double price)-> price ))
                    .get();

            //.forEach(System.out::println);

            System.out.println("Max Nox : " + nox_max_th);

            Double nox_min_th = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[4]);
                    })
                    .min(Comparator.comparing((Double price)-> price ))
                    .get();

            System.out.println("Min Nox : " + nox_min_th);

            Double nox_count_th = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
                    .count();

            Double nox_sum_th = (double)Files.lines(path)
                    .skip(1)
                    .filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[4]);
                    })


                    .reduce(0.0, (Double result, Double price) -> (
                            result+price ));

            System.out.println("Avg Nox : " + nox_sum_th/nox_count_th);
//-------------------------------------------------# of rooms---------------------
            Double room_max_th = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[5]);
                    })
                    .max(Comparator.comparing((Double price)-> price ))
                    .get();

            //.forEach(System.out::println);

            System.out.println("Max Rooms : " + room_max_th);

            Double room_min_th = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[5]);
                    })
                    .min(Comparator.comparing((Double price)-> price ))
                    .get();

            System.out.println("Min Rooms : " + room_min_th);

            Double room_count_th = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
                    .count();

            Double room_sum_th = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Double.parseDouble(line.split(",")[0]) <= crime_threshold  && Double.parseDouble(line.split(",")[10]) <= pt_threshold)
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[5]);
                    })


                    .reduce(0.0, (Double result, Double price) -> (
                            result+price ));

            System.out.println("Avg Number of Rooms : " + room_sum_th/room_count_th);
//-------------------------------------------Data processing 3 below Tax rate of charles river houses

            d1 = Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[9]);
                    }).min(Comparator.comparing((Double price)-> price ))
                    .get();



            d2 = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[9]);
                    })
                    .max(Comparator.comparing((Double price)-> price ))
                    .get();

            System.out.println("3) Tax analysis of houses next to Charles river : ");
            System.out.println("Max Tax : " + d2);
            System.out.println("Min Tax : " + d1);

            d3 = Files.lines(path)
                    .skip(1)
		    .parallel()
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
		    .parallel()
                    .filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[9]);
                    }).reduce(0.0, (Double result, Double price) -> (
                            result+price ));

            System.out.println("Average Tax : " + d4/d3);
//-------------------------------------------Data processing 4 below Age rate of charles river houses

            d1 = Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[6]);
                    }).min(Comparator.comparing((Double price)-> price ))
                    .get();



            d2 = (double)Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[6]);
                    })
                    .max(Comparator.comparing((Double price)-> price ))
                    .get();

            System.out.println("4) Age analysis of houses next to Charles river : ");
            System.out.println("Max Age : " + d2);
            System.out.println("Min Age : " + d1);

            d3 = Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
                    .count();

            d4 = Files.lines(path)
                    .skip(1)
		    .parallel()
                    .filter( line -> Integer.parseInt(line.split(",")[3].substring(1,2)) == 1 )
                    .map( line -> {
                        String[] fields = line.split(",");
                        return Double.parseDouble(fields[6]);
                    }).reduce(0.0, (Double result, Double price) -> (
                            result+price ));

            System.out.println("Average Age : " + d4/d3);


        } catch (IOException ex) {
            System.out.println("problem");
        }



    }
}
