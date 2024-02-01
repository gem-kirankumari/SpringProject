package com.example.WebService.repository;

import com.example.WebService.model.EmployeeModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface EmployeeRepository extends MongoRepository<EmployeeModel, String> {
    EmployeeModel findByFirstName(String firstName);

    EmployeeModel deleteByFirstName(String firstName);

    List<EmployeeModel> findByAgeOrderByAgeAsc(long age);

    List<EmployeeModel> findByAgeBetweenOrderByAgeAsc(long start, long end);

}
