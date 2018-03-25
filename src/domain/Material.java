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
public class Material {
    private String code;
    private int available;

    public Material() {
    }

    public Material(String code, int available) {
        this.code = code;
        this.available = available;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Material{" + "code=" + code + ", available=" + available + '}';
    }
    
}
