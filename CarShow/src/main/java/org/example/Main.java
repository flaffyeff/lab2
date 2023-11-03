package org.example;

import zxc.lib.PrintCars;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, MalformedURLException {
        PrintCars printCars = new PrintCars();
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
                    6)Добавить случайный автомобиль
                    7)Выйти""");
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
//                    for (Car c : cars) {
//                        System.out.println(c);
//                    }
//                    System.out.println("Общая стоимость автосалона: $" +autoSalon.getTotalValue());
                    printCars.print(cars);
                   /* Car car1 = new Car("BMW", "X1", 2020, 25000);
                    Car car2 = new Car("TESLA", "Model S", 2018, 30000);
                    Car car3 = new Car("MERCEDES", "BENZ", 2019, 28000);
                    cars.add(car1);
                    cars.add(car2);
                    cars.add(car3);*/
                    String jarFilePath = "D:\\КПО\\lab1\\lib.jar";

                    // Создайте экземпляр URLClassLoader с указанным путем к JAR-файлу
                    URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file:" + "D:\\КПО\\lab1\\lib.jar")});

                    // Имя класса, который вы хотите загрузить
                    String className = "zxc.lib.PrintCars";

                    // Загрузите класс с использованием URLClassLoader
                    Class<?> loadedClass = classLoader.loadClass(className);

                    Object instance = loadedClass.newInstance();

                    loadedClass.getMethod("print", List.class).invoke(instance, cars);
                    /*saveCarsToFile(cars, "cars.txt");*/
                    saveCarsToBinaryFile(autoSalon.showCars(), "carsDataBinaryFile.bin");
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
                case (6) -> {
                    //addRandomCarsFromFile(autoSalon, "names.txt");
                    addRandomCarsFromBinaryFile(autoSalon, "carsDataBinaryFile.bin");
                }
                default -> {
                }
            }
        } while (choice != 7);
    }

    private static void saveCarsToFile(List<Car> cars, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Car car : cars) {
                writer.write(car.toString());
                writer.newLine();
            }
            System.out.println("Данные об автомобилях были успешно сохранены в файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private static void addRandomCarsFromFile(AutoSalon autoSalon, String fileName) {
        try {
            File file = new File("D:\\КПО\\lab1\\names.txt");
            Scanner fileScanner = new Scanner(file);
            List<String[]> carDataList = new ArrayList<>();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] carData = line.split(","); // Предполагаем, что данные разделены запятыми

                if (carData.length == 2) {
                    carDataList.add(carData);
                }
            }
            fileScanner.close();

            Random random = new Random();

            for (String[] carData : carDataList) {
                String brand = carData[0];
                String model = carData[1];

                // Генерация случайных значений для создания машин
                int randomYear = 1990 + random.nextInt(31); // случайный год в диапазоне от 1990 до 2020
                int randomPrice = 5000 + random.nextInt(95001); // случайная цена от $5000 до $100000

                Car car = new Car(brand, model, randomYear, randomPrice);
                autoSalon.addCar(car);
            }

            System.out.println("Рандомные автомобили из файла были успешно добавлены в Автосалон.");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private static void saveCarsToBinaryFile(List<Car> cars, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(cars);
            System.out.println("Данные об автомобилях были успешно сохранены в бинарный файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в бинарный файл: " + e.getMessage());
        }
    }

    private static void addRandomCarsFromBinaryFile(AutoSalon autoSalon, String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Car> carDataList = (List<Car>) ois.readObject();

            for (Car car : carDataList) {
                autoSalon.addCar(car);
            }

            System.out.println("Данные об автомобилях были успешно добавлены из бинарного файла в Автосалон.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при чтении из бинарного файла: " + e.getMessage());
        }
    }
}