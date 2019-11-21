package app;

import misc.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

    /**
     * Notas pra eu n precisar ficar indo na especificacao toda hora
     * 
     * Implementar:
     * (POR ULTIMO) implementar execucao do programa usando flags
     * 
     * (LEMBRAR): fazer os sets de entrada, java docs e configurar o ant compiler
     * 
     * So precisa fazer 1 set de entrada, o segundo eh o primeiro com alguma 
     * inconsistencia (pode escolher qual tipo de inconsistencia)
     * 
     * "Conter o cadastro de pelo menos 5 docentes, 20 veículos, 30 publicações 
     * espalhadas pelos anos 2013 a 2016 (ao menos 10 delas devem ter mais 
     * de um autor), qualificação dos veículos em 3 anos diferentes e 2 regras 
     * de pontuação: uma para 2017 e outra para 2018"
     * 
     */

public class Main {
    public static void main(String[] args) throws Exception {
        Core sys = new Core();
        boolean readOnly = false;
        boolean writeOnly = false;

        try {
            if(!writeOnly){
                //READ FROM CSV
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
                sys.generateReports(2017);
            }    
        } catch (IOException e) {
            System.out.println((new exceptions.IOException()).getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }       
    }
}