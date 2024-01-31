package com.example.WebService.Services;


import com.example.WebService.model.EmployeeModel;
import com.example.WebService.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;


//Services will have business logic
@Slf4j
@Service
public class EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean createEmployee(EmployeeModel employeeModel) {
        EmployeeModel saved = employeeRepository.save(employeeModel);
        if (saved == null) {return false;}
        return true;
    }

    public List<EmployeeModel> employeeDetails() {
        List<EmployeeModel> employeeModelList = employeeRepository.findAll();
        return employeeModelList;
    }

    public EmployeeModel updateEmployee(EmployeeModel searchEmployee) {
        log.info("Here");
        log.info(searchEmployee.toString());
        log.info("Here2");
        EmployeeModel searchedEmployee = employeeRepository.findByFirstName(searchEmployee.getFirstName());

        if (searchedEmployee != null) {
            log.info("not null");
            EmployeeModel done = employeeRepository.deleteByFirstName(searchedEmployee.getFirstName());
            log.info("Done " + done);
            employeeRepository.save(searchEmployee);
            return searchedEmployee;
        }
        return null;
    }

    public boolean deleteEmployee(EmployeeModel searchEmployee) {
        EmployeeModel searchedEmployee = employeeRepository.findByFirstName(searchEmployee.getFirstName());
        if (searchedEmployee != null) {
            EmployeeModel done = employeeRepository.deleteByFirstName(searchedEmployee.getFirstName());
            if (done != null) {
                return true;
            }
            return false;
        }
        return false;
    }
}
