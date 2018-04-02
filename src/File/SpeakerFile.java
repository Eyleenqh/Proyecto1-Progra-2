/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import domain.Speaker;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author Steven
 * @author Eyleen
 */
public class SpeakerFile {

    //atributos
    public RandomAccessFile randomAccessFile;//instacia./
    private int regsQuantity;//cantidad de registros en el archivo
    private int regSize;//tamanio del registro

    //constructor
    public SpeakerFile(File file) throws IOException {
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
    public boolean putValue(int position, Speaker speaker) throws IOException {
        //primero: verificar que sea valida la insercion
        if (position < 0 && position > this.regsQuantity) {
            System.err.println("Sp1- Record position is out of bounds");
            return false;
        } else {
            if (speaker.sizeInBytes() > this.regSize) {
                System.err.println("Sp2- Record size is out of bounds");
                return false;
            } else {
                //BINGO
                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeInt(speaker.getQuantitySpeaker());
                randomAccessFile.writeUTF(speaker.getSize());
                randomAccessFile.writeInt(speaker.getQuantity());
                randomAccessFile.writeInt(speaker.getCode());
                randomAccessFile.writeUTF(speaker.getBrand());
                randomAccessFile.writeInt(speaker.getYear());

                return true;
            }
        }
    }//end method

    //insertar al final del archivo
    public boolean addEndRecord(Speaker speaker) throws IOException {
        boolean success = putValue(this.regsQuantity, speaker);//pone el brazo en la ultima posicion para agregar otro estudiante
        if (success) {
            ++this.regsQuantity;// se aumenta la cantidad
        }
        return success;
    }//end method

    //obtener un estudiante  
    public Speaker getSpeaker(int position) throws IOException {
        //validar la posicion 
        if (position >= 0 && position < this.regsQuantity) {
            //colocamos el brazo en el lugar adecuado
            randomAccessFile.seek(position * this.regSize);

            //llevamos a cabo la lectura
            Speaker speakerTemp = new Speaker();
            speakerTemp.setQuantitySpeaker(randomAccessFile.readInt());
            speakerTemp.setSize(randomAccessFile.readUTF());
            speakerTemp.setQuantity(randomAccessFile.readInt());
            speakerTemp.setCode(randomAccessFile.readInt());
            speakerTemp.setBrand(randomAccessFile.readUTF());
            speakerTemp.setYear(randomAccessFile.readInt());

            if (speakerTemp.getCode() == 0) {
                return null;
            } else {
                return speakerTemp;
            }
        } else {
            System.err.println("Sp3- position is out of bounds");
            return null;
        }
    }//end method

    //eliminar un estudiante
    public boolean delete(int code) throws IOException {
        Speaker mySpeaker;
        //ciclo buscar el studiantes
        for (int i = 0; i < regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            mySpeaker = this.getSpeaker(i);

            //preguntar si es el estudiante que desea borrar
            if (mySpeaker.getCode() == code) {
                //marcar como eliminado
                mySpeaker.setCode(0);

                return this.putValue(i, mySpeaker);
            }
        }
        return false;
    }

    //retornar una lista de estudiantes
    public ArrayList<Speaker> getAllSpeaker() throws IOException {
        ArrayList<Speaker> speakersArray = new ArrayList<Speaker>();

        for (int i = 0; i < this.regsQuantity; i++) {
            Speaker sTemp = this.getSpeaker(i);

            if (sTemp != null) {
                speakersArray.add(sTemp);

            }
        }//end for
        return speakersArray;
    }

    public boolean verifyBrand(String brand, int quantity) throws IOException {
        Speaker speaker;
        //Ciclo buscar el libro
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el libro fisico de la posicion actual
            speaker = this.getSpeaker(i);

            //Pregunta si los nombres son iguales
            if (speaker.getBrand().equalsIgnoreCase(brand)) {
                speaker.setQuantity(speaker.getQuantity() + quantity);
                return true;
            } else {
            }
        }
        return false;
    }//End method

    public String createCode() throws IOException {
        String code = "2";
        code = code + Consecutive();

        return code;
    }

    private String Consecutive() throws IOException {
        ArrayList<Speaker> speaker = getAllSpeaker();
        String consecutive = "";
        String lastSpeaker;
        int part;
        String consecutive2 = "";

        if (speaker.isEmpty()) {
            consecutive2 = "0000";
        } else {
            lastSpeaker = String.valueOf(speaker.get(speaker.size() - 1).getCode());
            String part2 = lastSpeaker.substring(1, 5);
            int part3 = Integer.parseInt(part2);
            part = part3 + 1;
            consecutive = "0000" + String.valueOf(part);
            consecutive2 = consecutive.substring(consecutive.length() - 4, consecutive.length());
        }
        return consecutive2;
    }

    public Speaker searchBrand(String brand) throws IOException {
        Speaker speaker = new Speaker();
        ArrayList<Speaker> speakerA = getAllSpeaker();

        //Ciclo buscar la laptop
        for (int i = 0; i < speakerA.size(); i++) {
            //obtener el libro fisico la laptop de la posicion actual
            speaker = this.getSpeaker(i);

            //Pregunta si las marcas son iguales
            if (speaker.getBrand().equalsIgnoreCase(brand)) {
                return speaker;
            }
        }

        return null;
    }//End method

    public int reduceQuantity(String brand) throws IOException {
        Speaker speaker = new Speaker();
        int quantity = 0;
        speaker = searchBrand(brand);
        if (speaker != null && speaker.getQuantity() != 0) {
            speaker.setQuantity(speaker.getQuantity() - 1);
            quantity = speaker.getQuantity();
        }
        return quantity;
    }//End method

    public boolean update(String brand, int quantity1) throws IOException {
        Speaker speaker = new Speaker();
        ArrayList<Speaker> speakerA = getAllSpeaker();

        //Ciclo buscar la laptop
        for (int i = 0; i < speakerA.size(); i++) {
            //obtener la laptop de la posicion actual
            speaker = this.getSpeaker(i);

            //Pregunta si las marcas son iguales
            if (speaker.getBrand().equalsIgnoreCase(brand)) {
                speaker.setQuantity(quantity1);
                return this.putValue(i, speaker);
            }
        }
        return true;
    }//End method
}
