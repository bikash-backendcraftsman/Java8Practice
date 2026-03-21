package com.technostack.stream.model;

public class Customer {
    private String id;
    private String name;
    private String city;
    private String segment; // PREMIUM / NORMAL

    public Customer(String id, String name, String city, String segment) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.segment = segment;
    }

    public Customer() {

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", segment='" + segment + '\'' +
                '}';
    }
}
