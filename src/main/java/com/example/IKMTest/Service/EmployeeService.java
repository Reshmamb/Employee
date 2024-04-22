package com.example.IKMTest.Service;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

import com.example.IKMTest.Contract.EmployeeRequest;
import com.example.IKMTest.Contract.EmployeeResponse;
import com.example.IKMTest.Model.Employee;
import com.example.IKMTest.Repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public Employee saveEmployee(EmployeeRequest request, boolean isUpdate) {

        Employee employee;

        if (isUpdate) {

            employee =
                    employeeRepository
                            .findById(request.getId())
                            .orElseThrow(
                                    () ->
                                            new IllegalArgumentException(
                                                    "Employee Id not Exist" + request.getId()));

        } else {
            employee = new Employee();
        }
        employee = modelMapper.map(request, Employee.class);
        employeeRepository.save(employee);

        return employeeRepository.save(employee);
    }

    public EmployeeResponse getByName(String name) {
        modelMapper
                .getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(PRIVATE)
                .setPropertyCondition(Conditions.isNotNull());
        return mapToResponse(employeeRepository.findByEmpName(name));
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        EmployeeResponse response = modelMapper.map(employee, EmployeeResponse.class);

        return response;
    }
}
