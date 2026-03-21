package com.technostack.stream.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private String id;
    private String customerId;
    private LocalDate orderDate;
    private String status; // PLACED, SHIPPED, DELIVERED, CANCELLED
    private List<OrderItem> items;
    private List<Payment> payments;
    private List<String> coupons;

    public Order(String id, String customerId, LocalDate orderDate, String status, List<OrderItem> items, List<Payment> payments, List<String> coupons) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.items = items;
        this.payments = payments;
        this.coupons = coupons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<String> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<String> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", items=" + items +
                ", payments=" + payments +
                ", coupons=" + coupons +
                '}';
    }
}
