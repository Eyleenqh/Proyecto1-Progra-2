/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import domain.Projector;
import domain.Speaker;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author Steven
 * @author Eyleen
 */
public class ProjectorFile {

    //atributos
    public RandomAccessFile randomAccessFile;//instacia./
    private int regsQuantity;//cantidad de registros en el archivo
    private int regSize;//tamanio del registro

    //constructor
    public ProjectorFile(File file) throws IOException {
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

    //Cierra el archivo
    public void close() throws IOException {
        randomAccessFile.close();
    }

    //indicar la cantidad de registros de nuestro archivo
    public int fileSize() {
        return this.regsQuantity;
    }

    //insertar un nuevo resgistro en una posicio especifica
    public boolean putValue(int position, Projector projector) throws IOException {
        //primero: verificar que sea valida la insercion
        if (position < 0 && position > this.regsQuantity) {
            System.err.println("P1- Record position is out of bounds");
            return false;
        } else {
            if (projector.sizeInBytes() > this.regSize) {
                System.err.println("P2- Record size is out of bounds");
                return false;
            } else {
                //BINGO
                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeUTF(projector.getScreenSize());
                randomAccessFile.writeInt(projector.getQuantity());
                randomAccessFile.writeInt(projector.getCode());
                randomAccessFile.writeUTF(projector.getBrand());
                randomAccessFile.writeInt(projector.getYear());

                return true;
            }
        }
    }//end method

    //insertar al final del archivo
    public boolean addEndRecord(Projector projector) throws IOException {
        boolean success = putValue(this.regsQuantity, projector);//pone el brazo en la ultima posicion para agregar otro estudiante
        if (success) {
            ++this.regsQuantity;// se aumenta la cantidad
        }
        return success;
    }//end method

    //obtener un proyector
    public Projector getProjector(int position) throws IOException {
        //validar la posicion 
        if (position >= 0 && position < this.regsQuantity) {
            //colocamos el brazo en el lugar adecuado
            randomAccessFile.seek(position * this.regSize);

            //llevamos a cabo la lectura
            Projector projectorTemp = new Projector();
            projectorTemp.setScreenSize(randomAccessFile.readUTF());
            projectorTemp.setQuantity(randomAccessFile.readInt());
            projectorTemp.setCode(randomAccessFile.readInt());
            projectorTemp.setBrand(randomAccessFile.readUTF());
            projectorTemp.setYear(randomAccessFile.readInt());
            
            if (projectorTemp.getCode() == 0) {
                return null;
            } else {
                return projectorTemp;
            }
        } else {
            System.err.println("P3- position is out of bounds");
            return null;
        }
    }//end method

    //eliminar un proyector
    public boolean delete(int code) throws IOException {
        Projector myProjector;
        //ciclo buscar el proyector
        for (int i = 0; i < regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            myProjector = this.getProjector(i);

            //preguntar si es el projector que desea borrar
            if (myProjector.getCode() == code) {
                //marcar como eliminado
                myProjector.setCode(0);

                return this.putValue(i, myProjector);
            }
        }
        return false;
    }

    //retornar una lista de los proyectores
    public ArrayList<Projector> getAllProjector() throws IOException {
        ArrayList<Projector> projectorsArray = new ArrayList<Projector>();

        for (int i = 0; i < this.regsQuantity; i++) {
            Projector pTemp = this.getProjector(i);

            if (pTemp != null) {
                projectorsArray.add(pTemp);

            }
        }//end for
        return projectorsArray;
    }
     public boolean verifyBrand(String brand, int quantity) throws IOException {
        Projector projector;
        //Ciclo buscar el libro
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el libro fisico de la posicion actual
            projector = this.getProjector(i);

            //Pregunta si los nombres son iguales
            if (projector.getBrand().equalsIgnoreCase(brand)) {
                projector.setQuantity(projector.getQuantity()+quantity);
                return true;
            } else {
            }
        }
        return false;
    }//End method
    
     public String createCode( ) throws IOException {
        String code="3";
        code=code+Consecutive();
        
        return code;
    }

    private String Consecutive() throws IOException {
        ArrayList<Projector> projector = getAllProjector();
        String consecutive = "";
        String lastProjector;
        int part;
        String consecutive2="";

        if (projector.isEmpty()) {
            consecutive2 = "0000";
        } else {
            lastProjector= String.valueOf(projector.get(projector.size() - 1).getCode());
            String part2 =lastProjector.substring(1, 5);
            int part3 = Integer.parseInt(part2) ;
            part = part3 + 1;
            consecutive ="0000"+String.valueOf(part);
            consecutive2=consecutive.substring(consecutive.length()-4, consecutive.length());
        }
        return consecutive2;
    }  
    
    public Projector searchBrand(String brand) throws IOException {
        Projector projector = new Projector();
        ArrayList<Projector> speakerA = getAllProjector();

        //Ciclo buscar la laptop
        for (int i = 0; i < speakerA.size(); i++) {
            //obtener el libro fisico la laptop de la posicion actual
            projector = this.getProjector(i);

            //Pregunta si las marcas son iguales
            if (projector.getBrand().equalsIgnoreCase(brand)) {
                return projector;
            }
        }

        return null;
    }//End method

    public int reduceQuantity(String brand) throws IOException {
        Projector projector = new Projector();
        int quantity = 0;
       projector = searchBrand(brand);
        if (projector != null && projector.getQuantity() != 0) {
            projector.setQuantity(projector.getQuantity() - 1);
            quantity = projector.getQuantity();
        }
        return quantity;
    }//End method

    public boolean update(String brand, int quantity1) throws IOException {
        Projector projector = new Projector();
        ArrayList<Projector> projectorA = getAllProjector();

        //Ciclo buscar la laptop
        for (int i = 0; i < projectorA.size(); i++) {
            //obtener la laptop de la posicion actual
            projector = this.getProjector(i);

            //Pregunta si las marcas son iguales
            if (projector.getBrand().equalsIgnoreCase(brand)) {
                projector.setQuantity(quantity1);
                return this.putValue(i, projector);
            }
        }
        return true;
    }//End method
}
