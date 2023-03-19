package edu.umb.cs681.HW02;
import java.util.*;
public class CarPriceResultHolder {
    private int numCarExamined;
    private double average;

    public static void main(String[] args) {
        ArrayList<Car> c = new ArrayList<>();
        c.add(new Car("MarutiSuzuki", "Swift", 18, 2015, 10000));
        double averagePrice = c.stream()
                .map( car -> car.getPrice() )
                .reduce(0,(x,y)->x+y,Integer::sum );
        double length = c.stream()
                .map( car -> car.getPrice() ).count();

        double averagePrice_9 = c.stream()
                .map( car -> car.getPrice() )
                .reduce( new CarPriceResultHolder(),
                        (result, price)->{

                            return result;}),
        (finalResult, intermediateResult)->
        System.out.println("Hello world!");
    }

    private static void getAverage() {
        return this.average;
    }
}
