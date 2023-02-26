package com.student;

import java.util.Date;
import java.util.Objects;

public class StudentAccount {
    private String firstName;
    private String lastName;
    private int studentID;
    private Date dateOfBirth;
    private String data;
    private static int count;

    public StudentAccount(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = count;
        count++;
        this.dateOfBirth = dateOfBirth;
        data = "Lorem Ipsum";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getData() {
        return data;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StudentAccount{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentID=" + studentID +
                ", dateOfBirth=" + dateOfBirth +
                ", data='" + data + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAccount that = (StudentAccount) o;
        return studentID == that.studentID &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                dateOfBirth == that.dateOfBirth &&
                data.equals(that.data);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31*hash + firstName.hashCode();
        hash = 31*hash + lastName.hashCode();
        hash = 31*hash + dateOfBirth.hashCode();
        return hash;
    }
}
