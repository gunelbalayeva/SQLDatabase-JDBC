package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String jobId;
    private BigDecimal salary;
    private BigDecimal commissionPercent;
    private LocalDate hireDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getCommissionPercent() {
        return commissionPercent;
    }

    public void setCommissionPercent(BigDecimal commissionPercent) {
        this.commissionPercent = commissionPercent;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee: " +
                "id: " + id +
                " firstname: " + firstname + '\'' +
                " lastname: " + lastname + '\'' +
                " email: " + email + '\'' +
                " phone: " + phone + '\'' +
                " jobId: " + jobId + '\'' +
                " salary: " + salary +
                " commissionPercent: " + commissionPercent +
                " hireDate: " + hireDate ;
    }
}
