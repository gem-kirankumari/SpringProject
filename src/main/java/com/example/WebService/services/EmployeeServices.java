package com.example.WebService.services;


import com.example.WebService.model.EmployeeModel;
import com.example.WebService.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//Services will have business logic
@Slf4j
@Service
public class EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean createEmployee(EmployeeModel employeeModel) {
        EmployeeModel saved = employeeRepository.save(employeeModel);
        return (saved == null) ? false : true;
    }

    public List<EmployeeModel> employeeDetails() {
        List<EmployeeModel> employeeModelList = employeeRepository.findAll();
        return employeeModelList;
    }

    public EmployeeModel updateEmployee(EmployeeModel searchEmployee) {
        EmployeeModel searchedEmployee = employeeRepository.findByFirstName(searchEmployee.getFirstName());
        EmployeeModel employeeDataUpdated = (searchedEmployee != null) ? employeeRepository.deleteByFirstName(searchedEmployee.getFirstName()) : null;
        employeeRepository.save(searchEmployee);
        return searchEmployee;
    }

    public boolean deleteEmployee(EmployeeModel searchEmployee) {
        EmployeeModel searchedEmployee = employeeRepository.findByFirstName(searchEmployee.getFirstName());
        EmployeeModel done = (searchedEmployee != null) ? employeeRepository.deleteByFirstName(searchedEmployee.getFirstName()) : null;
        return (done != null) ? true : false;
    }

    public List<EmployeeModel> findByAge(long age) {
        return employeeRepository.findByAgeOrderByAgeAsc(age);
    }

    public List<EmployeeModel> findEmployeeByAgeRange(long lowerAge, long upperAge) {
        return employeeRepository.findByAgeBetweenOrderByAgeAsc(lowerAge, upperAge);
    }

    public EmployeeModel findEmployeeByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }
}
