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
public class Book{

    private String name;
    private String author;
    private String code;
    private int numberPages;private int year;

    public Book() {  
        this.name = "";
        this.author = "";
        this.code = "";
        this.numberPages = 0;
        this.year = 0;
    }

    public Book(String name, String author, String code, int numberPages, int year) {
        this.name = name;
        this.author = author;
        this.code = code;
        this.numberPages = numberPages;
        this.year = year;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    

    @Override
    public String toString() {
        return name + ", Author=" + author+", Year= "+ this.year;
    }
    
    
    //Metodo que verifica las letras para la busqueda filtrada
    public boolean startsWith(String chars){
        if(chars.isEmpty() || chars.length()>this.name.length()){
            return false;
        }
        
        for(int i=0; i< chars.length(); i++){
            if(chars.charAt(i) != this.name.charAt(i)){
                return false;
            }
        }
        return true;
    }

}
