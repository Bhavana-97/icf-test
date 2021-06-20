package com.bhavana;

import com.bhavana.error.EmployeeNotFoundException;
import com.bhavana.error.EmployeeUnSupportedFieldPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.io.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/employees")
    List<Employee> findAll() {
        return repository.findAll();
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    Employee newEmploye(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @GetMapping("/employees/{id}")
    Employee findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    Employee saveOrUpdate(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setFirstName(newEmployee.getFirstName());
                    x.setLastName(newEmployee.getLastName());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/employees/{id}/dependents")
    List<Dependent> findAllDependents(@PathVariable Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return employee.getDependents();
    }
    
    @DeleteMapping("/employees/{id}/dependents/{dependentId}")
    void deleteDependent(@PathVariable Long id,@PathVariable Long dependentId) {
        Employee employee = repository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
        System.out.println(employee.getDependents().size());
        employee.deleteDependent(dependentId);
        System.out.println(employee.getDependents().size());
        repository.deleteById(employee.getId());
        repository.save(employee);
    }

    @GetMapping("/employees/{id}/qualification")
    Qualification findQualification(@PathVariable Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return employee.getQualification();
    }

}
