package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    // in controller class  always String// because it works with html.
    @RequestMapping("/home")
    public String home(){
        return "home.html";

    }
    @RequestMapping("/ozzy")
    public String getHomePage(){
        return "home.html";

    }
    @RequestMapping("/")
    public String getHomePage2(){
        return "home.html";

    }
//    @RequestMapping("/student")
//    public String getStudentInfo(){
//        return "student/welcome.html";
//
//
//    }




}
