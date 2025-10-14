package com.technostack.model;

import java.util.Arrays;
import java.util.List;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private String category;
    private boolean defective;

    // constructor
    public Product(String name, double price, int quantity, String category, boolean defective) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.defective = defective;
    }

    // getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getCategory() { return category; }
    public boolean isDefective() { return defective; }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", defective=" + defective +
                '}';
    }
}
