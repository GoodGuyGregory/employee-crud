package com.goodguygregory.employee_crud.service;

import com.goodguygregory.employee_crud.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {
    public List<Employee> findAll();

    public Employee findById(Integer id);

    public Employee create(Employee employee);

    public Employee save(Employee employee);

    public void deleteById(Integer id);
}
