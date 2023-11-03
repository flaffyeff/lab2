package org.example;

import java.io.*;

public class Car implements Serializable {
    private String brand;
    private String model;
    private int year;
    private int price;

    //public Car() {
    //
    //}

    public void serializeObject(Car car, String fileName) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(car);
        }
    }
    public Car deserializeObject(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Car) inputStream.readObject();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        // Определите, какие поля объекта должны быть сериализованы
        out.writeObject(brand);
        out.writeObject(model);
        out.writeObject(year);
        out.writeObject(price);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // Восстановите объект из сериализованных данных
        brand = (String) in.readObject();
        model = (String) in.readObject();
        year = (int) in.readObject();
        price = (int) in.readObject();
    }
    // Метод для кастомной сериализации объекта в файл
    public void customSerializeObject(Car car, String fileName) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(car);
        }
    }

    // Метод для кастомной десериализации объекта из файла
    public Car customDeserializeObject(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Car) inputStream.readObject();
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Car(String brand, String model, int year, int price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    @Override
    public String toString() {
        return brand + " " + model + " (" + year + ") - $" + price;
    }

}
