package com.technostack.stream.model;

public class Product_Record {

    private String id;
    private String name;
    private String category;
    private String brand;
    private double rating;

    public Product_Record(String id, String name, String category, String brand, double rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", rating=" + rating +
                '}';
    }
}
