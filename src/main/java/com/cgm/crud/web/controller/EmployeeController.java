package com.cgm.crud.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cgm.crud.bl.dto.EmployeeDto;
import com.cgm.crud.persistence.entity.Employee;
import com.cgm.crud.web.form.CreateEmpForm;
import com.cgm.crud.bl.service.EmployeeServices;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @RequestMapping(value = { "/addEmployee" }, method = RequestMethod.GET)
    public String showRegister(Model model) {
        CreateEmpForm createEmpForm = new CreateEmpForm();
        model.addAttribute("createEmpForm", createEmpForm);
        return "register";
    }

    @RequestMapping(value = { "/insertEmployee" }, method = RequestMethod.POST)
    public ModelAndView insertEmployee(@Valid @ModelAttribute("createEmpForm") CreateEmpForm emp,
            BindingResult result) {

        ModelAndView mv = new ModelAndView("register");
        if (result.hasErrors()) {
            System.out.println("----validation error---");
            return mv;
        }
        System.out.println("----no validation error---");
        // save data to database
        employeeServices.addEmp(emp);
        ModelAndView mvr = new ModelAndView("redirect:/employeeReport");
        return mvr;
    }

    // lode employee data
    @GetMapping("employeeReport")
    public ModelAndView lodeEmployee() {
        ModelAndView report = new ModelAndView("employeeReport");

        // get data from database
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

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ModelAndView exportEmployee(HttpServletResponse response) throws IOException {
        employeeServices.doDownloadAllEmp(response);
        return null;
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String uploadExcel(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
            throws IOException {

        String statusMessage = employeeServices.doImportEmp(file);

        redirectAttributes.addFlashAttribute("message", statusMessage);

        return "redirect:/employeeReport";
    }
}