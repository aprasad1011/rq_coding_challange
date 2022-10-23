package com.challange.rq.controller;

import com.challange.rq.exception.EmployeeNotFoundException;
import com.challange.rq.model.Employee;
import com.challange.rq.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeControllerImpl implements IEmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeControllerImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() {
        log.info("Request received to get list of all employees");
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
        log.info("Request received to get list of employees containing {} in name", searchString);
        return ResponseEntity.ok(employeeService.getEmployeesByNameSearch(searchString));
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) {
        log.info("Request received to get employee with id {} ", id);
        Employee employeeById = employeeService.getEmployeeById(id);
        if(employeeById == null){
            throw new EmployeeNotFoundException("Employee details not found for id " + id);
        }
        return ResponseEntity.ok(employeeById);
    }

    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        log.info("Request received to get highest salary of employees");
        return ResponseEntity.ok(employeeService.getHighestSalaryOfEmployee());
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        log.info("Request received to get top ten highest earning employee names");
        return ResponseEntity.ok(employeeService.getTopTenHighestEarningEmployeesName());
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) {
        log.info("Request received to create new employee");
        return ResponseEntity.ok(employeeService.createEmployee(employeeInput));
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) {
        log.info("Request received to delete employee with id {}", id);
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }
}
