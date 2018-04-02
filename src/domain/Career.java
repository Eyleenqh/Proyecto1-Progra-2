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
public class Career {
    private int quantity;
    private String coodinator;
    private int foundationYear;

    public Career() {
         this.quantity = 0;
        this.coodinator = "";
        this.foundationYear =0;
    }

    public Career(int quantity, String coodinator, int foundationYear) {
        this.quantity = quantity;
        this.coodinator = coodinator;
        this.foundationYear = foundationYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCoodinator() {
        return coodinator;
    }

    public void setCoodinator(String coodinator) {
        this.coodinator = coodinator;
    }

    public int getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(int foundationYear) {
        this.foundationYear = foundationYear;
    }

    @Override
    public String toString() {
        return "Career{" + "quantity=" + quantity + ", coodinator=" + coodinator + ", foundationYear=" + foundationYear + '}';
    }
    
    
}
