package com.guidodelbo.employeesapi.service;

import com.guidodelbo.employeesapi.exception.ItemNotFoundException;
import com.guidodelbo.employeesapi.model.dto.EmployeeRequest;
import com.guidodelbo.employeesapi.model.dto.EmployeeResponse;
import com.guidodelbo.employeesapi.model.entity.Employee;
import com.guidodelbo.employeesapi.model.mapper.EmployeeToEmployeeResponseMapper;
import com.guidodelbo.employeesapi.repository.EmployeeRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeToEmployeeResponseMapper employeeToEmployeeResponseMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeToEmployeeResponseMapper employeeToEmployeeResponseMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeToEmployeeResponseMapper = employeeToEmployeeResponseMapper;
    }

    public EmployeeResponse getEmployee(@NonNull final String employeeCode) {
        var employee = employeeRepository.findByEmployeeCode(employeeCode)
                .orElseThrow(() -> new ItemNotFoundException("No employee with code [" + employeeCode + "] was found."));

        return EmployeeResponse.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .jobTitle(employee.getJobTitle())
                .phone(employee.getPhone())
                .imageUrl(employee.getImageUrl())
                .employeeCode(employee.getEmployeeCode())
                .build();
    }

    public List<EmployeeResponse> getAllEmployees() {
        var employees = employeeRepository.findAll();

        return employees.stream()
                .map(e -> EmployeeResponse.builder()
                        .name(e.getName())
                        .email(e.getEmail())
                        .jobTitle(e.getJobTitle())
                        .phone(e.getPhone())
                        .imageUrl(e.getImageUrl())
                        .employeeCode(e.getEmployeeCode())
                        .build())
                .collect(Collectors.toList());
    }

    public EmployeeResponse createEmployee(@NonNull final EmployeeRequest request) {
        // TODO: validar request
        var entity = Employee.builder()
                .name(request.getName())
                .email(request.getEmail())
                .jobTitle(request.getJobTitle())
                .phone(request.getPhone())
                .imageUrl(request.getImageUrl())
                .employeeCode(UUID.randomUUID().toString())
                .build();

        var employee = employeeRepository.save(entity);

        return employeeToEmployeeResponseMapper.apply(employee);
    }

    public void updateEmployee(@NonNull final String employeeCode, @NonNull final EmployeeRequest request) {
        var employee = employeeRepository.findByEmployeeCode(employeeCode)
                .orElseThrow(() -> new ItemNotFoundException("No employee with code [" + employeeCode + "] was found."));

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setJobTitle(request.getJobTitle());
        employee.setPhone(request.getPhone());
        employee.setImageUrl(request.getImageUrl());

        employeeRepository.save(employee);
    }

    public void deleteEmployee(@NonNull final String employeeCode) {
        var employee = employeeRepository.findByEmployeeCode(employeeCode)
                .orElseThrow(() -> new ItemNotFoundException("No employee with code [" + employeeCode + "] was found."));

        employeeRepository.delete(employee);
    }

}
