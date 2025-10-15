package com.technostack;

import com.technostack.model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        // 1️⃣ Are all products non-defective?

        /**
         * Resoning
         * 1️⃣ getSampleProducts().stream() → creates a Stream of all Product objects.
         * 2️⃣ allMatch(...) → checks whether every product satisfies the condition.
         * 3️⃣ product -> !product.isDefective() → verifies that each product is not defective.
         * 4️⃣ ✅ If all products are non-defective, it returns true; otherwise false.
         * 5️⃣ ⚙️ For an empty list, it returns true (vacuously true).
         */
        boolean allGood = getSampleProducts().stream().allMatch(product -> !product.isDefective());
        System.out.println("Return Value :"+allGood);


        // 2️⃣ Is any product out of stock?

        /**
         * Resoning
         *
         * 1️⃣ Predicate<Product> productPredicate = product -> product.getQuantity() == 0;
         * → Creates a predicate that checks if a product’s quantity is 0 (out of stock).
         *
         * 2️⃣ getSampleProducts().stream()
         * → Creates a Stream of all Product objects.
         *
         * 3️⃣ .anyMatch(productPredicate)
         * → Returns true if at least one product satisfies the predicate (i.e., quantity is 0).
         *
         * 4️⃣ ✅ So if any product is out of stock, isProductOutOfStock becomes true; otherwise false.
         *
         * 5️⃣ ⚙️ For an empty list, it safely returns false (no match found).
         */
        Predicate<Product> productPredicate = product -> product.getQuantity() == 0;
        boolean isProductOutOfStock = getSampleProducts().stream().anyMatch(productPredicate);
        System.out.println("Is Product out of Stock :"+isProductOutOfStock);


        //3️⃣ Is no product priced below 1000 ₹?

        /**
         * Resoning
         *
         * 1️⃣ getSampleProducts().stream() → creates a stream of Product objects.
         * 2️⃣ .map(Product::getPrice) → transforms it into a stream of Double values (prices).
         * 3️⃣ .noneMatch(price -> price < 1000) → checks if any price in that stream is less than 1000.
         * 4️⃣ ✅ If at least one product has a price below 1000 → returns true; otherwise false.
         * 5️⃣ ⚙️ For an empty product list → returns false (no match found).
         *
         * Note : Here we can use noneMatch() and as well as map() + anyMatch() together 👏.
         */

        boolean returnedProductbelow = getSampleProducts().stream().map(Product::getPrice).noneMatch(price -> price < 1000);
        System.out.println("Returned Product below 1000 :"+returnedProductbelow);

        //4️⃣ Are all electronics priced above 30 000 ₹?


        /**
         * Resoning
         *
         * 1️⃣ getSampleProducts().stream() → creates a stream of all Product objects.
         * 2️⃣ .filter(pro -> pro.getCategory().equalsIgnoreCase("electronics")) → filters only those products whose category is “electronics” (case-insensitive).
         * 3️⃣ .allMatch(product -> product.getPrice() > 30000) → checks if all filtered electronics products have a price greater than ₹30,000.
         * 4️⃣ ✅ Returns true if all electronics meet the condition, otherwise false.
         * 5️⃣ ⚙️ If there are no electronics products, it returns true (vacuously true behavior of allMatch).
         */
        boolean elctronicsAbovePriced = getSampleProducts().stream().filter(pro -> pro.getCategory().equalsIgnoreCase("electronics"))
                .allMatch(product -> product.getPrice() > 30000);
        System.out.println("Electronic Above Priced :"+elctronicsAbovePriced);


        // 5️⃣ Is there any fashion product defective?

        /**
         * Resoning
         *
         * 1️⃣ Predicate<Product> fasionPredicate = product -> product.getCategory().equalsIgnoreCase("Fashion");
         * → Defines a predicate that selects only fashion category products.
         *
         * 2️⃣ getSampleProducts().stream()
         * → Creates a Stream of all Product objects.
         *
         * 3️⃣ .filter(fasionPredicate)
         * → Filters the stream to include only fashion products.
         *
         * 4️⃣ .anyMatch(prod -> prod.isDefective())
         * → Checks if any fashion product is defective.
         *
         * 5️⃣ ✅ Returns true if at least one fashion product is defective; otherwise false.
         * 6️⃣ ⚙️ If there are no fashion products, it safely returns false (no match found).
         */
        Predicate<Product> fasionPredicate = product -> product.getCategory().equalsIgnoreCase("Fashion");
        boolean isFashionProductDefective = getSampleProducts().stream().filter(fasionPredicate).anyMatch(prod -> !prod.isDefective());
        System.out.println("Fashion Product is Defective :"+isFashionProductDefective);
    }

    private static List<Product> getSampleProducts() {
        return Arrays.asList(
                new Product("iPhone 15", 89000, 10, "Electronics", false),
                new Product("Samsung TV", 55000, 0, "Electronics", false),
                new Product("HP Laptop", 60000, 5, "Electronics", true),
                new Product("Nike Shoes", 7000, 15, "Fashion", false),
                new Product("RayBan Glasses", 9500, 8, "Fashion", false)
        );
    }
}
