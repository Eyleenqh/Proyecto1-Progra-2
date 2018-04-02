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
public class Speaker extends AudiovisualMaterial{
    private int quantitySpeaker;
    private String size;

    public Speaker() {
        super();
        this.quantitySpeaker=0;
        this.size="";
    }

    public Speaker(int quantitySpeaker, String size, int quantity, int code, String brand, int year) {
        super(quantity, code, brand, year);
        this.quantitySpeaker = quantitySpeaker;
        this.size = size;
    }

    public int getQuantitySpeaker() {
        return quantitySpeaker;
    }

    public void setQuantitySpeaker(int quantitySpeaker) {
        this.quantitySpeaker = quantitySpeaker;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Speaker{" + "quantitySpeaker=" + quantitySpeaker + ", size=" + size + '}';
    }
    
    public int sizeInBytes(){
        return super.getBrand().length()*2+4+4+4+4+4;
                
    }
    
}
