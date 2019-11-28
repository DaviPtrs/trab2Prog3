package system.app;

import system.misc.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

    /**
     * Notas pra eu n precisar ficar indo na especificacao toda hora
     * 
     * (LEMBRAR): fazer os sets de entrada, java docs 
     * 
     * So precisa fazer 1 set de entrada, o segundo eh o primeiro com alguma 
     * inconsistencia (pode escolher qual tipo de inconsistencia)
     * 
     * "Conter o cadastro de pelo menos 5 docentes, 20 veículos, 30 publicações 
     * espalhadas pelos anos 2013 a 2016 (ao menos 10 delas devem ter mais 
     * de um autor), qualificação dos veículos em 3 anos diferentes e 2 regras 
     * de pontuação: uma para 2017 e outra para 2018"
     * 
     * por ultimo passar o script pra ver se n tem nenhuma disney
     */

public class Main {
    public static void main(String[] args) throws Exception {
        Core sys = new Core();
        boolean readOnly = false;
        boolean writeOnly = false;
        
        try {
            //Parsing argument flags
            HashMap<String, String> flags = Utils.flagParser(args);
            readOnly = flags.keySet().contains("read-only");
            writeOnly = flags.keySet().contains("write-only");
            if(readOnly && writeOnly){
                throw new IOException();
            }

            if(!writeOnly){
                //READ FROM CSV
                File teachers = Utils.openFile(flags.get("d"));
                sys.importTeacherFile(teachers);
                File vehicles = Utils.openFile(flags.get("v"));
                sys.importVehicleFile(vehicles);
                File posts = Utils.openFile(flags.get("p"));
                sys.importPostFile(posts);
                File qualis = Utils.openFile(flags.get("q"));
                sys.importQualisFile(qualis);
                File rules = Utils.openFile(flags.get("r"));
                sys.importRuleFile(rules);
                sys.setCredentYear(Integer.parseInt(flags.get("a")));
                if(readOnly){
                    //READ-ONLY
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("recredenciamento.dat"));
                    out.writeObject(sys);
                    out.close();
                }
            }else{
                //WRITE-ONLY
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("recredenciamento.dat"));
                sys = (Core)in.readObject();
                in.close();                
            }
            if(!readOnly || writeOnly){
                //GENERATE OUTPUTS
                sys.generateReports(sys.getCredentYear(),"");
            }    
        } catch (IOException e) {
            System.out.println((new system.exceptions.IOException()).getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }       
    }
}