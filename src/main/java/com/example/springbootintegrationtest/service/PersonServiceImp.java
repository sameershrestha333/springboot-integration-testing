package com.example.springbootintegrationtest.service;

import com.example.springbootintegrationtest.model.Employee;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PersonServiceImp implements PersonApiService {

    private RestTemplate restTemplate;

    String url ="http://localhost:8080/api/employees/";

    public PersonServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Employee> getEmployees() {
        ResponseEntity<List<Employee>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
        });
        return response.getBody();
    }
}
