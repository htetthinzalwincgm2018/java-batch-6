package com.cgm.crud.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cgm.crud.dto.EmployeeDto;
import com.cgm.crud.entity.Employee;
import com.cgm.crud.form.CreateEmpForm;
import com.cgm.crud.service.EmployeeServices;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @RequestMapping(value = { "/addEmployee" }, method = RequestMethod.GET)
    public String showRegister() {
        return "register";
    }

    @RequestMapping(value = { "/insertEmployee" }, method = RequestMethod.POST)
    public String insertEmployee(@ModelAttribute("insertEmployee") @Valid CreateEmpForm emp, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("----error---");
            return "register";
        }
        employeeServices.addEmp(emp);
        return "redirect:/addEmployee";
    }

    // lode employee data
    @GetMapping("employeeReport")
    public ModelAndView lodeEmployee() {
        ModelAndView report = new ModelAndView("employeeReport");
        List<EmployeeDto> emp = employeeServices.getAllEmp();
        report.addObject("employee", emp);
        report.addObject("title", "Employee Report");

        return report;
    }

    // lode edit form
    @GetMapping("/editEmployee/{id}")
    public String lodeEditForm(@PathVariable(value = "id") int id, Model m) {
        Employee emp = employeeServices.getById(id);

        System.out.println(emp);

        m.addAttribute("employee", emp);
        m.addAttribute("title", "Edit Employee");

        return "editEmployee";
    }

    @PostMapping("/editEmployee/updateEmployee")
    public String updateEmp(@ModelAttribute("updateEmployee") Employee emp) {
        employeeServices.updateEmp(emp);

        return "redirect:/employeeReport";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeServices.deleteEmployee(id);

        return "redirect:/employeeReport";
    }
}
