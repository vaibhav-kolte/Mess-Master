package org.mycode.messmaster.entities;

import java.util.Date;

public class Student {

    private Integer studentId;
    private String name;
    private String email;
    private String phoneNumber;
    private Date enrollmentDate;
    private String address;
    private String className;
    private Department department;

    public Student() {
    }

    public Student(Integer studentId, String name, String email, String phoneNumber, Date enrollmentDate, String address, String className, Department department) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.enrollmentDate = enrollmentDate;
        this.address = address;
        this.className = className;
        this.department = department;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                ", address='" + address + '\'' +
                ", className='" + className + '\'' +
                ", department=" + department +
                '}';
    }
}
