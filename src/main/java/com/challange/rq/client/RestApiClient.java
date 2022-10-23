package com.challange.rq.client;

import com.challange.rq.model.Employee;
import com.challange.rq.model.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(url = "${dummyrest.base.url}", name = "restApiClient")
public interface RestApiClient {

    @GetMapping("/employees")
    RestResponse<List<Employee>> getAllEmployees();

    @GetMapping("/employee/{id}")
    RestResponse<Employee> getEmployeeById(@PathVariable("id") String id);

    @DeleteMapping("/delete/{id}")
    RestResponse deleteEmployeeById(@PathVariable("id") String id);

    @PostMapping("/create")
    RestResponse<Map<String, Object>> createEmployee(@RequestBody Map<String, Object> employeeInput);

}
