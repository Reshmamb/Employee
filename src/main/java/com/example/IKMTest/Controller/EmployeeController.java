package com.example.IKMTest.Controller;

import com.example.IKMTest.Common.Contract.Response;
import com.example.IKMTest.Contract.EmployeeRequest;
import com.example.IKMTest.Contract.EmployeeResponse;
import com.example.IKMTest.Model.Employee;
import com.example.IKMTest.Service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @SneakyThrows
    @Operation(summary = "Create Employee")
    @PostMapping(value = {"/save-employee"})
    public ResponseEntity<Response> saveEmployee(@Valid @RequestBody EmployeeRequest request) {

        Employee employee = employeeService.saveEmployee(request, false);
        return new ResponseEntity<>(
                Response.builder().payload(employee.getId()).message("Success").build(),
                HttpStatus.CREATED);
    }

    @SneakyThrows
    @Operation(summary = "Update Employee")
    @PostMapping(value = {"/update-employee"})
    public ResponseEntity<Response> updateEmployee(@Valid @RequestBody EmployeeRequest request) {

        Employee employee = employeeService.saveEmployee(request, true);
        return new ResponseEntity<>(
                Response.builder().payload(employee.getId()).message("Success").build(),
                HttpStatus.CREATED);
    }

    @GetMapping("/fetch-by-name/{empName}")
    public ResponseEntity<Response<EmployeeResponse>> getByName(
            @PathVariable("empName") String name) {
        return new ResponseEntity<>(
                Response.<EmployeeResponse>builder()
                        .payload(employeeService.getByName(name))
                        .message("record fetched successfully.")
                        .build(),
                HttpStatus.OK);
    }
}
