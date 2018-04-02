/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import domain.Student;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author Steven
 * @author Eyleen
 */
public class StudentFile {
    //atributos

    public RandomAccessFile randomAccessFile;//instacia./
    private int regsQuantity;//cantidad de registros en el archivo
    private int regSize;//tamanio del registro

    //Contadores
    private int countInf = 0;
    private int countAgr = 0;
    private int countEdu = 0;
    
    private int count;

    //constructor
    public StudentFile(File file) throws IOException {
        //indico el tamanio maximo en bytes que va a tener el registo
        this.regSize = 140;//cambiaaarrr

        //una validacion sencilla 
        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + " is an invalid file");
        } else {
            //crear la nueva instancia de RAF
            randomAccessFile = new RandomAccessFile(file, "rw");

            //necesitamos indicar cuantos registros tiene el archivo
            this.regsQuantity = (int) Math.ceil(randomAccessFile.length() / (double) regSize);//el double le da presicion
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
    public boolean putValue(int position, Student student) throws IOException {
        //primero: verificar que sea valida la insercion
        if (position < 0 && position > this.regsQuantity) {
            System.err.println("S1- Record position is out of bounds");
            return false;
        } else {
            if (student.sizeInBytes() > this.regSize) {
                System.err.println("S2- Record size is out of bounds");
                return false;
            } else {
                //BINGO
                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeUTF(student.getName());
                randomAccessFile.writeUTF(student.getSurname());
                randomAccessFile.writeUTF(student.getUniversitiId());
                randomAccessFile.writeUTF(student.getCareer());
                randomAccessFile.writeInt(student.getId());
                randomAccessFile.writeInt(student.getPhoneNumber());
                randomAccessFile.writeInt(student.getYear());
                return true;
            }
        }
    }//end method

    //insertar al final del archivo
    public boolean addEndRecord(Student student) throws IOException {
        boolean success = putValue(this.regsQuantity, student);//pone el brazo en la ultima posicion para agregar otro estudiante
        if (success) {
            ++this.regsQuantity;// se aumenta la cantidad
        }
        return success;
    }//end method

    //obtener un estudiante
    public Student getStudent(int position) throws IOException {
        //validar la posicion 
        if (position >= 0 && position < this.regsQuantity) {
            //colocamos el brazo en el lugar adecuado
            randomAccessFile.seek(position * this.regSize);

            //llevamos a cabo la lectura
            Student studentTemp = new Student();
            studentTemp.setName(randomAccessFile.readUTF());
            studentTemp.setSurname(randomAccessFile.readUTF());
            studentTemp.setUniversitiId(randomAccessFile.readUTF());
            studentTemp.setCareer(randomAccessFile.readUTF());
            studentTemp.setYear(randomAccessFile.readInt());
            studentTemp.setId(randomAccessFile.readInt());
            studentTemp.setPhoneNumber(randomAccessFile.readInt());

            if (studentTemp.getName().equalsIgnoreCase("deleted")) {
                return null;
            } else {
                return studentTemp;
            }
        } else {
            System.err.println("S3- position is out of bounds");
            return null;
        }
    }//end method

    //eliminar un estudiante
    public boolean delete(String name) throws IOException {
        Student myStudent;
        //ciclo buscar el studiantes
        for (int i = 0; i < regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            myStudent = this.getStudent(i);

            //preguntar si es el estudiante que desea borrar
            if (myStudent.getName().equalsIgnoreCase(name)) {
                //marcar como eliminado
                myStudent.setName("deleted");

                return this.putValue(i, myStudent);
            }
        }
        return false;
    }

    //retornar una lista de estudiantes
    public ArrayList<Student> getAllStudent() throws IOException {
        ArrayList<Student> studentsArray = new ArrayList<Student>();

        for (int i = 0; i < this.regsQuantity; i++) {
            Student sTemp = this.getStudent(i);

            if (sTemp != null) {
                studentsArray.add(sTemp);

            }
        }//end for
        return studentsArray;
    }

    public boolean verifyId(int id) throws IOException {
        Student student;
        //Ciclo buscar el estudiante
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            student = this.getStudent(i);

            //Pregunta si las cedulas son iguales
            if (student.getId() == id) {
                return true;
            } else {
            }
        }
        return false;
    }//End method

    public String createUniversityId(String career, String year) throws IOException {
        char lastCharacter = year.charAt(year.length() - 1);
        String id = career + lastCharacter + Consecutive(career);
        return id;
    }

    private String Consecutive(String initial) throws IOException {
        if (initial.equals("I")) {
            this.countInf++;
            count = this.countInf;
        } else {
            if (initial.equals("A")) {
                this.countAgr++;
                count = this.countAgr;
            } else {
                this.countEdu++;
                count = this.countEdu;
            }
        }

        String consecutive = "";
        String consecutive2 = "";

        consecutive = "000" + String.valueOf(count);
        consecutive2 = consecutive.substring(consecutive.length() - 3, consecutive.length());
        return consecutive2;
        /*ArrayList<Student> students = getAllStudent();
        String consecutive = "";
        String lastUniversityId;
        int part;
        String consecutive2="";

        if (students.isEmpty()) {
            consecutive2 = "000";
        } else {
            lastUniversityId = students.get(students.size() - 1).getUniversitiId();
            String part2 =lastUniversityId.substring(2, 5);
            int part3 = Integer.parseInt(part2) ;
            part = part3 + 1;
            consecutive ="000"+String.valueOf(part);
            consecutive2=consecutive.substring(consecutive.length()-3, consecutive.length());
        }
        return consecutive2;*/
    }

    public boolean verifyUniversityId(String uId) throws IOException {
        ArrayList<Student> students = getAllStudent();
        Student student;
        //Ciclo buscar el estudiante
        for (int i = 0; i < students.size(); i++) {
            //obtener el estudiante de la posicion actual
            student = this.getStudent(i);

            //Pregunta si las cedulas son iguales
            if (student.getUniversitiId().equalsIgnoreCase(uId)) {
                return true;
            } else {
            }
        }
        return false;
    }//End method
}
