package com.example.springbootintegrationtest.bootstrap;

import com.example.springbootintegrationtest.model.Employee;
import com.example.springbootintegrationtest.repository.EmployeeRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private EmployeeRepository employeeRepository;

    public DevBootStrap(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private void initData(){
        Employee employee = new Employee("Sameer");
        Employee employee1 = new Employee("Shrestha");

        employeeRepository.save(employee);
        employeeRepository.save(employee1);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
