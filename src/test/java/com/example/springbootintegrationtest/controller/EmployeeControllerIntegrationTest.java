package com.example.springbootintegrationtest.controller;


import com.example.springbootintegrationtest.model.Employee;
import com.example.springbootintegrationtest.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    String url = "/api/employees";

    @Test
    public void whenPostEmployee_thenCreateEmployee() throws Exception {
        Employee employee = buildEmployee();

        given(employeeService.save(Mockito.anyObject())).willReturn(employee);

        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(employee)))
                .andExpect(status().isCreated());

        verifyCreateEmployeeIsCalledOnce();

    }

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
        List<Employee> employees = buildEmployee(3);
        given(employeeService.findAll()).willReturn(employees);

        mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verifyGetAllEmployeeIsCalledOnce();
    }

    private void verifyGetAllEmployeeIsCalledOnce() {
        Mockito.verify(employeeService,VerificationModeFactory.times(1)).findAll();
        Mockito.reset();
    }

    private void verifyCreateEmployeeIsCalledOnce() {
        Mockito.verify(employeeService, VerificationModeFactory.times(1)).save(Mockito.anyObject());
        Mockito.reset(employeeService);
    }

    private Employee buildEmployee() {
        Employee sameer = new Employee("Sameer");
        return sameer;
    }

    private List<Employee> buildEmployee(int num) {
        Employee employee;
        List<Employee> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            employee = new Employee("Sameer " + i);
            list.add(employee);
        }
        return list;
    }
}
