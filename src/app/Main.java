package app;

import misc.*;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        Core sys = new Core();

        try {
            File teacher = Utils.openFile("testes/01/in/docentes.csv");
            sys.importTeacherFile(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        sys.printTeachers();
    }
}