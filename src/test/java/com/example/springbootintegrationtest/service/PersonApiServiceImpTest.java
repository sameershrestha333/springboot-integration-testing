package com.example.springbootintegrationtest.service;

import com.example.springbootintegrationtest.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonApiServiceImpTest {

    @Autowired
    private PersonApiService personApiService;

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getEmployees() {
        mockServer.expect(requestTo("http://localhost:8080")).andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));
        List<Employee> employees = personApiService.getEmployees();
        assertEquals(2,employees.size());

    }
}