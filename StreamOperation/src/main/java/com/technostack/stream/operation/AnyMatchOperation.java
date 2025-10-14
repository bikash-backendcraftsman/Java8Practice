package com.technostack.stream.operation;

import com.technostack.stream.model.Product;

import java.util.List;

public class AnyMatchOperation {

    /**
     * ✅ Find if any product is out of stock (quantity == 0).
     * @param productList
     * @return true | false
     */

    /**
     * 1️⃣ productList.stream() → creates a stream of Product objects.
     * 2️⃣ anyMatch(...) → terminal operation that returns true if at least one product matches the condition.
     * 3️⃣ product -> product.getQuantity() == 0 → correctly checks if any product’s quantity is 0 (out of stock).
     */
    public static boolean checkIfAnyProductIsOutOfStock(List<Product> productList){
        boolean returnedVal = productList.stream().anyMatch(product -> product.getQuantity() == 0);
        return returnedVal;
    }

    /**
     * ✅ Check if all product prices are greater than 100.
     * @param pList
     * @return true | false
     */

    /**
     * 1️⃣ pList.stream() → creates a Stream of Product objects from the product list.
     * 2️⃣ allMatch(...) → a terminal operation that returns true only if all products satisfy the given condition.
     * 3️⃣ product -> product.getPrice() > 100 → the predicate condition checking whether each product’s price is greater than 100.
     * 4️⃣ ✅ If every product has price > 100 → returns true; otherwise → false.
     * 5️⃣ ⚙️ For an empty list, it still returns true (vacuously true behavior of allMatch).
     *
     */

    public static boolean checkIfProductPricesGreaterThan100(List<Product> pList){
        return pList.stream().allMatch(product -> product.getPrice() > 100);
    }
}
