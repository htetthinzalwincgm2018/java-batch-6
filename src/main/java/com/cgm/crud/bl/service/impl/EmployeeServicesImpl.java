package com.cgm.crud.bl.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cgm.crud.persistence.dao.EmployeeDao;
import com.cgm.crud.bl.dto.EmployeeDto;
import com.cgm.crud.persistence.entity.Employee;
import com.cgm.crud.web.form.CreateEmpForm;
import com.cgm.crud.bl.service.EmployeeServices;

@Transactional
@Service
public class EmployeeServicesImpl implements EmployeeServices {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    // add employee
    @Override
    public void addEmp(CreateEmpForm empFrom) {
        Employee emp = new Employee();
        emp.setName(empFrom.getName());
        emp.setEmail(empFrom.getEmail());
        emp.setPassword(passwordEncoder.encode(empFrom.getPassword()));
        emp.setType(empFrom.getType());
        emp.setDepartment(empFrom.getDepartment());
        emp.setAddress(empFrom.getAddress());
        emp.setSalary(empFrom.getSalary());
        employeeDao.addEmployee(emp);
    }

    // get all employee
    @Override
    public List<EmployeeDto> getAllEmp() {
        List<Employee> allEmp = employeeDao.getAllEmp();

        List<EmployeeDto> employee = new ArrayList<EmployeeDto>();
        for (Employee emp : allEmp) {
            EmployeeDto empDto = new EmployeeDto(emp);
            empDto.setId(emp.getId());
            empDto.setName(emp.getName());
            empDto.setEmail(emp.getEmail());
            empDto.setDepartment(emp.getDepartment());
            empDto.setAddress(emp.getAddress());
            empDto.setSalary(emp.getSalary());
            // if condition
            employee.add(empDto);
        }

        return employee;
    }

    // get emp by id
    @Override
    public Employee getById(int id) {
        return employeeDao.getEmpById(id);
    }

    // update employee
    @Override
    public void updateEmp(Employee emp) {
        employeeDao.updateEmp(emp);
    }

    // delete employee
    @Override
    public void deleteEmployee(int id) {
        employeeDao.deleteEmp(id);
    }

    // get employee by email
    @Override
    public EmployeeDto findByEmail(String email) {
        Employee emp = employeeDao.findByEmail(email);

        if (emp == null) {
            return null;
        }
        EmployeeDto employeeDto = new EmployeeDto(emp);
        return employeeDto;
    }

    @Override
    public void doDownloadAllEmp(HttpServletResponse response) throws IOException {
        List<Employee> listEmp = employeeDao.getAllEmp();
        String fileName = "employee_list.xlsx";

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("User List");
        XSSFRow rowHead = sheet.createRow((short) 0);
        rowHead.createCell(0).setCellValue("ID");
        rowHead.createCell(1).setCellValue("Name");
        rowHead.createCell(2).setCellValue("Email");
        rowHead.createCell(3).setCellValue("Department");
        rowHead.createCell(4).setCellValue("Address");
        rowHead.createCell(5).setCellValue("Salary");
        rowHead.createCell(6).setCellValue("Type");

        int count = 1;
        for (Employee emp : listEmp) {
            XSSFRow row = sheet.createRow((short) count);
            row.createCell(0).setCellValue(count);
            row.createCell(1).setCellValue(emp.getName());
            row.createCell(2).setCellValue(emp.getEmail());
            row.createCell(3).setCellValue(emp.getDepartment());
            row.createCell(4).setCellValue(emp.getAddress());
            row.createCell(5).setCellValue(emp.getSalary());
            String type = emp.getType() == "0" ? "User" : "Admin";
            row.createCell(6).setCellValue(type);
            count++;
        }
        count = 0;

        try {
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", fileName);
            response.setHeader(headerKey, headerValue);
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workbook.close();
            IOUtils.closeQuietly(response.getOutputStream());
        }
    }

    @SuppressWarnings("resource")
    @Override
    @Transactional
    public String doImportEmp(MultipartFile file) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
            Employee emp = new Employee();
            Row row = sheet.getRow(i);

            Cell cellName = row.getCell(0);
            emp.setName(cellName.getStringCellValue());

            Cell cellEmail = row.getCell(1);
            emp.setEmail(cellEmail.getStringCellValue());

            emp.setPassword(passwordEncoder.encode("111111"));

            Cell cellDept = row.getCell(2);
            emp.setDepartment(cellDept.getStringCellValue());

            Cell cellAddress = row.getCell(3);
            emp.setAddress(cellAddress.getStringCellValue());

            Cell cellSalary = row.getCell(4);
            emp.setSalary((int) cellSalary.getNumericCellValue());

            Cell cellType = row.getCell(5);
            String type = (cellType.getStringCellValue().equals("User")) ? "0" : "1";
            emp.setType(type);

            employeeDao.addEmployee(emp);
        }

        return messageSource.getMessage("M_SC_USR_0008", null, null);
    }
}
