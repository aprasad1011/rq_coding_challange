package com.challange.rq.controller;

import com.challange.rq.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class EmployeeControllerIT extends BaseIT {
    private static final String API_BASE_URL_EMPLOYEES = "/employees";

    @Test
    void shouldReturnAllEmployees() throws Exception {

        MvcResult response = mockMvc.perform(
            get(API_BASE_URL_EMPLOYEES)
        ).andReturn();
        List<Employee> allEmployees = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(allEmployees);
        assertEquals(4, allEmployees.size());
    }

    @Test
    void shouldReturnEmployeeDetailsForGivenEmployeeId() throws Exception{
        MvcResult response = mockMvc.perform(
            get(API_BASE_URL_EMPLOYEES + "/1")
        ).andReturn();
        Employee employee = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(employee);
        assertEquals("1", employee.getId());
    }

    @Test
    void shouldThrowExceptionIfEmployeeNotFoundForGivenId() throws Exception{
        MvcResult response = mockMvc.perform(
            get(API_BASE_URL_EMPLOYEES  + "/1234")
        ).andReturn();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getResponse().getStatus());
        assertEquals("Employee details not found for id 1234", response.getResolvedException().getMessage());
    }

    @Test
    void shouldReturnHighestSalary() throws Exception{
        MvcResult response = mockMvc.perform(
            get(API_BASE_URL_EMPLOYEES + "/highestSalary")
        ).andReturn();
        Integer highestSalary = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(785000, highestSalary);
    }

    @Test
    void shouldReturnTopTenHighestEarningEmployeeNames() throws Exception{
        MvcResult response = mockMvc.perform(
            get(API_BASE_URL_EMPLOYEES + "/topTenHighestEarningEmployeeNames")
        ).andReturn();
        List<String> topTenEmployeeWithHighestSalary = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(topTenEmployeeWithHighestSalary);
    }

    @Test
    void itShouldCreateNewEmployee() throws Exception{
        Map<String, Object> employeeDetails = new HashMap<>();
        employeeDetails.put("name", "Relia Quest");
        employeeDetails.put("salary", 323333);
        employeeDetails.put("age", 32);
        employeeDetails.put("profile_image", "dummy_url");
        MvcResult response = mockMvc.perform(
            post(API_BASE_URL_EMPLOYEES)
            .content(new ObjectMapper().writeValueAsString(employeeDetails))
            .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        Employee createdEmployee = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});
        assertNotNull(createdEmployee);
        assertEquals("1234", createdEmployee.getId());
    }

    @Test
    void shouldDeleteEmployeeForGivenId() throws Exception{
        MvcResult response = mockMvc.perform(
            delete(API_BASE_URL_EMPLOYEES + "/1")
        ).andReturn();
        String deleteResponse = response.getResponse().getContentAsString();
        assertNotNull(deleteResponse);
        assertEquals("Employee with id 1 deleted successfully", deleteResponse);
    }


}
