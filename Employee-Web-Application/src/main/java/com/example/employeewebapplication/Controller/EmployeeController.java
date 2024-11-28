package com.example.employeewebapplication.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class EmployeeController {


    @GetMapping("/dummy")
    public String dummyMethod()
    {
        System.out.println("Hi Hello All");
        return "Hi Hello All Ji";
    }




}
