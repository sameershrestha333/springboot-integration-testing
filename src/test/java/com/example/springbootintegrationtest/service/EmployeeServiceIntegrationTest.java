package com.example.springbootintegrationtest.service;

import com.example.springbootintegrationtest.model.Employee;
import com.example.springbootintegrationtest.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class EmployeeServiceIntegrationTest {

    @TestConfiguration
    static class EmployeeServiceImpTestConfiguration {

        @Bean
        public EmployeeService employeeService() {
            return new EmployeeServiceImp();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;


    @Before
    public void setUp() {
        Employee sameer = new Employee("Sameer");
        sameer.setId(101L);

        Employee john = new Employee("John");
        Employee alex = new Employee("Alex");
        List<Employee> allEmployees = Arrays.asList(sameer, john, alex);

        Mockito.when(employeeRepository.findByName(sameer.getName())).thenReturn(sameer);
        Mockito.when(employeeRepository.findById(sameer.getId())).thenReturn(Optional.of(sameer));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
    }

    @Test
    public void whenValidName_thenReturnEmplyee() {
        String name = "Sameer";
        Employee returnedEmplyee = employeeService.findByName(name);

        assertEquals(returnedEmplyee.getName(), name);
        varifyFindByNameCalledOnce("Sameer");
    }

    private void varifyFindByNameCalledOnce(String sameer) {
        Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findByName(sameer);
        Mockito.reset(employeeRepository);
    }

    @Test
    public void whenInValidName_thenNull() {
        String name = "Shrestha";
        Employee returnedEmplyee = employeeService.findByName(name);
        assertNull(returnedEmplyee);
    }

    @Test
    public void whenValidId_thenEmployee() {
        Long id = 101L;
        Employee returnedEmplyee = employeeService.findById(id);
        assertEquals(returnedEmplyee.getId(), id);

        verificationFindByIdIsCalledOnce(id);
    }

    private void verificationFindByIdIsCalledOnce(Long id) {
        Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findById(id);
        Mockito.reset(employeeRepository);
    }

    @Test
    public void getAll3records() {
        Employee sameer = new Employee("Sameer");
        Employee john = new Employee("John");
        Employee alex = new Employee("Alex");
        List<Employee> returnedEmployee = employeeService.findAll();
        assertEquals(3, returnedEmployee.size());
        verificationFindAllIsCalledOnce();
        assertThat(returnedEmployee).hasSize(3).extracting(Employee::getName).contains(sameer.getName(), john.getName(), alex.getName());

    }

    private void verificationFindAllIsCalledOnce() {
        Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(employeeRepository);
    }
}
