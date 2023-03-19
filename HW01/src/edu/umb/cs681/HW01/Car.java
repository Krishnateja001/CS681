package edu.umb.cs681.HW01;
import java.util.ArrayList;


public class Car {
    private String make, model;
    private int mileage, year;
    private int price;
    private int dominationCount;
    public Car(String make, String model, int mileage, int year, int price) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;

    }

    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }
    public void  setDominationCount(ArrayList<Car> cars) {
        this.dominationCount = 0;
        System.out.println("Inside domination");
        for (Car car : cars) {
            if(car.getMileage() <= this.getMileage() && car.getPrice() <= this.getPrice() && car.getYear() >= this.getYear()) {
                if(car.getMileage() < this.getMileage() || car.getPrice() < this.getPrice() || car.getYear() > this.getYear()) {
                    dominationCount++;
                }
            }
        }
    }

    public int getDominationCount() {
        return this.dominationCount;
    }
}