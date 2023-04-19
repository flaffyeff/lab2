package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AutoSalon autoSalon = new AutoSalon("LuNich AutoSalon", "пр. Независимости, 198", 123456789, 20);

        autoSalon.printInfo();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в Автосалон!");

        int choice;
        do {
            System.out.println("""
                    Выберите действие:
                    1)Добавить автомобиль в Автосалон
                    2)Вывести все автомобили, имеющиеся в салоне
                    3)Приобрести автомобиль
                    4)Вывести самый дорогой автомобиль
                    5)Вывести самый дешевый автомобиль
                    6)Выйти""");
            choice = scanner.nextInt();
            switch (choice) {
                case (1) -> {
                    System.out.println("Введите название автомобиля: ");
                    scanner.nextLine();
                    String brand = scanner.nextLine();
                    System.out.println("Введите модель автомобиля: ");
                    String model = scanner.nextLine();
                    System.out.println("Введите год выпуска автомобиля: ");
                    int year = scanner.nextInt();
                    System.out.println("Введите стоимость автомобиля: ");
                    int price = scanner.nextInt();
                    System.out.println("Автомобиль добавлен!");
                    Car car = new Car(brand, model, year, price);
                    autoSalon.addCar(car);
                }
                case (2) -> {
                    List<Car> cars = autoSalon.showCars();
                    for (Car c : cars) {
                        System.out.println(c);
                    }
                    System.out.println("Общая стоимость автосалона: $" +autoSalon.getTotalValue());
                }
                case (3) -> {
                    System.out.println("Введите ваше имя: ");
                    scanner.nextLine();
                    String firstName = scanner.nextLine();
                    System.out.println("Введите вашу фамилию: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Введите ваш мобильный телефон: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("Введите ваш бюджет: ");
                    long budget = scanner.nextLong();
                    Customer customer = new Customer(firstName, lastName, phoneNumber, budget);
                    System.out.println("Покупатель: " + customer.getFullName() + "\nБюджет: $" + customer.getBudget());

// Выберите авто из автосалона
                    System.out.println("Введите название автомобиля для покупки: ");
                    scanner.nextLine();
                    String carBrand = scanner.nextLine();
                    System.out.println("Введите модель автомобиля для покупки: ");
                    String carModel = scanner.nextLine();
                    Car carForSale = autoSalon.getCar(carBrand, carModel);

                    if (carForSale == null) {
                        System.out.println("Автомобиль не найден!");
                    } else if (carForSale.getPrice() > budget) {
                        System.out.println("Ваш бюджет мал для данного автомобиля! Выберите другой автомобиль");
                    } else {
                        autoSalon.sellCar(carForSale);
                        System.out.println("Автомобиль продан!");
                    }
                }
                case (4) -> {
                    Car mostExpensiveCar = autoSalon.getMostExpensiveCar();
                    System.out.println("Самая дорогая машина: " + mostExpensiveCar);
                }
                case (5) -> {
                    Car cheapestCar = autoSalon.getCheapestCar();
                    System.out.println("Самая дешевая машина: " + cheapestCar);
                }
                default -> {
                }
            }
        } while (choice != 6);
    }
}