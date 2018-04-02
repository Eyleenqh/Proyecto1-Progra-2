/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import domain.Dvd;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author Steven
 * @author Eyleen
 */
public class DvdFile {

    //atributos
    public RandomAccessFile randomAccessFile;//instacia./
    private int regsQuantity;//cantidad de registros en el archivo
    private int regSize;//tamanio del registro

    //constructor
    public DvdFile(File file) throws IOException {
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
    public boolean putValue(int position, Dvd dvd) throws IOException {
        //primero: verificar que sea valida la insercion
        if (position < 0 && position > this.regsQuantity) {
            System.err.println("D1- Record position is out of bounds");
            return false;
        } else {
            if (dvd.sizeInBytes() > this.regSize) {
                System.err.println("D2- Record size is out of bounds");
                return false;
            } else {
                //BINGO
                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeUTF(dvd.getName());
                randomAccessFile.writeInt(dvd.getSpace());
                randomAccessFile.writeInt(dvd.getQuantity());
                randomAccessFile.writeInt(dvd.getCode());
                randomAccessFile.writeUTF(dvd.getBrand());
                randomAccessFile.writeInt(dvd.getYear());

                return true;
            }
        }
    }//end method

    //insertar al final del archivo
    public boolean addEndRecord(Dvd dvd) throws IOException {
        boolean success = putValue(this.regsQuantity, dvd);//pone el brazo en la ultima posicion para agregar otro estudiante
        if (success) {
            ++this.regsQuantity;// se aumenta la cantidad
        }
        return success;
    }//end method

    //obtener un estudiante   
    public Dvd getDvd(int position) throws IOException {
        //validar la posicion 
        if (position >= 0 && position < this.regsQuantity) {
            //colocamos el brazo en el lugar adecuado
            randomAccessFile.seek(position * this.regSize);

            //llevamos a cabo la lectura
            Dvd dvdTemp = new Dvd();
            dvdTemp.setName(randomAccessFile.readUTF());
            dvdTemp.setSpace(randomAccessFile.readInt());
            dvdTemp.setQuantity(randomAccessFile.readInt());
            dvdTemp.setCode(randomAccessFile.readInt());
            dvdTemp.setBrand(randomAccessFile.readUTF());
            dvdTemp.setYear(randomAccessFile.readInt());

            if (dvdTemp.getCode() == 0) {
                return null;
            } else {
                return dvdTemp;
            }
        } else {
            System.err.println("D3- position is out of bounds");
            return null;
        }
    }//end method

    //eliminar un estudiante
    public boolean delete(int code) throws IOException {
        Dvd myDvd;
        //ciclo buscar el studiantes
        for (int i = 0; i < regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            myDvd = this.getDvd(i);

            //preguntar si es el estudiante que desea borrar
            if (myDvd.getCode() == code) {
                //marcar como eliminado
                myDvd.setCode(0);

                return this.putValue(i, myDvd);
            }
        }
        return false;
    }

    //retornar una lista de estudiantes
    public ArrayList<Dvd> getAllDvd() throws IOException {
        ArrayList<Dvd> dvdsArray = new ArrayList<Dvd>();

        for (int i = 0; i < this.regsQuantity; i++) {
            Dvd dTemp = this.getDvd(i);

            if (dTemp != null) {
                dvdsArray.add(dTemp);

            }
        }//end for
        return dvdsArray;
    }
     public boolean verifyName(String name, int quantity) throws IOException {
        Dvd dvd;
        //Ciclo buscar el libro
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el libro fisico de la posicion actual
            dvd = this.getDvd(i);

            //Pregunta si los nombres son iguales
            if (dvd.getName().equalsIgnoreCase(name)) {
                dvd.setQuantity(dvd.getQuantity()+quantity);
                return true;
            } else {
            }
        }
        return false;
    }//End method
    
     public String createCode( ) throws IOException {
        String code="5";
        code=code+Consecutive();
        
        return code;
    }

    private String Consecutive() throws IOException {
        ArrayList<Dvd> dvd = getAllDvd();
        String consecutive = "";
        String lastDvd;
        int part;
        String consecutive2="";

        if (dvd.isEmpty()) {
            consecutive2 = "0000";
        } else {
            lastDvd= String.valueOf(dvd.get(dvd.size() - 1).getCode());
            String part2 =lastDvd.substring(1, 5);
            int part3 = Integer.parseInt(part2) ;
            part = part3 + 1;
            consecutive ="0000"+String.valueOf(part);
            consecutive2=consecutive.substring(consecutive.length()-4, consecutive.length());
        }
        return consecutive2;
    }  
    
    public Dvd searchName(String name) throws IOException {
        Dvd dvd = new Dvd();
        ArrayList<Dvd> dvdA = getAllDvd();

        //Ciclo buscar el dvd
        for (int i = 0; i < dvdA.size(); i++) {
            //obtener el Dvd de la posicion actual
            dvd = this.getDvd(i);

            //Pregunta si los son iguales
            if (dvd.getName().equalsIgnoreCase(name)) {
                return dvd;
            }
        }

        return null;
    }//End method

    public int reduceQuantity(String name) throws IOException {
        Dvd dvd = new Dvd();
        int quantity = 0;
        dvd = searchName(name);
        if (dvd != null && dvd.getQuantity() != 0) {
            dvd.setQuantity(dvd.getQuantity() - 1);
            quantity = dvd.getQuantity();
        }
        return quantity;
    }//End method

    public boolean update(String name, int quantity1) throws IOException {
        Dvd dvd = new Dvd();
        ArrayList<Dvd> dvdA = getAllDvd();

        //Ciclo buscar la laptop
        for (int i = 0; i < dvdA.size(); i++) {
            //obtener la laptop de la posicion actual
            dvd = this.getDvd(i);

            //Pregunta si las marcas son iguales
            if (dvd.getName().equalsIgnoreCase(name)) {
                dvd.setQuantity(quantity1);
                return this.putValue(i, dvd);
            }
        }
        return true;
    }//End method
}

