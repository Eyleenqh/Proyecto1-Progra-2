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
public class Projector extends AudiovisualMaterial {
    private String screenSize;

    public Projector() {
        super();
        this.screenSize ="";
    }

    public Projector(String screenSize, int quantity, int code, String brand, int year) {
        super(quantity, code, brand, year);
        this.screenSize = screenSize;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return "Projector{" + "screenSize=" + screenSize + '}';
    }
    
    public int sizeInBytes(){
        return super.getBrand().length()*2+this.getScreenSize().length()*2+4+4+4;
                
    }
}
