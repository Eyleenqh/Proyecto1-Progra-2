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
public class AudiovisualMaterial extends Material{

    private int quantity;
    private int code;
    private String brand;
    private int year;

    public AudiovisualMaterial() {
        this.quantity = 0;
        this.code = 0;
        this.brand = "";
        this.year = 0;
    }

    public AudiovisualMaterial(int quantity, int code, String brand, int year) {
        this.quantity = quantity;
        this.code = code;
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Quantity=" + quantity + ", Code=" + code + ", Brand=" + brand + super.toString();
    }

}
