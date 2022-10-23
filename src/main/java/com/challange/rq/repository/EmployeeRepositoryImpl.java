package com.challange.rq.repository;

import com.challange.rq.client.RestApiClient;
import com.challange.rq.model.Employee;
import com.challange.rq.model.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final RestApiClient restApiClient;

    @Autowired
    public EmployeeRepositoryImpl(RestApiClient restApiClient) {
        this.restApiClient = restApiClient;
    }

    @Override
    public List<Employee> getAllEmployees(){
        log.info("Fetching list of all employees from rest client");
        RestResponse<List<Employee>> allEmployeesResponse = restApiClient.getAllEmployees();
        return allEmployeesResponse.getData();
    }

    @Override
    public Employee getEmployeeById(String id){
        log.info("Fetching employee by Id from rest client for Id {}", id);
        RestResponse<Employee> employeeByIdResponse = restApiClient.getEmployeeById(id);
        return employeeByIdResponse.getData();
    }

    @Override
    public String deleteEmployeeById(String id){
        log.info("Deleting employee having Id {}", id);
        RestResponse restResponse = restApiClient.deleteEmployeeById(id);
        return restResponse.getMessage();
    }

    @Override
    public Employee createEmployee(Map<String, Object> employeeInput){
        log.info("Creating new employee");
        RestResponse<Map<String, Object>> createEmployeeResp = restApiClient.createEmployee(employeeInput);
        Map<String, Object> employeeRespData = createEmployeeResp.getData();
        return new Employee(
            employeeRespData.get("id").toString(),
            employeeRespData.get("name").toString(),
            Integer.parseInt(employeeRespData.get("salary").toString()),
            Integer.parseInt(employeeRespData.get("age").toString()),
            employeeRespData.get("profile_image").toString()
        );
    }

}
