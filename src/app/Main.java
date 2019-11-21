package app;

import misc.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        Core sys = new Core();
        boolean exitcode = false;

        try {
            File teachers = Utils.openFile("testes/01/in/docentes.csv");
            sys.importTeacherFile(teachers);
            File vehicles = Utils.openFile("testes/01/in/veiculos.csv");
            sys.importVehicleFile(vehicles);
            File posts = Utils.openFile("testes/01/in/publicacoes.csv");
            sys.importPostFile(posts);
            File qualis = Utils.openFile("testes/01/in/qualis.csv");
            sys.importQualisFile(qualis);
            File rules = Utils.openFile("testes/01/in/regras.csv");
            sys.importRuleFile(rules);
        } catch (IOException e) {
            System.out.println((new exceptions.IOException()).getMessage());
            exitcode = true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            exitcode = true;
        }

        if(exitcode){
            System.exit(1);
        }
        // sys.reCredent(2017);
        System.out.print(sys.reCredent(2017));
        System.out.print(sys.listPosts());

        // Utils.printObjArray(sys.getTeachers());
        // Utils.printObjArray(sys.getVehs());
        // Utils.printObjArray(sys.getPosts());
        // Utils.printObjArray(sys.getQualifies());
        // Utils.printObjArray(sys.getRules());
        
    }
}