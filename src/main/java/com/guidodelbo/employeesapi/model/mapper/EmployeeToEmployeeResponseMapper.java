package com.guidodelbo.employeesapi.model.mapper;

import com.guidodelbo.employeesapi.model.dto.EmployeeResponse;
import com.guidodelbo.employeesapi.model.entity.Employee;

import java.util.function.Function;

@FunctionalInterface
public interface EmployeeToEmployeeResponseMapper extends Function<Employee, EmployeeResponse> {
}
