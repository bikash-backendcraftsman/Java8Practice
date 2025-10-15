package com.technostack.stream.funInterface.predicate;

import com.technostack.stream.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateInterfaceBeginner {

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


    // 	6.	✅ Find if there are no products with quantity 0.

    /**
     * Learning Note :
     * ==============
     * 🔍 Analysis:
     * 	•	You’re mapping the Product stream to Integer (quantity), then applying noneMatch.
     * 	•	This works technically — but you’re breaking the object context early.
     *
     * If use map(), technically correct but beraking object Context.
     *
     * ❌ Problem: You are calling .map(Product::getQuantity) early in the stream chain
     * 	•	That converts the stream from Stream<Product> to Stream<Integer>.
     * 	•	After that point, you’re no longer working with the full Product object, just with its quantity value.
     *
     * That’s what we mean by:
     *
     * “Breaking the object context early”
     *
     * ✅ Guiding Principle
     *
     * 🧠 Always keep the object (Product, Employee, etc.) in the stream as long as possible — until you really
     * need to map to a primitive or final result.
     */
    public static boolean filterProductsWithZeroQuantity(List<Product> productList){
        return productList.stream().noneMatch(product -> product.getQuantity() == 0);
    }

    // 	7.	✅ Check if any product’s price equals 300.

    public static boolean checkIfProductPriceWithMatchingAmount(List<Product> productList){
        Predicate<Product> checkPredicate = product -> product.getPrice() == 300;
        return productList.stream().anyMatch(checkPredicate);
    }

    // 8.	✅ Filter all products whose quantity is greater than 50.

    public static List<Product> filterAllProductWhoseQuantityIsGreaterThan50(List<Product> productList){
        return productList.stream().filter(product -> product.getQuantity() > 50).collect(Collectors.toList());
    }

    // 	9.	✅ Count how many products have price more than 400.

    public static long countProductWhosePriceIsMoreThan400(List<Product> productList){
        return productList.stream().filter(product -> product.getPrice() > 400).count();
    }

    // 	10.	✅ Use Predicate.isEqual() to check if a product equals a given product.


    // 	11.	✅ Check if all product names are non-null.

    /**
     * Learning Note :
     * ==============
     *
     * ❌ Using filter() — Possible, but Indirect
     *
     * You can write it like this:
     * long nonNullNameCount = productList.stream()
     *         .filter(product -> product.getName() != null)
     *         .count();
     *
     * return nonNullNameCount == productList.size();
     *
     * ✅ This works fine, but:
     * 	•	It’s less efficient (you traverse full list even if 1st is null)
     * 	•	Slightly more code
     * 	•	Less expressive than allMatch
     *
     * 	🧠 When is filter() better?
     * 	•	✅ When you’re building a List of items that meet the condition
     * 	•	✅ When you want to collect or transform the data
     * 	•	❌ Not ideal for true/false logic (use anyMatch, allMatch, noneMatch)
     */
    public static boolean checkIfProductsAreNonNull(List<Product> productList){
        return productList.stream().allMatch(product -> product.getName() != null);
    }

    // 12.	✅ Print all products where the price is divisible by 100.

    public List<Product> printAllProductWhosePriceIsDivisibleBy100(List<Product> productList){
        return productList.stream().filter(product -> product.getPrice() % 100 == 0).collect(Collectors.toList());
    }

    // 	13.	✅ Check if all product names have length > 3.

    public static boolean checkIfAllProductNamesLengthGreaterThan3(List<Product> productList){
        return productList.stream().allMatch(product -> product.getName() != null && product.getName().length() > 3);
    }

    // 	14.	✅ Find any product whose name starts with “M”.

    /**
     * Learning Note :
     * =================
     *
     * 🧠 Why findAny()?
     * 	•	findAny() → returns an Optional<Product> — any one matching product (non-deterministic in parallel stream).
     * 	•	findFirst() → returns the first product that matches, in encounter order.
     *
     * Both are correct — choose based on use case.
     */
    public static Optional<Product> findProductWhooseNameStartsWithLetterM(List<Product> productList){
       return productList.stream().filter(product -> product.getName().toLowerCase().startsWith("m")).findAny();

    }

    // 	15.	✅ Find the product(s) whose name ends with “r”.

    public static List<Product> getProductsWhooseNameEndsWithR(List<Product> productList){
        return productList.stream().filter(product -> product.getName().toLowerCase().endsWith("r")).collect(Collectors.toList());
    }
}
