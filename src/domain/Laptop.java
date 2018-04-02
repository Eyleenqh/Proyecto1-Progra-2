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
public class Laptop extends AudiovisualMaterial {
    private int size;
    private String color;
    private boolean camera;

    public Laptop() {
        super();
        this.size =0;
        this.color ="";
        this.camera = false;
    }
    
    public Laptop(int size, String color, boolean camera, int quantity, int code, String brand, int year) {
        super(quantity, code, brand, year);
        this.size = size;
        this.color = color;
        this.camera = camera;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isCamera() {
        return camera;
    }

    public void setCamera(boolean camera) {
        this.camera = camera;
    }

    @Override
    public String toString() {
        return "Laptop{"+ "size=" + size + ", color=" + color + ", camera=" + camera + '}';
    }
    
    public int sizeInBytes(){
        return super.getBrand().length()*2+this.getColor().length()*2+4+4+4+4+1;
                
    }
    
}
