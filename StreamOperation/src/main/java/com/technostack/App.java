package com.technostack;

import com.technostack.stream.model.Employee;
import com.technostack.stream.operation.AllMatchOperation;

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
    }
}
