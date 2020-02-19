package org.groodle.boot.service.employee.repository;

import org.groodle.boot.service.employee.model.Employee;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepository {

    static Map<String, Employee> employeeData;

    static
    {
        employeeData = new HashMap<>();
        employeeData.put("1",Employee.builder().id("1").firstName("Debangshu").lastName("Dasgupta").build());
        employeeData.put("2",Employee.builder().id("2").firstName("Soumyadip").lastName("Dhar").build());
        employeeData.put("3",Employee.builder().id("3").firstName("Srinivas").lastName("Chidella").build());
        employeeData.put("4",Employee.builder().id("4").firstName("Siva").lastName("Eranki").build());
        employeeData.put("5",Employee.builder().id("5").firstName("Vivek").lastName("Rajput").build());
        employeeData.put("6",Employee.builder().id("6").firstName("Abhijit").lastName("Turang").build());
        employeeData.put("7",Employee.builder().id("7").firstName("Amit").lastName("Mohanty").build());
        employeeData.put("8",Employee.builder().id("8").firstName("Raj").lastName("Saka").build());
        employeeData.put("9",Employee.builder().id("9").firstName("Laxman").lastName("Munigala").build());
        employeeData.put("10",Employee.builder().id("10").firstName("Balaji").lastName("Mariyappan").build());

//        employeeAccessData=new HashMap<>();
//        employeeAccessData.put("1", "Employee 1 Access Key");
//        employeeAccessData.put("2", "Employee 2 Access Key");
//        employeeAccessData.put("3", "Employee 3 Access Key");
//        employeeAccessData.put("4", "Employee 4 Access Key");
//        employeeAccessData.put("5", "Employee 5 Access Key");
//        employeeAccessData.put("6", "Employee 6 Access Key");
//        employeeAccessData.put("7", "Employee 7 Access Key");
//        employeeAccessData.put("8", "Employee 8 Access Key");
//        employeeAccessData.put("9", "Employee 9 Access Key");
//        employeeAccessData.put("10", "Employee 10 Access Key");
    }

    public Mono<Employee> findEmployeeById(String id)
    {
        return Mono.just(employeeData.get(id));
    }

    public Flux<Employee> findAllEmployees() {
        return Flux.fromIterable(employeeData.values());
    }
}
