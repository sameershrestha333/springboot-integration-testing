package com.example.springbootintegrationtest.controller;

import com.example.springbootintegrationtest.model.Employee;
import com.example.springbootintegrationtest.service.PersonApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class PersonController {

     //String url = "http://localhost:8080/api/employees/";

    @Autowired
    private PersonApiService personApiService;

    @GetMapping("/persons")
    public List<Employee> getEmployee(){
        /*RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Employee>> exchange = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
        });
        return  exchange.getBody();*/
        return personApiService.getEmployees();
    }

}
