package com.cgm.crud.web.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cgm.crud.persistence.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEmpForm {

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    
    @NotEmpty(message = "Name cannot be empty")
    private String email;
    
    @NotEmpty
    @Size(min = 6, max = 12)
    private String password;
    
    @NotEmpty
    private String type;

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
        this.email = emp.getEmail();
        this.password = emp.getPassword();
        this.type = emp.getType();
        this.department = emp.getDepartment();
        this.address = emp.getAddress();
        this.salary = emp.getSalary();
    }
}
