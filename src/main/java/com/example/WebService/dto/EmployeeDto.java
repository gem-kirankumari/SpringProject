package com.example.WebService.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {

    @NotNull
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Min(value = 15)
    private Long age = Long.MIN_VALUE;
}
