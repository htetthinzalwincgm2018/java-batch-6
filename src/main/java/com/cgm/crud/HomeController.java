package com.cgm.crud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String viewHome() {
        return "home";
    }

    @RequestMapping("/login")
    public String viewLogin() {
        return "login";
    }

    @RequestMapping("/passwordReset")
    public String viewPasswordReset() {
        return "passwordReset";
    }

    @RequestMapping(value = "/error")
    public String error() {
        return "denied";
    }
}
