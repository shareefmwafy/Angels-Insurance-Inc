//package com.asal.insurance_system.Model;
//
//
//import com.asal.insurance_system.Enum.EnumRolePermission;
//import jakarta.persistence.*;
//
//import java.util.Date;
//
//@Entity
//@Table(name = "employees")
//public class Employee extends User{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "employee_id")
//    private Integer employeeId;
//    private Integer departmentId;
//    private Date hiringDate;
//    private Float salary;
//
//    public Employee(String firstName, String secondName, String thirdName, String lastName, String phoneNumber, String email, String password, String country, String city, String street, String idNumber, EnumRolePermission role, Integer departmentId, Date hiringDate, Float salary) {
//        this.firstName = firstName;
//        this.secondName = secondName;
//        this.thirdName = thirdName;
//        this.lastName = lastName;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.password = password;
//        this.country = country;
//        this.city = city;
//        this.street = street;
//        this.idNumber = idNumber;
//        this.role = role;
//        this.departmentId = departmentId;
//        this.hiringDate = hiringDate;
//        this.salary = salary;
//    }
//
//    public Employee(String firstName, String lastName, String email, String password, String idNumber, EnumRolePermission role, Integer departmentId, Date hiringDate, Float salary) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.idNumber = idNumber;
//        this.role = role;
//        this.departmentId = departmentId;
//        this.hiringDate = hiringDate;
//        this.salary = salary;
//    }
//
//    public Employee() {
//    }
//
//    public Integer getEmployeeId() {
//        return employeeId;
//    }
//
//    public void setEmployeeId(Integer employeeId) {
//        this.employeeId = employeeId;
//    }
//
//    public Integer getDepartmentId() {
//        return departmentId;
//    }
//
//    public void setDepartmentId(Integer departmentId) {
//        this.departmentId = departmentId;
//    }
//
//    public Date getHiringDate() {
//        return hiringDate;
//    }
//
//    public void setHiringDate(Date hiringDate) {
//        this.hiringDate = hiringDate;
//    }
//
//    public Float getSalary() {
//        return salary;
//    }
//
//    public void setSalary(Float salary) {
//        this.salary = salary;
//    }
//
//
//}
