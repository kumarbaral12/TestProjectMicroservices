package com.example.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long departmentId;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
   // private Blob image;

}
