package com.example.springbootintegrationtest.Model;

import com.example.springbootintegrationtest.model.Employee;
import com.example.springbootintegrationtest.repository.EmployeeRepository;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TestEntityManager entityManager;

    private  Employee employee;

    @Test
    public void whenFindByName_thenReturnEmplyoee() {
        employee = new Employee("Sameer");

        entityManager.persist(employee);
        entityManager.flush();

        Employee returnedEmployee = employeeRepository.findByName(employee.getName());
        assertEquals(returnedEmployee.getName(), employee.getName());

    }

    @Test
    public void whenFindyByInvalidName_thenReutrnNull() {
        Employee returnedEmployed = employeeRepository.findByName("doesNotExits");
        assertNull(returnedEmployed);
    }

    @Test
    public void whenFindById_thenReturnEmployee() {
        employee = new Employee("Sameer");
        Employee employee = entityManager.persistAndFlush(this.employee);

       Employee returnEmployee = employeeRepository.findById(this.employee.getId()).orElse(null);
       assertEquals(returnEmployee.getName(),employee.getName());
    }
}

