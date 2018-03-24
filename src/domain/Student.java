/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Eyleen
 */
public class Student {
    private String name;
    private String surname;
    private String universityId;
    private String career;
    private int year;

    public Student() {
    }

    public Student(String name, String surname, String license, String career, int year) {
        this.name = name;
        this.surname = surname;
        this.universityId = license;
        this.career = career;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLicense() {
        return universityId;
    }

    public void setLicense(String license) {
        this.universityId = license;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", surname=" + surname + ", universityId=" +universityId + ", career=" + career + ", year=" + year + '}';
    }
    
    
    
}
