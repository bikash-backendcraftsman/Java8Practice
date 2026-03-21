package com.technostack.stream.model;

public class OrderItem {
    private String productId;
    private String name;
    private String category;
    private String brand;
    private int qty;
    private double unitPrice;
    private boolean returnable;

    public OrderItem(String productId, String name, String category, String brand, int qty, double unitPrice, boolean returnable) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.returnable = returnable;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isReturnable() {
        return returnable;
    }

    public void setReturnable(boolean returnable) {
        this.returnable = returnable;
    }

    public double revenue() {
        return qty * unitPrice;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", returnable=" + returnable +
                '}';
    }
}
