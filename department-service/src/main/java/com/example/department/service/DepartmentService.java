package com.example.department.service;

import com.example.department.dto.DepartmentDto;
import com.example.department.entity.Department;
import com.example.department.repository.DepartmentRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        try {
            department.setImage(BlobProxy.generateProxy(Files.readAllBytes(Paths.get(department.getFileName()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return departmentRepository.save(department);
    }

    public Department getDepartment(Long id) {
        return departmentRepository.getById(id);
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }
}
