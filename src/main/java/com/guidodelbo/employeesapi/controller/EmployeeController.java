package com.guidodelbo.employeesapi.controller;

import com.guidodelbo.employeesapi.model.dto.EmployeeRequest;
import com.guidodelbo.employeesapi.model.dto.EmployeeResponse;
import com.guidodelbo.employeesapi.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor(access = PACKAGE)
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/{employeeCode}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> get(
            @PathVariable("employeeCode") @NonNull final String employeeCode) {
        var employee = employeeService.getEmployee(employeeCode);

        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        var employees = employeeService.getAllEmployees();

        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest request) {
        var employee = employeeService.createEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping(value = "/{employeeCode}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(
            @PathVariable("employeeCode") @NonNull final String employeeCode,
            @RequestBody EmployeeRequest request) {
        employeeService.updateEmployee(employeeCode, request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{employeeCode}")
    public ResponseEntity<?> delete(
            @PathVariable("employeeCode") @NonNull final String employeeCode) {
        employeeService.deleteEmployee(employeeCode);

        return ResponseEntity.ok().build();
    }
}
