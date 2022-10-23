package com.challange.rq.service;

import com.challange.rq.model.Employee;
import com.challange.rq.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return allEmployeeList();
    }

    public List<Employee> getEmployeesByNameSearch(String searchString) {
        List<Employee> allEmployees = allEmployeeList();
        return allEmployees.stream()
            .filter(emp -> emp.getEmployeeName().toLowerCase().contains(searchString.toLowerCase()))
            .collect(Collectors.toList());
    }

    public Employee getEmployeeById(String id) {
        return employeeRepository.getEmployeeById(id);
    }


    public Integer getHighestSalaryOfEmployee() {
        return allEmployeeList().stream()
            .max(Comparator.comparing(Employee::getEmployeeSalary))
            .map(Employee::getEmployeeSalary)
            .get();
    }

    public List<String> getTopTenHighestEarningEmployeesName() {
        return allEmployeeList().stream()
            .sorted(Comparator.comparing(Employee::getEmployeeSalary).reversed())
            .map(Employee::getEmployeeName)
            .limit(10)
            .collect(Collectors.toList());
    }

    public Employee createEmployee(Map<String, Object> employeeInput) {
        Employee createdEmployee = employeeRepository.createEmployee(employeeInput);
        log.info("new employee created with id {}", createdEmployee.getId());
        return createdEmployee;
    }

    public String deleteEmployee(String id) {
        return employeeRepository.deleteEmployeeById(id);
    }

    private List<Employee> allEmployeeList(){
        return employeeRepository.getAllEmployees();
    }
}
