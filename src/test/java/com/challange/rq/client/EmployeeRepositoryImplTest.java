package com.challange.rq.client;

import com.challange.rq.model.Employee;
import com.challange.rq.repository.EmployeeRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Profile("test")
@Component
@Primary
public class EmployeeRepositoryImplTest implements EmployeeRepository {

    @Override
    public List<Employee> getAllEmployees() {
        Employee emp1 = new Employee("1", "John Doe", 785000, 32, "www.dummy1.com");
        Employee emp2 = new Employee("2", "Ralo Tes", 5000, 34, "www.dummy2.com");
        Employee emp3 = new Employee("3", "Ashton Cox", 85000, 30, "www.dummy3.com");
        Employee emp4 = new Employee("4", "Rhona Doedson", 125000, 28, "www.dummy4.com");

        return Arrays.asList(emp1, emp2, emp3, emp4);
    }

    @Override
    public Employee getEmployeeById(String id) {
        if("1234".equals(id)){
            return null;
        }
        return new Employee(id, "John Doe", 785000, 32, "www.dummy1.com");
    }

    @Override
    public String deleteEmployeeById(String id) {
        return "Employee with id " + id + " deleted successfully";
    }

    @Override
    public Employee createEmployee(Map<String, Object> employeeInput) {
        return new Employee(
            "1234",
            employeeInput.get("name").toString(),
            Integer.parseInt(employeeInput.get("salary").toString()),
            Integer.parseInt(employeeInput.get("age").toString()),
            employeeInput.get("profile_image").toString()
        );
    }
}
