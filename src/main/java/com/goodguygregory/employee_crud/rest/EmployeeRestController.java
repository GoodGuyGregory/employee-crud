package com.goodguygregory.employee_crud.rest;


import com.goodguygregory.employee_crud.dao.EmployeeDAO;
import com.goodguygregory.employee_crud.entity.Employee;
import com.goodguygregory.employee_crud.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    // inject the employee service within the controller
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // expose employee GET endpoint
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return this.employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable Integer employeeId) {
        Employee foundEmployee = this.employeeService.findById(employeeId);
        if (foundEmployee == null) {
            throw new RuntimeException("Employee id not found: " + employeeId);
        }

        return foundEmployee;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        // makes a call to the service
        Employee createdEmployee = this.employeeService.create(employee);

        return createdEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = this.employeeService.save(employee);

        return updatedEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId) {
        Employee employee = this.employeeService.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee id not found: " + employeeId);
        }

        employeeService.deleteById(employee.getId());
        return "Deleted Employee: " + employee.getId();
    }


}
