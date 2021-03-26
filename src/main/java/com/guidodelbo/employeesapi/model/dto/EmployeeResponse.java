package com.guidodelbo.employeesapi.model.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder(toBuilder = true)
public class EmployeeResponse implements Serializable {
    private static final long serialVersionUID = -1255729209758092753L;

    String name;
    String email;
    String jobTitle;
    String phone;
    String imageUrl;
    String employeeCode;
}
