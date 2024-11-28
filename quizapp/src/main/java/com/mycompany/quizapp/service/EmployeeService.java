package com.mycompany.quizapp.service;

import com.mycompany.quizapp.dao.EmployeeRepository;
import com.mycompany.quizapp.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<String> addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Employee>> getEmployeeByCategory(String category) {

        return new ResponseEntity<>(employeeRepository.getEmployeeByCategory(category),HttpStatus.OK);

    }

    public ResponseEntity<List<Employee>> getAllEmployeeDetails() {
//        System.out.println("Inside the employeeservice");
//        String res = employeeDao.toString();
//        System.out.println(res);
//        employeeDao.
//        List<Employee> employeeList = employeeRepository.findAll();
//        log.info("all : {}", employeeList);
        try
        {
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>("deletion success",HttpStatus.OK);
    }

    public ResponseEntity<String> updateEmployeeById(int id,Employee update) {
        Employee res= employeeRepository.getEmployeeById(id);
        res.setFirstName(update.getFirstName());
        employeeRepository.save(res);
        return  new ResponseEntity<>("update success" ,HttpStatus.OK);


    }
}
