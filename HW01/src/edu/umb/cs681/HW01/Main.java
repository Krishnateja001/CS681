package edu.umb.cs681.HW01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.*;
public class Main
{
    public static void main(String[] args)
    {
        int[] a = new int[4];
        ArrayList<Car> ListOfCars = new ArrayList<Car>();
        ListOfCars.add(new Car("MarutiSuzuki", "Swift", 18, 2015, 10000));
        ListOfCars.add(new Car("KIA", "Soul", 19, 2016, 12000));
        ListOfCars.add(new Car("Ford", "MustangGT", 21, 2018, 34000));
        ListOfCars.add(new Car("Maserati", "Ghibli", 22, 2019, 78000));
        var Sorted = ListOfCars.stream()
                .map((Car car) -> car.getMileage())
                .sorted()
                .collect(Collectors.toList());
        System.out.println(Sorted);
        //sorting the array into higher and lower bound
        int threshold = 20;
        ArrayList<Integer>H = new ArrayList<>();
        ArrayList<Integer>L = new ArrayList<>();
        var high = ListOfCars.stream()
                .map((Car car) -> {if(car.getMileage() >= threshold){H.add(car.getMileage());}return car.getMileage();})
                .collect(Collectors.toList());

        System.out.println("high bound=" + H);
        var minmilage = H.stream().mapToInt(Integer::intValue).min();
        var maxmilage = H.stream().mapToInt(Integer::intValue).max();
        var avgmilage = H.stream().mapToInt(Integer::intValue).average();
        System.out.println("min max and avg value of mileage low bound "+minmilage.getAsInt()+","+maxmilage.getAsInt()+","+avgmilage.getAsDouble());

        var low = ListOfCars.stream()
                .map((Car car) -> {if(car.getMileage() < threshold){L.add(car.getMileage());}return car.getMileage();})
                .collect(Collectors.toList());
        minmilage = L.stream().mapToInt(Integer::intValue).min();
        maxmilage = L.stream().mapToInt(Integer::intValue).max();
        avgmilage = L.stream().mapToInt(Integer::intValue).average();
        System.out.println("low bound="+L);
        System.out.println("min max and avg value of mileage low bound "+minmilage.getAsInt()+","+maxmilage.getAsInt()+","+avgmilage.getAsDouble());

        var SortedYear = ListOfCars.stream()
                .map((Car car) -> car.getYear())
                .sorted()
                .collect(Collectors.toList());
        System.out.println(SortedYear);
        //sorting the array into higher and lower bound
        H.clear();
        high = ListOfCars.stream()
                .map((Car car) -> {if(car.getYear() >= 2017){H.add(car.getYear());}return car.getYear();})
                .collect(Collectors.toList());
        System.out.println("high bound=" + H);
        minmilage = H.stream().mapToInt(Integer::intValue).min();
        maxmilage = H.stream().mapToInt(Integer::intValue).max();
        avgmilage = H.stream().mapToInt(Integer::intValue).average();
        System.out.println("min max and avg value of car year low bound "+minmilage.getAsInt()+","+maxmilage.getAsInt()+","+avgmilage.getAsDouble());
        L.clear();
        low = ListOfCars.stream()
                .map((Car car) -> {if(car.getYear() < 2017){L.add(car.getYear());}return car.getYear();})
                .collect(Collectors.toList());
        minmilage = L.stream().mapToInt(Integer::intValue).min();
        maxmilage = L.stream().mapToInt(Integer::intValue).max();
        avgmilage = L.stream().mapToInt(Integer::intValue).average();
        System.out.println("low bound="+L);
        System.out.println("min max and avg value of car year low bound "+minmilage.getAsInt()+","+maxmilage.getAsInt()+","+avgmilage.getAsDouble());

        H.clear();
        var SortedPrice = ListOfCars.stream()
                .map((Car car) -> car.getPrice())
                .sorted()
                .collect(Collectors.toList());
        System.out.println(SortedPrice);
        //sorting the array into higher and lower bound
//        ArrayList<Integer>Hp = new ArrayList<>();
//        ArrayList<Integer>Lp = new ArrayList<>();
        high = ListOfCars.stream()
                .map((Car car) -> {if(car.getPrice() >= 15000){H.add(car.getPrice());}return car.getPrice();})
                .collect(Collectors.toList());
        System.out.println("high bound=" + H);
        minmilage = H.stream().mapToInt(Integer::intValue).min();
        maxmilage = H.stream().mapToInt(Integer::intValue).max();
        avgmilage = H.stream().mapToInt(Integer::intValue).average();
        System.out.println("min max and avg value of car price low bound "+minmilage.getAsInt()+","+maxmilage.getAsInt()+","+avgmilage.getAsDouble());
        L.clear();
        low = ListOfCars.stream()
                .map((Car car) -> {if(car.getPrice() < 15000){L.add(car.getPrice());}return car.getPrice();})
                .collect(Collectors.toList());
        minmilage = L.stream().mapToInt(Integer::intValue).min();
        maxmilage = L.stream().mapToInt(Integer::intValue).max();
        avgmilage = L.stream().mapToInt(Integer::intValue).average();
        System.out.println("low bound="+L);
        System.out.println("min max and avg value of car price low bound "+minmilage.getAsInt()+","+maxmilage.getAsInt()+","+avgmilage.getAsDouble());

        H.clear();
        //Car obj=new Car(String make='', String model, int mileage, int year, int price);
        Car c = new Car("MarutiSuzuki", "Swift", 18, 2015, 10000);
        c.setDominationCount(ListOfCars);

        var SortedDomination = ListOfCars.stream()
                .map((Car car) -> car.getDominationCount())
                .sorted()
                .collect(Collectors.toList());
        System.out.println(SortedDomination);

        high = ListOfCars.stream()
                .map((Car car) -> {if(car.getDominationCount() >= 2){H.add(car.getDominationCount());}return car.getDominationCount();})
                .collect(Collectors.toList());
        System.out.println("high bound=" + H);
        minmilage = H.stream().mapToInt(Integer::intValue).min();
        maxmilage = H.stream().mapToInt(Integer::intValue).max();
        avgmilage = H.stream().mapToInt(Integer::intValue).average();
        System.out.println("min max and avg value of car price low bound "+minmilage.getAsInt()+","+maxmilage.getAsInt()+","+avgmilage.getAsDouble());

        L.clear();
        low = ListOfCars.stream()
                .map((Car car) -> {if(car.getDominationCount() < 2){L.add(car.getDominationCount() );}return car.getDominationCount() ;})
                .collect(Collectors.toList());
        minmilage = L.stream().mapToInt(Integer::intValue).min();
        maxmilage = L.stream().mapToInt(Integer::intValue).max();
        avgmilage = L.stream().mapToInt(Integer::intValue).average();
        System.out.println("low bound="+L);
        System.out.println("min max and avg value of car price low bound "+minmilage.getAsInt()+","+maxmilage.getAsInt()+","+avgmilage.getAsDouble());

    }
}


