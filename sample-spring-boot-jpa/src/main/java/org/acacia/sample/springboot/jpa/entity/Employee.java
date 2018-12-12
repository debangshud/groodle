package org.acacia.sample.springboot.jpa.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEES")
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;

//    private String jobId;

    @OneToOne
    @JoinColumn(name = "JOB_ID")
    private Job job;

    private BigDecimal salary;

    @Column(name = "COMMISSION_PCT")
    private BigDecimal commisionPercentage;
//    private Integer managerId;

//    @OneToOne(fetch = FetchType.LAZY)
    @OneToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;

//    private Integer departmentId;

    @OneToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

//    public String getJobId() {
//        return jobId;
//    }
//
//    public void setJobId(String jobId) {
//        this.jobId = jobId;
//    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getCommisionPercentage() {
        return commisionPercentage;
    }

    public void setCommisionPercentage(BigDecimal commisionPercentage) {
        this.commisionPercentage = commisionPercentage;
    }

//    public Integer getManagerId() {
//        return managerId;
//    }
//
//    public void setManagerId(Integer managerId) {
//        this.managerId = managerId;
//    }

//    public Integer getDepartmentId() {
//        return departmentId;
//    }
//
//    public void setDepartmentId(Integer departmentId) {
//        this.departmentId = departmentId;
//    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
