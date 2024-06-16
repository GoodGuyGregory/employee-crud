package com.goodguygregory.employee_crud.dao;

import com.goodguygregory.employee_crud.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    //define entity manager
    private EntityManager entityManager;

    // add the bean
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // service call to return all of the employees
    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> employeeQuery = entityManager.createQuery("SELECT e FROM Employee e", Employee.class);

        //execute the  query to get employees
        List<Employee> employees = employeeQuery.getResultList();

        // return the list found
        return employees;
    }

    @Override
    public Employee findById(Integer id) {
        TypedQuery<Employee> empQuery = entityManager.createQuery("select e from Employee e where e.id = :id", Employee.class)
                .setParameter("id", id);

        Employee employee = empQuery.getSingleResult();

        return employee;
    }

    @Override
    public Employee create(Employee employee) {

        entityManager.persist(employee);
        return employee;
    }

    // using merge allows for both updates and potentially new employees to be saved to the db
    @Override
    public Employee save(Employee employee) {

        entityManager.merge(employee);
        return employee;
    }

    @Override
    public void deleteById(Integer employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        entityManager.remove(employee);
    }
}
