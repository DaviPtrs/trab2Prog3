package app;

import misc.*;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        Core sys = new Core();

        try {
            File teachers = Utils.openFile("testes/01/in/docentes.csv");
            sys.importTeacherFile(teachers);
            File vehicles = Utils.openFile("testes/01/in/veiculos.csv");
            sys.importVehicleFile(vehicles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Utils.printObjArray(sys.getTeachers());
        Utils.printObjArray(sys.getVehs());
    }
}