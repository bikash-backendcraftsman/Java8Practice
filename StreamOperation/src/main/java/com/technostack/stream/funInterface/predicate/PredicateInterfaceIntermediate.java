package com.technostack.stream.funInterface.predicate;

import com.technostack.stream.model.Product;

import java.time.Period;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateInterfaceIntermediate {

    // 🔹 2. Intermediate Level (Predicate + Method Chaining) – 20 Questions

    // 16.	🔁 Filter products with price > 300 and quantity > 50.
    public static List<Product> filterProductbasedOnPriceAndQuantity(List<Product> productList) {
        Predicate<Product> pricePredicate = product -> product.getPrice() > 300;
        Predicate<Product> quantityPredicate = product -> product.getQuantity() > 50;

        Predicate<Product> priceAndQuantityPredicate = pricePredicate.and(quantityPredicate);
        return productList.stream().filter(priceAndQuantityPredicate).collect(Collectors.toList());
    }

    // 17.	🔁 Filter products with name starting with “M” or price < 250.

    /**
     * Learning Note :
     * <p>
     * 🧠 Now, the important part:
     * ❓ How does filter() work internally?
     * <p>
     * Let’s understand how .filter() → .collect(Collectors.toList()) works:
     * List<Product> productList = createDummyProductList();
     * <p>
     * List<Product> result = productList.stream()
     * .filter(product -> condition)   // step 1
     * .collect(Collectors.toList());  // step 2
     * <p>
     * 🔍 Internally (what happens):
     * <p>
     * 1️⃣ .stream()
     * •	Creates a lazy stream (does not process elements yet)
     * <p>
     * 2️⃣ .filter(predicate)
     * •	Accepts a Predicate, which returns true or false for each element
     * •	Internally calls:
     * if (predicate.test(product)) {
     * // allow the product to pass through the stream pipeline
     * }
     * <p>
     * •	Only those elements for which the predicate returns true are passed to the next step
     * <p>
     * 3️⃣ .collect(Collectors.toList())
     * •	Consumes the stream and collects all passing elements into a List
     * <p>
     * Internally, it does something like this: [very very Important]
     * =========================================
     * List<Product> result = new ArrayList<>();
     * for (Product product : stream) {
     * if (predicate.test(product)) {
     * result.add(product);
     * }
     * }
     * ===========================================
     * 🧠 Key Concept:
     * <p>
     * ✅ .filter() is a “stream pipeline stage” that acts like a sieve — allowing only those elements through that satisfy the given condition.
     * <p>
     * Then .collect() simply collects the passing elements into a container (like List, Set, etc.)
     * <p>
     * filter()   :    Applies a condition and passes only matching elements
     * Predicate result :   Must return true to continue to next stage
     * collect()   :      Gathers the filtered stream elements into a container
     */

    public static List<Product> filterProductbasedonNameAndPrice(List<Product> productList) {
        return productList.stream().filter(product -> product.getName().toLowerCase().startsWith("m") || product.getPrice() < 250).collect(Collectors.toList());
    }

    // 18.	🔁 Use negate() to exclude all products with price < 400.

    public static List<Product> excludeProductsPriceIsLessThan400(List<Product> productList) {
        Predicate<Product> productPredicate = product -> product.getPrice() < 400;
        return productList.stream().filter(productPredicate.negate()).collect(Collectors.toList());
    }

    // 19.	🔁 Check if any product has quantity exactly 70 and price >= 400.

    public static boolean checkProductQuantityIs70AndPriceGreaterThan400(List<Product> productList) {
        Predicate<Product> qPredicate = product -> product.getQuantity() == 70;
        Predicate<Product> pricePred = product -> product.getPrice() >= 400;

        Predicate<Product> jointPredicate = qPredicate.and(pricePred);

        return productList.stream().anyMatch(jointPredicate);
    }

    // 20.	🔁 Use Predicate<Product> with .removeIf() to remove low stock (< 20) products.

    public static boolean getProductWIthLowStockBelow20(List<Product> productList) {
        Predicate<Product> productPredicate = product -> product.getQuantity() < 20;
        return productList.removeIf(productPredicate);
    }

    // 21.	🔁 Find all products with name containing “a” and quantity < 80.

    public static List<Product> findNamesBasedonLetterAndQuantity(List<Product> productList) {
        return productList.stream().filter(product -> product.getName().toLowerCase().contains("a")
                && product.getQuantity() < 80).collect(Collectors.toList());
    }

    // 22.	🔁 Use method reference for a predicate that checks Product::getPrice > 500.

    public static boolean isPriceAbove500(Product product) {
        return product.getPrice() > 500;
    }

    public static boolean checkProductPriceGreaterThan500(List<Product> productList) {
        return productList.stream().anyMatch(PredicateInterfaceIntermediate::isPriceAbove500);
    }

    // 23.	🔁 Filter products where name contains "o" and price is divisible by 100.

    // 24.	🔁 Combine multiple predicates: starts with “M”, price > 200, quantity < 90.

    // 25.	🔁 Find if none of the products have quantity more than 100.

    // 26.	🔁 Count how many products have name length < 6.

    // 27.	🔁 Filter products where name does not contain “e”.

    // 28.	🔁 Use a Predicate<Product> that checks name equalsIgnoreCase "MOUSE".

    // 29.	🔁 Filter products where quantity is a multiple of 10 and price > 400.

    // 30.	🔁 Check if all product names are in UPPERCASE (optional enhancement).

    // 31.	🔁 Filter products where price is between 300 and 600 (inclusive).

    // 32.	🔁 Find products where name starts with “K” or “S”.

    // 33.	🔁 Filter products whose name has vowels more than 2.

    // 34.	🔁 Find if any product’s name contains “top” (e.g., “Laptop”).

    // 35.	🔁 Exclude all products with duplicate names using Predicate + helper logic.
}
