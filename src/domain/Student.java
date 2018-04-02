/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 * @author Steven
 * @author Eyleen
 */
public class Student {

    private String name;
    private String surname;
    private String universityId;
    private String career;
    private int year;
    private int id;
    private int phoneNumber;

    public Student() {
        this.name = "";
        this.surname = "";
        this.universityId = "";
        this.career = "";
        this.year = 0;
        this.id = 0;
        this.phoneNumber = 0;
    }

    public Student(String name, String surname, String universityId, String career, int year, int id, int phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.universityId = universityId;
        this.career = career;
        this.year = year;
        this.id = id;
        this.phoneNumber = phoneNumber;
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

    public String getUniversitiId() {
        return universityId;
    }

    public void setUniversitiId(String universitiId) {
        this.universityId = universitiId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", surname=" + surname + ", universityId=" + universityId + ", career=" + career + ", year=" + year + ", id=" + id + ", phoneNumber=" + phoneNumber + '}';
    }

    public int sizeInBytes() {
        return this.getName().length() * 2 + this.getSurname().length() * 2 + this.getUniversitiId().length() * 2 + this.getCareer().length() * 2 + 4 + 4 + 4;

    }

}
