package com.example.IKMTest.Contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    private UUID id;
    private String empName;
    private String empAddress;
}
