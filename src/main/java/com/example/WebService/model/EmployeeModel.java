package com.example.WebService.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("Employee")
public class EmployeeModel {

    private String firstName;
    private String lastName;
    private Long age;
}
