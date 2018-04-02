/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import domain.Cd;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author Steven
 * @author Eyleen
 */
public class CdFile {
    //atributos

    public RandomAccessFile randomAccessFile;//instacia./
    private int regsQuantity;//cantidad de registros en el archivo
    private int regSize;//tamanio del registro

    //constructor
    public CdFile(File file) throws IOException {
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
    public boolean putValue(int position, Cd cd) throws IOException {
        //primero: verificar que sea valida la insercion
        if (position < 0 && position > this.regsQuantity) {
            System.err.println("C1- Record position is out of bounds");
            return false;
        } else {
            if (cd.sizeInBytes() > this.regSize) {
                System.err.println("C2- Record size is out of bounds");
                return false;
            } else {
                //BINGO
                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeUTF(cd.getName());
                randomAccessFile.writeInt(cd.getSpace());
                randomAccessFile.writeInt(cd.getQuantity());
                randomAccessFile.writeInt(cd.getCode());
                randomAccessFile.writeUTF(cd.getBrand());
                randomAccessFile.writeInt(cd.getYear());

                return true;
            }
        }
    }//end method

    //insertar al final del archivo
    public boolean addEndRecord(Cd cd) throws IOException {
        boolean success = putValue(this.regsQuantity, cd);//pone el brazo en la ultima posicion para agregar otro estudiante
        if (success) {
            ++this.regsQuantity;// se aumenta la cantidad
        }
        return success;
    }//end method

    //obtener un estudiante   int 
    public Cd getCd(int position) throws IOException {
        //validar la posicion 
        if (position >= 0 && position < this.regsQuantity) {
            //colocamos el brazo en el lugar adecuado
            randomAccessFile.seek(position * this.regSize);

            //llevamos a cabo la lectura
            Cd cdTemp = new Cd();
            cdTemp.setName(randomAccessFile.readUTF());
            cdTemp.setSpace(randomAccessFile.readInt());
            cdTemp.setQuantity(randomAccessFile.readInt());
            cdTemp.setCode(randomAccessFile.readInt());
            cdTemp.setBrand(randomAccessFile.readUTF());
            cdTemp.setYear(randomAccessFile.readInt());

            if (cdTemp.getCode() == 0) {
                return null;
            } else {
                return cdTemp;
            }
        } else {
            System.err.println("C3- position is out of bounds");
            return null;
        }
    }//end method

    //eliminar un estudiante
    public boolean delete(int code) throws IOException {
        Cd myCd;
        //ciclo buscar el studiantes
        for (int i = 0; i < regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            myCd = this.getCd(i);

            //preguntar si es el estudiante que desea borrar
            if (myCd.getCode() == code) {
                //marcar como eliminado
                myCd.setCode(0);

                return this.putValue(i, myCd);
            }
        }
        return false;
    }

    //retornar una lista de estudiantes
    public ArrayList<Cd> getAllCd() throws IOException {
        ArrayList<Cd> cdsArray = new ArrayList<Cd>();

        for (int i = 0; i < this.regsQuantity; i++) {
            Cd cTemp = this.getCd(i);

            if (cTemp != null) {
                cdsArray.add(cTemp);

            }
        }//end for
        return cdsArray;
    }
     public boolean verifyName(String name, int quantity) throws IOException {
        Cd cd;
        //Ciclo buscar el libro
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el libro fisico de la posicion actual
            cd = this.getCd(i);

            //Pregunta si los nombres son iguales
            if (cd.getName().equalsIgnoreCase(name)) {
                cd.setQuantity(cd.getQuantity()+quantity);
                return true;
            } else {
            }
        }
        return false;
    }//End method
    
     public String createCode( ) throws IOException {
        String code="4";
        code=code+Consecutive();
        
        return code;
    }

    private String Consecutive() throws IOException {
        ArrayList<Cd> cd = getAllCd();
        String consecutive = "";
        String lastCd;
        int part;
        String consecutive2="";

        if (cd.isEmpty()) {
            consecutive2 = "0000";
        } else {
            lastCd= String.valueOf(cd.get(cd.size() - 1).getCode());
            String part2 =lastCd.substring(1, 5);
            int part3 = Integer.parseInt(part2) ;
            part = part3 + 1;
            consecutive ="0000"+String.valueOf(part);
            consecutive2=consecutive.substring(consecutive.length()-4, consecutive.length());
        }
        return consecutive2;
    } 
    
    public Cd searchName(String name) throws IOException {
        Cd cd = new Cd();
        ArrayList<Cd> cdA = getAllCd();

        //Ciclo buscar el dvd
        for (int i = 0; i < cdA.size(); i++) {
            //obtener el Dvd de la posicion actual
            cd = this.getCd(i);

            //Pregunta si los son iguales
            if (cd.getName().equalsIgnoreCase(name)) {
                return cd;
            }
        }

        return null;
    }//End method

    public int reduceQuantity(String name) throws IOException {
        Cd cd = new Cd();
        int quantity = 0;
        cd = searchName(name);
        if (cd != null && cd.getQuantity() != 0) {
            cd.setQuantity(cd.getQuantity() - 1);
            quantity = cd.getQuantity();
        }
        return quantity;
    }//End method

    public boolean update(String name, int quantity1) throws IOException {
        Cd cd = new Cd();
        ArrayList<Cd> cdA = getAllCd();

        //Ciclo buscar la laptop
        for (int i = 0; i < cdA.size(); i++) {
            //obtener la laptop de la posicion actual
            cd = this.getCd(i);

            //Pregunta si las marcas son iguales
            if (cd.getName().equalsIgnoreCase(name)) {
                cd.setQuantity(quantity1);
                return this.putValue(i, cd);
            }
        }
        return true;
    }//End method
}
