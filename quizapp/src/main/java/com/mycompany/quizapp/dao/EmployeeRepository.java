package com.mycompany.quizapp.dao;


import com.mycompany.quizapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> getEmployeeByCategory(String category);

    Employee getEmployeeById(int id);

}
