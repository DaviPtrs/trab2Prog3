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
     * Classe main, que faz a chamada das funções de leitura de arquivo para carregar uma instância da classe Core,
     * além de gerar os arquivos de saída.
     * 
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