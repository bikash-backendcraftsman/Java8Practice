package com.technostack.stream.model;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
    }

    public static List<Product> createDummyProductList(){
        List<Product> productList = new ArrayList<>();
        Product p1 = new Product("Laptop", 1000.0, 10);
        Product p2 = new Product("Mobile", 500.0, 20);
        Product p3 = new Product("Tablet", 300.0, 30);
        Product p4 = new Product("Mouse", 100.0, 40);
        Product p5 = new Product("Keyboard", 200.0, 50);
        Product p6 = new Product("Monitor", 300.0, 60);
        Product p7 = new Product("Printer", 400.0, 70);
        Product p8 = new Product("Scanner", 500.0, 80);
        Product p9 = new Product("Camera", 600.0, 90);
        Product p10 = new Product("Mouse", 700.0, 100);
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
        productList.add(p4);
        productList.add(p5);
        productList.add(p6);
        productList.add(p7);
        productList.add(p8);
        productList.add(p9);
        productList.add(p10);
        return productList;
    }

}
