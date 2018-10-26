package com.example.springbootintegrationtest.service;

import com.example.springbootintegrationtest.model.Employee;

import java.util.List;

public interface PersonApiService {
    List<Employee> getEmployees();
}
