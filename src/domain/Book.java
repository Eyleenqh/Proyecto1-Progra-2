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
public class Book extends Material{
    private String name;
    private String author;
    private String area;
    private boolean typeDigital;
    

    public Book() {
    }

    public Book(String name, String author, String area, String code, boolean typeDigital, int available) {
        super(code, available);
        this.name = name;
        this.author = author;
        this.area = area;
        this.typeDigital = typeDigital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isTypeDigital() {
        return typeDigital;
    }

    public void setTypeDigital(boolean typeDigital) {
        this.typeDigital = typeDigital;
    }

    @Override
    public String toString() {
        return super.toString()+ " Book{" + "name=" + name + ", author=" + author + ", area=" + area + ", typeDigital=" + typeDigital + '}';
    }
    
}
