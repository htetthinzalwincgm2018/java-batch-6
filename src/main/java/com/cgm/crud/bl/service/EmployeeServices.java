package com.cgm.crud.bl.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.cgm.crud.bl.dto.EmployeeDto;
import com.cgm.crud.persistence.entity.Employee;
import com.cgm.crud.web.form.CreateEmpForm;

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

    // get employee by email
    public EmployeeDto findByEmail(String email);

    public void doDownloadAllEmp(HttpServletResponse response) throws IOException;

    public String doImportEmp(MultipartFile file) throws IOException;

}