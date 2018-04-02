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
public class Physical extends Book {
    private int quantity;

    public Physical() {
        super();
        this.quantity=0;
    }

    public Physical(int quantity, String name, String author, String code, int year, int numberPages) {
        super(name, author, code, year, numberPages);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return super.toString() + " Cantidad=" + quantity;
    }
    public int sizeInBytes() {
        return super.getName().length() * 2 + super.getAuthor().length() * 2+ super.getCode().length() * 2 + 4 + 4 +4;

    }
    
}
