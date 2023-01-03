package com.cgm.crud.persistence.dao;

import java.util.List;

import com.cgm.crud.persistence.entity.Employee;

public interface EmployeeDao {
    // add employee
    public void addEmployee(Employee emp);

    // get all employee
    public List<Employee> getAllEmp();

    // get employee by id
    public Employee getEmpById(int id);

    // update employee
    public void updateEmp(Employee emp);

    // delete employee
    public void deleteEmp(int id);
    
    // get employee by email
    public Employee findByEmail(String email);
}
