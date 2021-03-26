package com.guidodelbo.employeesapi.model.mapper.impl;

import com.guidodelbo.employeesapi.model.dto.EmployeeResponse;
import com.guidodelbo.employeesapi.model.entity.Employee;
import com.guidodelbo.employeesapi.model.mapper.EmployeeToEmployeeResponseMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeToEmployeeResponseMapperImpl implements EmployeeToEmployeeResponseMapper {

    @Override
    public EmployeeResponse apply(@NonNull final Employee employee) {
        return EmployeeResponse.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .jobTitle(employee.getJobTitle())
                .phone(employee.getPhone())
                .imageUrl(employee.getImageUrl())
                .employeeCode(employee.getEmployeeCode())
                .build();
    }

}
