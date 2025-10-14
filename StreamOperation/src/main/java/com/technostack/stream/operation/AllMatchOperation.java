package com.technostack.stream.operation;

import com.technostack.stream.model.Employee;

import java.util.List;
import java.util.function.Predicate;

public class AllMatchOperation {


   // ✅ Check if all employees are active.
   //🔹 2️⃣ Stream + allMatch
    public static boolean checkIfAllEmployeeAreActive(List<Employee> employeeList){
        Predicate<Employee> empPredicate = emp -> emp.isActive();
        return employeeList != null && !employeeList.isEmpty() && employeeList.stream().allMatch(empPredicate);
    }
}
