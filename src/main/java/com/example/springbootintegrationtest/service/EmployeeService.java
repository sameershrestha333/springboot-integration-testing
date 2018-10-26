package com.example.springbootintegrationtest.service;

import com.example.springbootintegrationtest.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee findByName(String name);
    Employee findById(Long id);
    List<Employee> findAll();
    Employee save(Employee employee);
}
