package com.example.department.controller;

import com.example.department.dto.DepartmentDto;
import com.example.department.entity.Department;
import com.example.department.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/saveDepartment")
    public DepartmentDto saveDepartment(@RequestBody Department department){
        return mapper.map(departmentService.saveDepartment(department), DepartmentDto.class);
    }

    @GetMapping("getDepartments")
    public List<DepartmentDto> getDepartments(){
        return departmentService.getDepartments().stream().map(dto -> mapper.map(dto, DepartmentDto.class)).collect(Collectors.toList());
    }

    @GetMapping("getDepartment/{id}")
    public DepartmentDto getDepartment(@PathVariable Long id){
        return mapper.map(departmentService.getDepartment(id), DepartmentDto.class);
    }
}
