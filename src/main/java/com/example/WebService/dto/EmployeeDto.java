package com.example.WebService.dto;


import lombok.*;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {

    private String firstName;
    private String lastName;
    private long age;
}
