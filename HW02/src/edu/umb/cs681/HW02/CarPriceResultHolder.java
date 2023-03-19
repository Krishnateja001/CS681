package edu.umb.cs681.HW02;
import java.util.*;
public class CarPriceResultHolder {
    private int numCarExamined;
    private double average;

    public double getAverage() {
        return average;
    }
    public static void main(String[] args) {
        ArrayList<Car> c = new ArrayList<>();
        c.add(new Car("MarutiSuzuki", "Swift", 18, 2015, 10000));
        c.add(new Car("KIA", "Soul", 19, 2016, 12000));
        c.add(new Car("Ford", "MustangGT", 21, 2018, 34000));
        c.add(new Car("Maserati", "Ghibli", 22, 2019, 78000));
//        double averagePrice = c.stream()
//                .map( car -> car.getPrice() )
//                .reduce(0,(x,y)->x+y,Integer::sum );
//        double length = c.stream()
//                .map( car -> car.getPrice() ).count();

        double averagePrice_9 = c.stream()
                .map( car -> car.getPrice() )
                .reduce( new CarPriceResultHolder(),
                        (result, price)->{
                            double intermediateResult = (result.numCarExamined * result.average+ price);
                            int length = (++result.numCarExamined);
                            result.average = intermediateResult/length;
                            return result;},
        (finalResult, intermediateResult)->finalResult).getAverage();
        System.out.println("Average price:"+averagePrice_9);
    }




}
