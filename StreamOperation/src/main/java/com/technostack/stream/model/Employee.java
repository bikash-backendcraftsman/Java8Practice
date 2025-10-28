package com.technostack.stream.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private int age;
    private boolean isActive;
    private double salary;

    public Employee(String name, int age, boolean isActive,double salary) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                ", salary=" + salary +
                '}';
    }

    public static List<Employee> createDummyEmployeeData(){
        List<Employee> empList = new ArrayList<>();
        Employee e1 = new Employee("Bikash",23,true,50000);
        Employee e2 = new Employee("Tapan",32,true,70000);
        Employee e3 = new Employee("Muskan",32,true,90000);
        Employee e4 = new Employee("Suman",33,false,30000);
        Employee e5 = new Employee("Guntur",36,true,3000);
        Employee e6 = new Employee("Mayur",56,false,8000);
        Employee e7 = new Employee("Aakash",67,true,17000);
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        empList.add(e4);
        empList.add(e5);
        empList.add(e6);
        empList.add(e7);
        return empList;
    }
}
