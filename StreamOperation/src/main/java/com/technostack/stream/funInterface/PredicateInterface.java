package com.technostack.stream.funInterface;

import com.technostack.stream.model.Product;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateInterface {

    //🔹 1. Beginner Level (Basic Predicate<T> Usage) – 15 Questions

    // 1.	✅ Check if any product name equals "Laptop".
    public static boolean checkIfAnyProductNamesEqualstoLaptop(List<Product> productList){
        Predicate<Product> namePredicate = product -> product.getName().equalsIgnoreCase("laptop");
        return productList.stream().anyMatch(namePredicate);

        //return productList.stream().map(Product::getName).anyMatch(name -> name.equalsIgnoreCase("laptop"));
    }

    // 2.	✅ Check if any product price is greater than 500.
    public static boolean checkIfAnyProductPriceIsGreaterThan500(List<Product> productList){
        return productList.stream().map(Product::getPrice).anyMatch(price -> price > 500);
    }

    //3.	✅ Filter all products whose price is less than 300.
    public static List<Product> filterAllProductsWhosePriceIsLessTha300(List<Product> productList){
        return productList.stream().filter(product -> product.getPrice() < 300).collect(Collectors.toList());
    }

    //5.	✅ Filter all products with name "Mouse".
    public static List<Product> filterProductsWIthMatchingName(List<Product> productList) {
        return productList.stream().filter(product -> product.getName().equalsIgnoreCase("Mouse")).collect(Collectors.toList());
    }
}
