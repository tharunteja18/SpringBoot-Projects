package com.mycompany.quizapp.controller;


import com.mycompany.quizapp.model.Employee;
import com.mycompany.quizapp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLEngineResult;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("employee")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService)
    {
        this.employeeService=employeeService;
    }

    @GetMapping("allEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
        return employeeService.getAllEmployeeDetails();
    }

    @GetMapping("Category/{category}")
    public ResponseEntity<List<Employee>> getEmployeeByCategory(@PathVariable("category") String category)
    {
        return employeeService.getEmployeeByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee)
    {
        log.info("Employee {}", employee);
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") int id)
    {
        return employeeService.deleteEmployeeById(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateEmployeeById(@PathVariable("id") int id, @RequestBody Employee update)
    {
        return employeeService.updateEmployeeById(id,update);
    }

}
