/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import domain.Digital;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author Steven
 * @author Eyleen
 */
public class DigitalFile {
    //atributos

    public RandomAccessFile randomAccessFile;//instacia./
    private int regsQuantity;//cantidad de registros en el archivo
    private int regSize;//tamanio del registro

    //constructor
    public DigitalFile(File file) throws IOException {
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
    public boolean putValue(int position, Digital digital) throws IOException {
        //primero: verificar que sea valida la insercion
        if (position < 0 && position > this.regsQuantity) {
            System.err.println("D1- Record position is out of bounds");
            return false;
        } else {
            if (digital.sizeInBytes() > this.regSize) {
                System.err.println("D2- Record size is out of bounds");
                return false;
            } else {
                //BINGO
                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeUTF(digital.getFormat());
                randomAccessFile.writeUTF(digital.getName());
                randomAccessFile.writeUTF(digital.getAuthor());
                randomAccessFile.writeUTF(digital.getCode());
                randomAccessFile.writeInt(digital.getYear());
                randomAccessFile.writeInt(digital.getNumberPages());

                return true;
            }
        }
    }//end method

    //insertar al final del archivo
    public boolean addEndRecord(Digital digital) throws IOException {
        boolean success = putValue(this.regsQuantity, digital);//pone el brazo en la ultima posicion para agregar otro estudiante
        if (success) {
            ++this.regsQuantity;// se aumenta la cantidad
        }
        return success;
    }//end method

    //obtener un estudiante   int 
    public Digital getDigital(int position) throws IOException {
        //validar la posicion 
        if (position >= 0 && position < this.regsQuantity) {
            //colocamos el brazo en el lugar adecuado
            randomAccessFile.seek(position * this.regSize);

            //llevamos a cabo la lectura
            Digital digitalTemp = new Digital();
            digitalTemp.setFormat(randomAccessFile.readUTF());
            digitalTemp.setName(randomAccessFile.readUTF());
            digitalTemp.setAuthor(randomAccessFile.readUTF());
            digitalTemp.setCode(randomAccessFile.readUTF());
            digitalTemp.setYear(randomAccessFile.readInt());
            digitalTemp.setNumberPages(randomAccessFile.readInt());

            if (digitalTemp.getCode().equalsIgnoreCase("Deleted")) {
                return null;
            } else {
                return digitalTemp;
            }
        } else {
            System.err.println("D3- position is out of bounds");
            return null;
        }
    }//end method

    //eliminar un estudiante
    public boolean delete(String code) throws IOException {
        Digital myDigital;
        //ciclo buscar el studiantes
        for (int i = 0; i < regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            myDigital = this.getDigital(i);

            //preguntar si es el estudiante que desea borrar
            if (myDigital.getCode().equalsIgnoreCase(code)) {
                //marcar como eliminado
                myDigital.setCode("Deleted");

                return this.putValue(i, myDigital);
            }
        }
        return false;
    }

    //retornar una lista de estudiantes
    public ArrayList<Digital> getAllDigital() throws IOException {
        ArrayList<Digital> digitalArray = new ArrayList<Digital>();

        for (int i = 0; i < this.regsQuantity; i++) {
            Digital dTemp = this.getDigital(i);

            if (dTemp != null) {
                digitalArray.add(dTemp);

            }
        }//end for
        return digitalArray;
    }
    
     public boolean verifyName(String name) throws IOException {
        Digital digital;
        //Ciclo buscar el libro
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el libro fisico de la posicion actual
            digital = this.getDigital(i);

            //Pregunta si los nombres son iguales
            if (digital.getName().equalsIgnoreCase(name)) {
                return true;
            } else {
            }
        }
        return false;
    }//End method
     
     public String createISBN( ) throws IOException {
        String isbn="D";
        isbn=isbn+Consecutive();
        
        return isbn;
    }

    private String Consecutive() throws IOException {
        ArrayList<Digital> digital = getAllDigital();
        String consecutive = "";
        String lastBookPhysical;
        int part;
        String consecutive2="";

        if (digital.isEmpty()) {
            consecutive2 = "0000000000";
        } else {
            lastBookPhysical= digital.get(digital.size() - 1).getCode();
            String part2 =lastBookPhysical.substring(1, 11);
            int part3 = Integer.parseInt(part2) ;
            part = part3 + 1;
            consecutive ="0000000000"+String.valueOf(part);
            consecutive2=consecutive.substring(consecutive.length()-10, consecutive.length());
        }
        return consecutive2;
    }

}
