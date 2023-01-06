package com.cgm.crud.service;

import java.util.List;

import com.cgm.crud.dto.EmployeeDto;
import com.cgm.crud.entity.Employee;
import com.cgm.crud.form.CreateEmpForm;

public interface EmployeeServices {
    // add employee
    public void addEmp(CreateEmpForm empFrom);

    // get all employee
    public List<EmployeeDto> getAllEmp();

    // get emp by id
    public Employee getById(int id);

    // update employee
    public void updateEmp(Employee emp);

    // delete employee
    public void deleteEmployee(int id);
}