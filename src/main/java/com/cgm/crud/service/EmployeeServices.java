package com.cgm.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cgm.crud.dao.EmployeeDao;
import com.cgm.crud.dto.EmployeeDto;
import com.cgm.crud.form.CreateEmpForm;
import com.cgm.crud.entity.Employee;

@Component
@Service
public class EmployeeServices {

    @Autowired
    EmployeeDao employeeDao;

    // add employee
    public void addEmp(CreateEmpForm empFrom) {
        Employee emp = new Employee();
        emp.setName(empFrom.getName());
        emp.setDepartment(empFrom.getDepartment());
        emp.setAddress(empFrom.getAddress());
        emp.setSalary(empFrom.getSalary());
        employeeDao.addEmployee(emp);
    }

    // get all employee
    public List<EmployeeDto> getAllEmp() {
        List<Employee> allEmp = employeeDao.getAllEmp();
        
        List<EmployeeDto> employee = new ArrayList<EmployeeDto>();
        for (Employee emp : allEmp) {
            EmployeeDto empDto = new EmployeeDto(emp);
            empDto.setId(emp.getId());
            empDto.setName(emp.getName());
            empDto.setDepartment(emp.getDepartment());
            empDto.setAddress(emp.getAddress());
            empDto.setSalary(emp.getSalary());
            //if condition
            employee.add(empDto);
        }

        return employee;
    }

    // get emp by id
    public Employee getById(int id) {
        return employeeDao.getEmpById(id);
    }

    // update employee
    public void updateEmp(Employee emp) {
        employeeDao.updateEmp(emp);
    }

    // delete employee
    public void deleteEmployee(int id) {
        employeeDao.deleteEmp(id);
    }
}
