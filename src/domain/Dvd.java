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
public class Dvd extends AudiovisualMaterial {

    private String name;
    private int space;

    public Dvd() {
        super();
        this.name = "";
        this.space = 0;
    }

    public Dvd(String name, int space, int quantity, int code, String brand, int year) {
        super(quantity, code, brand, year);
        this.name = name;
        this.space = space;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    @Override
    public String toString() {
        return "Dvd{" + "name=" + name + ", space=" + space + '}';
    }

    public int sizeInBytes() {
        return super.getBrand().length() * 2 + this.getName().length() * 2 + 4 + 4 + 4 + 4;

    }
}
