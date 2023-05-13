package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//class// model is interface to cary data
public class StudentController {

@RequestMapping("/welcome")
    public String homePage(Model model){// interface want to execute methode belongs to model interface
    model.addAttribute("name", "Cydeo");// Caring the data to view
    model.addAttribute("course", "MVC");
    return "student/welcome";
    }


}
