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
public class Digital extends Book{
    private String format;

    public Digital() {
         super();
         this.format ="";
    }

    public Digital(String format,String name, String author, String code, int year, int numberPages) {
        super(name, author, code, year, numberPages);
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "Digital{" + "format=" + format + '}';
    }
    
    public int sizeInBytes() {
        return this.getFormat().length() * 2+super.getName().length() * 2 + super.getAuthor().length() * 2+ super.getCode().length() * 2 + 4 + 4;

    }
    
}
