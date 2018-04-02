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
public class PenaltyFee {
    private int quantity;
    private String description;

    public PenaltyFee() {
        this.quantity =0;
        this.description ="";
    }

    public PenaltyFee(int quantity, String description) {
        this.quantity = quantity;
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PenaltyFee{" + "quantity=" + quantity + ", description=" + description + '}';
    }
    
    
}
