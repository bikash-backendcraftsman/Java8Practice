package com.technostack;

import com.technostack.stream.funInterface.predicate.PredicateInterfaceBeginner;
import com.technostack.stream.model.Employee;
import com.technostack.stream.model.Product;
import com.technostack.stream.operation.AllMatchOperation;
import com.technostack.stream.operation.AnyMatchOperation;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        List<Employee> dummyEmployeeData = Employee.createDummyEmployeeData();
        boolean b = AllMatchOperation.checkIfAllEmployeeAreActive(dummyEmployeeData);
        System.out.println(b);


        boolean b1 = AnyMatchOperation.checkIfAnyProductIsOutOfStock(Product.createDummyProductList());
        System.out.println("Print Val :"+b1);

        /**
         * Predicate Functional Interface ALl question : Beginner level
         */

        // 1.	✅ Check if any product name equals "Laptop".
         boolean val = PredicateInterfaceBeginner.checkIfAnyProductNamesEqualstoLaptop(Product.createDummyProductList());
         System.out.println("Check if any Product name Equals Laptop :"+val);

        // 2.	✅ Check if any product price is greater than 500.
        boolean greaterThan500 = PredicateInterfaceBeginner.checkIfAnyProductPriceIsGreaterThan500(Product.createDummyProductList());
        System.out.println("Check if any Product price is Greater than 500 :"+greaterThan500);

        //3.	✅ Filter all products whose price is less than 300.



    }
}
