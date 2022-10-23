package com.challange.rq.service;

import com.challange.rq.model.Employee;
import com.challange.rq.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private List<Employee> employees = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Employee emp1 = new Employee("1", "John Doe", 785000, 32, "www.dummy1.com");
        Employee emp2 = new Employee("2", "Ralo Tes", 5000, 34, "www.dummy2.com");
        Employee emp3 = new Employee("3", "Ashton Cox", 85000, 30, "www.dummy3.com");
        Employee emp4 = new Employee("4", "Rhona Doedson", 125000, 28, "www.dummy4.com");

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
    }

    @DisplayName("Test for fetch all employee")
    @Test
    void getAllEmployees() {
        given(employeeRepository.getAllEmployees()).willReturn(employees);
        List<Employee> allEmployees = employeeService.getAllEmployees();
        assertThat(allEmployees).isNotNull();
        assertEquals(employees.size(), allEmployees.size());
    }

    @DisplayName("Test for employees by search text")
    @Test
    void getEmployeesByNameSearch() {
        given(employeeRepository.getAllEmployees()).willReturn(employees);
        List<Employee> allEmployees = employeeService.getEmployeesByNameSearch("Doe");
        assertThat(allEmployees).isNotNull();
        assertEquals(2, allEmployees.size());
    }

    @DisplayName("Test for employee by id")
    @Test
    void getEmployeeById() {
        Employee emp1 = new Employee("1", "John Doe", 785000, 32, "www.dummy1.com");
        given(employeeRepository.getEmployeeById("1")).willReturn(emp1);
        Employee employeeById = employeeService.getEmployeeById("1");
        assertThat(employeeById).isNotNull();
        assertEquals("1", employeeById.getId());
    }

    @DisplayName("Test for searching employee by invalid id")
    @Test
    void getEmployeeByIdForInvalidId() {
        given(employeeRepository.getEmployeeById("1")).willReturn(null);
        Employee employeeById = employeeService.getEmployeeById("1");
        assertThat(employeeById).isNull();
    }

    @DisplayName("Test for highest salary")
    @Test
    void getHighestSalaryOfEmployee() {
        given(employeeRepository.getAllEmployees()).willReturn(employees);
        Integer highestSalaryOfEmployee = employeeService.getHighestSalaryOfEmployee();
        assertEquals(785000, highestSalaryOfEmployee);
    }
}