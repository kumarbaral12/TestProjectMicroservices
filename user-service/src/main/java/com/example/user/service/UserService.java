package com.example.user.service;

import com.example.user.common.Department;
import com.example.user.common.UserDepartment;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public UserDepartment getUserWithDepartment(Long id) {
        UserDepartment userDepartment = new UserDepartment();
        User user = userRepository.findById(id).get();
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/getDepartment/" + user.getDepartmentId(), Department.class);
        userDepartment.setDepartment(department);
        userDepartment.setUser(user);
        return userDepartment;
    }

    public List<UserDepartment> getUsersWithDepartments() {
        List<UserDepartment> list = new ArrayList<>();
        List<User> users = userRepository.findAll();
        ResponseEntity<Department[]> departments = restTemplate.getForEntity("http://DEPARTMENT-SERVICE/departments/getDepartments", Department[].class);
        Department[] departmentList = departments.getBody();

        UserDepartment userDepartment = new UserDepartment();
        for(int i = 0; i < users.size(); i++){
            userDepartment.setUser(users.get(i));
            assert departmentList != null;
            userDepartment.setDepartment(departmentList[i]);
            list.add(userDepartment);
        }
        return list;
    }
}
