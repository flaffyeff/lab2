package org.example;

import java.util.ArrayList;
import java.util.List;

public class AutoSalon {
    private String name;
    private String address;
    private int phoneNumber;
    private int capacity;
    private List<Car> cars;

    public AutoSalon(String name, String address, int phoneNumber, int capacity) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
        this.cars = new ArrayList<Car>();
    }

    public void addCar(Car car) {
        if (cars.size() < capacity) {
            cars.add(car);
        } else {
            System.out.println("Автосалон заполнен до максимума");
        }
    }

    public void removeCar(Car car) {
        if (cars.contains(car)) {
            cars.remove(car);
        } else {
            System.out.println("Такого автомобиля нет в наличии!");
        }
    }

    public List<Car> showCars() {
        if (cars.size() == 0)
            System.out.println("Автомобилей нет в наличии");
        return cars;
    }

    public void sellCar(Car car) {
        if (cars.contains(car)) {
            cars.remove(car);
            System.out.println("Автомобиль " + car.getModel() + " продан!");
        } else {
            System.out.println("Такого автомобиля нет в наличии!");
        }
    }

    public int getTotalValue() {
        int total = 0;
        for (Car car : cars) {
            total += car.getPrice();
        }
        return total;
    }

    public Car getMostExpensiveCar() {
        Car mostExpensive = null;
        int highestPrice = Integer.MIN_VALUE;
        for (Car car : cars) {
            if (car.getPrice() > highestPrice) {
                highestPrice = car.getPrice();
                mostExpensive = car;
            }
        }
        return mostExpensive;
    }

    public Car getCheapestCar() {
        Car cheapest = null;
        int lowestPrice = Integer.MAX_VALUE;
        for (Car car : cars) {
            if (car.getPrice() < lowestPrice) {
                lowestPrice = car.getPrice();
                cheapest = car;
            }
        }
        return cheapest;
    }

    public void printInfo() {
        System.out.println("Название автосалона: " +name);
        System.out.println("Адрес автосалона: " +address);
        System.out.println("Телефон автосалона: " +phoneNumber);
        System.out.println("Вместимость автосалона: " +capacity);
        System.out.println("Автомобили в наличии: " +cars.size());
    }

    public Car getCar(String carBrand, String carModel) {
        for (Car car : cars) {
            if (car.getBrand().equals(carBrand) && car.getModel().equals(carModel)) {
                return car;
            }
        }
        return null; // если автомобиль не был найден
    }
}
