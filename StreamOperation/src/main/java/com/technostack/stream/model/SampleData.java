package com.technostack.stream.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class SampleData {

    public static List<Customer> customers() {
        return Arrays.asList(
                new Customer("C1", "Bikash", "Bangalore", "PREMIUM"),
                new Customer("C2", "Amit", "Chennai", "NORMAL"),
                new Customer("C3", "Ravi", "Hyderabad", "PREMIUM"),
                new Customer("C4", "Neha", "Delhi", "NORMAL")
        );
    }

    public static List<Order> orders() {
        return Arrays.asList(
                new Order(
                        "O1", "C1", LocalDate.now().minusDays(5), "DELIVERED",
                        Arrays.asList(
                                new OrderItem("P1", "Laptop", "Electronics", "Dell", 1, 75000, true),
                                new OrderItem("P2", "Mouse", "Electronics", "Logitech", 2, 1500, true)
                        ),
                        Arrays.asList(
                                new Payment("CARD", 78000, "SUCCESS")
                        ),
                        Arrays.asList("NEWUSER")
                ),
                new Order(
                        "O2", "C2", LocalDate.now().minusDays(3), "SHIPPED",
                        Arrays.asList(
                                new OrderItem("P3", "Shampoo", "Grocery", "Dove", 3, 300, true),
                                new OrderItem("P4", "Oil", "Grocery", "Fortune", 1, 180, false)
                        ),
                        Arrays.asList(
                                new Payment("UPI", 1080, "SUCCESS")
                        ),
                        Arrays.asList()
                ),
                new Order(
                        "O3", "C3", LocalDate.now().minusDays(10), "DELIVERED",
                        Arrays.asList(
                                new OrderItem("P5", "T-Shirt", "Fashion", "Puma", 2, 2000, true)
                        ),
                        Arrays.asList(
                                new Payment("CARD", 4000, "FAILED"),
                                new Payment("CARD", 4000, "SUCCESS")
                        ),
                        Arrays.asList("FESTIVE")
                )
        );
    }

    public static List<Product_Record> products() {
        return Arrays.asList(
                new Product_Record("P1", "Laptop", "Electronics", "Dell", 4.5),
                new Product_Record("P2", "Mouse", "Electronics", "Logitech", 4.2),
                new Product_Record("P3", "Shampoo", "Grocery", "Dove", 4.0),
                new Product_Record("P4", "Oil", "Grocery", "Fortune", 3.8),
                new Product_Record("P5", "T-Shirt", "Fashion", "Puma", 4.3)
        );
    }

    public static List<Return> returns() {
        return Arrays.asList(
                new Return("O1", "P2", "DEFECTIVE", 1500),
                new Return("O3", "P5", "SIZE_ISSUE", 2000)
        );
    }
}
