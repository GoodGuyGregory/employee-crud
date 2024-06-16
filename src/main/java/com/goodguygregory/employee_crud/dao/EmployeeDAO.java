package com.goodguygregory.employee_crud.dao;

import com.goodguygregory.employee_crud.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> findAll();

    public Employee findById(Integer id);

    public Employee save(Employee employee);

    public Employee create(Employee employee);

    public void deleteById(Integer id);
}
