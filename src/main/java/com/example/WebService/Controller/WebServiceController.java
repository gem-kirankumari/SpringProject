package com.example.WebService.Controller;

import com.example.WebService.Services.EmployeeServices;
import com.example.WebService.dto.EmployeeDto;
import com.example.WebService.model.EmployeeModel;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebServiceController {

    @Autowired
    private EmployeeServices employeeServices;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/employee")
    private ResponseEntity<?> insertEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeModel employeeModel = modelMapper.map(employeeDto, EmployeeModel.class);
        boolean createFlag= employeeServices.createEmployee(employeeModel);
        if (createFlag) {
            return new ResponseEntity<>("Operation Successful", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Operation Unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/employee")
    public ResponseEntity<?> getEmployee() {
        List<EmployeeModel> employeeLists = employeeServices.employeeDetails();
        if (employeeLists == null)
        {         return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeLists, HttpStatus.OK);
    }

    @PutMapping("/employee")
    private ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeModel searchEmployee = modelMapper.map(employeeDto, EmployeeModel.class);
        EmployeeModel updatedEmployeeDetails = employeeServices.updateEmployee(searchEmployee);
        if (updatedEmployeeDetails == null) {
            return new ResponseEntity<>("Operation Unsuccessful", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedEmployeeDetails, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/employee")
    private ResponseEntity<?> deleteEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeModel searchEmployee = modelMapper.map(employeeDto, EmployeeModel.class);
        boolean deleted = employeeServices.deleteEmployee(searchEmployee);
        if (deleted) {
            return new ResponseEntity<>("Operation Successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Operation Unsuccessful", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/employee/age")
    private ResponseEntity<?> findByAge(@RequestParam(required = false) Long age, @RequestParam(required = false, defaultValue = "10") Long lowerAge, @RequestParam(required = false) Long upperAge) {
        List<EmployeeModel> employee = null;
        if((age != null && upperAge != null) || (age == null && upperAge == null)) {
            return new ResponseEntity<>("Operation Unsuccessful", HttpStatus.BAD_REQUEST);
        }
        if (age != null) {
            employee = employeeServices.findByAge(age);
        } else if (upperAge != null) {
            employee = employeeServices.findEmployeeByAgeRange(lowerAge, upperAge);
        }
        if (employee != null && employee.size() != 0) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>("Operation Unsuccessful", HttpStatus.NOT_FOUND);
    }



    @GetMapping(value = "/employee/{firstName}")
    private ResponseEntity<?> findEmployeeByFirstName(@PathVariable String firstName) {
        EmployeeModel employee = employeeServices.findEmployeeByFirstName(firstName);
        if (employee != null)
        { return new ResponseEntity<>(employee, HttpStatus.OK); }
        return new ResponseEntity<>("Operation Unsuccessful", HttpStatus.NOT_FOUND);
    }
}
