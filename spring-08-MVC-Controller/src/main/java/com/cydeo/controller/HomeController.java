package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    // in controller class  always String
    @RequestMapping("/home")
    public String home(){
        return "home.html";

    }




}
