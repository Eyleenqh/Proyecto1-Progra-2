/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import domain.Laptop;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author Steven
 * @author Eyleen
 */
public class LaptopFile {

    //atributos
    public RandomAccessFile randomAccessFile;//instacia./
    private int regsQuantity;//cantidad de registros en el archivo
    private int regSize;//tamanio del registro

    //constructor
    public LaptopFile(File file) throws IOException {
        //indico el tamanio maximo en bytes que va a tener el registo
        this.regSize = 140; //cambiar

        //una validacion sencilla 
        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + " is an invalid file");
        } else {
            //crea la nueva instancia de RAF
            randomAccessFile = new RandomAccessFile(file, "rw");

            //indica cuantos registros tiene el archivo
            this.regsQuantity = (int) Math.ceil(randomAccessFile.length() / (double) regSize);
        }

    }//end method

    //MUY importante cerrar nuestros archivos
    public void close() throws IOException {
        randomAccessFile.close();
    }

    //indicar la cantidad de registros de nuestro archivo
    public int fileSize() {
        return this.regsQuantity;
    }

    //insertar un nuevo resgistro en una posicio especifica
    public boolean putValue(int position, Laptop laptop) throws IOException {
        //primero: verificar que sea valida la insercion
        if (position < 0 && position > this.regsQuantity) {
            System.err.println("L1- Record position is out of bounds");
            return false;
        } else {
            if (laptop.sizeInBytes() > this.regSize) {
                System.err.println("L2- Record size is out of bounds");
                return false;
            } else {
                //BINGO
                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeInt(laptop.getSize());
                randomAccessFile.writeUTF(laptop.getColor());
                randomAccessFile.writeBoolean(laptop.isCamera());
                randomAccessFile.writeInt(laptop.getQuantity());
                randomAccessFile.writeInt(laptop.getCode());
                randomAccessFile.writeUTF(laptop.getBrand());
                randomAccessFile.writeInt(laptop.getYear());
                return true;
            }
        }
    }//end method

    //insertar al final del archivo
    public boolean addEndRecord(Laptop laptop) throws IOException {
        boolean success = putValue(this.regsQuantity, laptop);//pone el brazo en la ultima posicion para agregar otro estudiante
        if (success) {
            ++this.regsQuantity;// se aumenta la cantidad
        }
        return success;
    }//end method

    //obtener un estudiante  
    public Laptop getLaptop(int position) throws IOException {
        //validar la posicion 
        if (position >= 0 && position < this.regsQuantity) {
            //colocamos el brazo en el lugar adecuado
            randomAccessFile.seek(position * this.regSize);

            //llevamos a cabo la lectura
            Laptop laptopTemp = new Laptop();
            laptopTemp.setSize(randomAccessFile.readInt());
            laptopTemp.setColor(randomAccessFile.readUTF());
            laptopTemp.setCamera(randomAccessFile.readBoolean());
            laptopTemp.setQuantity(randomAccessFile.readInt());
            laptopTemp.setCode(randomAccessFile.readInt());
            laptopTemp.setBrand(randomAccessFile.readUTF());
            laptopTemp.setYear(randomAccessFile.readInt());

            if (laptopTemp.getCode() == 0) {
                return null;
            } else {
                return laptopTemp;
            }
        } else {
            System.err.println("L3- position is out of bounds");
            return null;
        }
    }//end method

    //eliminar un estudiante
    public boolean delete(int code) throws IOException {
        Laptop myLaptop;
        //ciclo buscar el studiantes
        for (int i = 0; i < regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            myLaptop = this.getLaptop(i);

            //preguntar si es el estudiante que desea borrar
            if (myLaptop.getCode() == code) {
                //marcar como eliminado
                myLaptop.setCode(0);

                return this.putValue(i, myLaptop);
            }
        }
        return false;
    }

    //retornar una lista de estudiantes
    public ArrayList<Laptop> getAllLaptop() throws IOException {
        ArrayList<Laptop> laptopsArray = new ArrayList<Laptop>();

        for (int i = 0; i < this.regsQuantity; i++) {
            Laptop lTemp = this.getLaptop(i);

            if (lTemp != null) {
                laptopsArray.add(lTemp);

            }
        }//end for
        return laptopsArray;
    }
    
     public boolean verifyBrand(String brand, int quantity) throws IOException {
        Laptop laptop;
        //Ciclo buscar el libro
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el libro fisico de la posicion actual
            laptop = this.getLaptop(i);

            //Pregunta si las marcas son iguales
            if (laptop.getBrand().equalsIgnoreCase(brand)) {
                laptop.setQuantity(laptop.getQuantity()+quantity);
                return true;
            } else {
            }
        }
        return false;
    }//End method
     
     public String createCode( ) throws IOException {
        String isbn="1";
        isbn=isbn+Consecutive();
        
        return isbn;
    }

    private String Consecutive() throws IOException {
        ArrayList<Laptop> laptop = getAllLaptop();
        String consecutive = "";
        String lastLaptop;
        int part;
        String consecutive2="";

        if (laptop.isEmpty()) {
            consecutive2 = "0000";
        } else {
            lastLaptop= String.valueOf(laptop.get(laptop.size() - 1).getCode());
            String part2 =lastLaptop.substring(1, 5);
            int part3 = Integer.parseInt(part2) ;
            part = part3 + 1;
            consecutive ="0000"+String.valueOf(part);
            consecutive2=consecutive.substring(consecutive.length()-4, consecutive.length());
        }
        return consecutive2;
    }    
    
    public Laptop searchBrand(String brand) throws IOException {
        Laptop laptop = new Laptop();
        ArrayList<Laptop> laptopA = getAllLaptop();

        //Ciclo buscar la laptop
        for (int i = 0; i < laptopA.size(); i++) {
            //obtener el libro fisico la laptop de la posicion actual
            laptop = this.getLaptop(i);

            //Pregunta si las marcas son iguales
            if (laptop.getBrand().equalsIgnoreCase(brand)) {
                return laptop;
            }
        }

        return null;
    }//End method

    public int reduceQuantity(String brand) throws IOException {
        Laptop laptop = new Laptop();
        int quantity = 0;
        laptop = searchBrand(brand);
        if (laptop != null && laptop.getQuantity() != 0) {
            laptop.setQuantity(laptop.getQuantity() - 1);
            quantity = laptop.getQuantity();
        }
        return quantity;
    }//End method

    public boolean update(String brand, int quantity1) throws IOException {
        Laptop laptop = new Laptop();
        ArrayList<Laptop> laptopA = getAllLaptop();

        //Ciclo buscar la laptop
        for (int i = 0; i < laptopA.size(); i++) {
            //obtener la laptop de la posicion actual
            laptop = this.getLaptop(i);

            //Pregunta si las marcas son iguales
            if (laptop.getBrand().equalsIgnoreCase(brand)) {
                laptop.setQuantity(quantity1);
                return this.putValue(i, laptop);
            }
        }
        return true;
    }//End method

}
