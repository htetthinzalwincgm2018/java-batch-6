package com.cgm.crud.form;

import javax.validation.constraints.NotEmpty;

import com.cgm.crud.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEmpForm {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    private String department;

    private String address;

    private int salary;

    public CreateEmpForm() {
        super();
    }

    public CreateEmpForm(Employee emp) {
        this.name = emp.getName();
        this.department = emp.getDepartment();
        this.address = emp.getAddress();
        this.salary = emp.getSalary();
    }
}
