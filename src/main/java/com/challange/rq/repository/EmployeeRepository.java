package com.challange.rq.repository;

import com.challange.rq.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(String id);

    String deleteEmployeeById(String id);

    Employee createEmployee(Map<String, Object> employeeInput);
}
