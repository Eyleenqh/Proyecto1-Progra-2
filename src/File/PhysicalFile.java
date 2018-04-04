/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import domain.Physical;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author Steven
 * @author Eyleen
 */
public class PhysicalFile {
    //atributos

    public RandomAccessFile randomAccessFile;//instacia./
    private int regsQuantity;//cantidad de registros en el archivo
    private int regSize;//tamanio del registro

    //constructor
    public PhysicalFile(File file) throws IOException {
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
    public boolean putValue(int position, Physical physical) throws IOException {
        //primero: verificar que sea valida la insercion
        if (position < 0 && position > this.regsQuantity) {
            System.err.println("P1- Record position is out of bounds");
            return false;
        } else {
            if (physical.sizeInBytes() > this.regSize) {
                System.err.println("P2- Record size is out of bounds");
                return false;
            } else {
                //BINGO
                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeInt(physical.getQuantity());
                randomAccessFile.writeUTF(physical.getName());
                randomAccessFile.writeUTF(physical.getAuthor());
                randomAccessFile.writeUTF(physical.getCode());
                randomAccessFile.writeInt(physical.getYear());
                randomAccessFile.writeInt(physical.getNumberPages());

                return true;
            }
        }
    }//end method

    //insertar al final del archivo
    public boolean addEndRecord(Physical physical) throws IOException {
        boolean success = putValue(this.regsQuantity, physical);//pone el brazo en la ultima posicion para agregar otro estudiante
        if (success) {
            ++this.regsQuantity;// se aumenta la cantidad
        }
        return success;
    }//end method

    //obtener libro 
    public Physical getPhysical(int position) throws IOException {
        //validar la posicion 
        if (position >= 0 && position <= this.regsQuantity) {
            //colocamos el brazo en el lugar adecuado
            randomAccessFile.seek(position * this.regSize);

            //llevamos a cabo la lectura
            Physical physicalTemp = new Physical();
            physicalTemp.setQuantity(randomAccessFile.readInt());
            physicalTemp.setName(randomAccessFile.readUTF());
            physicalTemp.setAuthor(randomAccessFile.readUTF());
            physicalTemp.setCode(randomAccessFile.readUTF());
            physicalTemp.setYear(randomAccessFile.readInt());
            physicalTemp.setNumberPages(randomAccessFile.readInt());

            if (physicalTemp.getName().equalsIgnoreCase("Deleted")) {
                return null;
            } else {
                return physicalTemp;
            }
        } else {
            System.err.println("P3- position is out of bounds");
            return null;
        }
    }//end method

    //eliminar un estudiante
    public boolean delete(String code) throws IOException {
        Physical myPhysical;
        //ciclo buscar el studiantes
        for (int i = 0; i < regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            myPhysical = this.getPhysical(i);

            //preguntar si es el estudiante que desea borrar
            if (myPhysical.getCode().equalsIgnoreCase(code)) {
                //marcar como eliminado
                myPhysical.setName("Deleted");

                return this.putValue(i, myPhysical);
            }
        }
        return false;
    }

    //retornar una lista de estudiantes
    public ArrayList<Physical> getAllPhysical() throws IOException {
        ArrayList<Physical> physicalArray = new ArrayList<Physical>();

        for (int i = 0; i < this.regsQuantity; i++) {
            Physical pTemp = this.getPhysical(i);

            if (pTemp != null) {
                physicalArray.add(pTemp);

            }
        }//end for
        return physicalArray;
    }

    public boolean verifyName(String name, int quantity) throws IOException {
        Physical physical;
        //Ciclo buscar el libro
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el libro fisico de la posicion actual
            physical = this.getPhysical(i);

            //Pregunta si los nombres son iguales
            if (physical.getName().equalsIgnoreCase(name)) {
                physical.setQuantity(physical.getQuantity() + quantity);
                return true;
            } else {
            }
        }
        return false;
    }//End method

    public String createISBN() throws IOException {
        String isbn = "P";
        isbn = isbn + Consecutive();

        return isbn;
    }

    private String Consecutive() throws IOException {
        ArrayList<Physical> physical = getAllPhysical();
        String consecutive = "";
        String lastBookPhysical;
        int part;
        String consecutive2 = "";

        if (physical.isEmpty()) {
            consecutive2 = "0000000000";
        } else {
            lastBookPhysical = physical.get(physical.size() - 1).getCode();
            String part2 = lastBookPhysical.substring(1, 11);
            int part3 = Integer.parseInt(part2);
            part = part3 + 1;
            consecutive = "0000000000" + String.valueOf(part);
            consecutive2 = consecutive.substring(consecutive.length() - 10, consecutive.length());
        }
        return consecutive2;
    }

    public Physical searchName(String name) throws IOException {
        Physical physical = new Physical();
        ArrayList<Physical> physicalA = getAllPhysical();

        //Ciclo buscar el libro
        for (int i = 0; i < physicalA.size(); i++) {
            //obtener el libro fisico de la posicion actual
            physical = this.getPhysical(i);

            //Pregunta si los nombres son iguales
            if (physical.getName().equalsIgnoreCase(name)) {
                return physical;
            }
        }

        return null;
    }//End method

    public int reduceQuantity(String name) throws IOException {
        Physical physical = new Physical();
        int quantity = 0;
        physical = searchName(name);
        if (physical != null && physical.getQuantity() != 0) {
            physical.setQuantity(physical.getQuantity() - 1);
            quantity = physical.getQuantity();
        }
        return quantity;
    }//End method

    public boolean update(String name, int quantity1) throws IOException {
        Physical physical = new Physical();
        ArrayList<Physical> physicalA = getAllPhysical();

        //Ciclo buscar el libro
        for (int i = 0; i < physicalA.size(); i++) {
            //obtener el libro fisico de la posicion actual
            physical = this.getPhysical(i);

            //Pregunta si los nombres son iguales
            if (physical.getName().equalsIgnoreCase(name)) {
                physical.setQuantity(quantity1);
                return this.putValue(i, physical);
            }
        }
        return true;
    }//End method
}
