package com.cgm.crud.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cgm.crud.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEmpForm {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty
    private String department;

    @NotNull
    @Size(max = 100)
    private String address;

    @Min(value = 1)
    @Max(value = 500000)
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
