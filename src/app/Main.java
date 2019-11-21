package app;

import misc.*;
import java.io.File;
import java.io.IOException;

    /**
     * Notas pra eu n precisar ficar indo na especificacao toda hora
     * 
     * Implementar:
     * funcao read-only -> ler os csv e serializar em recredenciamento.bat
     * funcao write-only -> carregar o .dat e gerar relatorio (csv)
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
        
        try {
            sys.generateReports(2017);
        } catch (IOException e) {
            System.out.println((new exceptions.IOException()).getMessage());
            exitcode = true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            exitcode = true;
        }
    }
}