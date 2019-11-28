package web;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import system.app.Core;
import system.misc.Utils;
import web.storage.StorageService;

@Controller
public class MainSystemController {
    private final StorageService storageService;
    private Core systemCore = null;

    @Autowired
    public MainSystemController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping("/gerar")
    public String gerarRelatorios(@RequestParam("ano") String ano, RedirectAttributes redirectAttributes) {
        try {
            if(systemCore == null){
                this.systemCore = new Core();
                File teachers = Utils.openFile(storageService.load("docentes.csv").toString());
                this.systemCore.importTeacherFile(teachers);
                File vehicles = Utils.openFile(storageService.load("veiculos.csv").toString());
                this.systemCore.importVehicleFile(vehicles);
                File posts = Utils.openFile(storageService.load("publicacoes.csv").toString());
                this.systemCore.importPostFile(posts);
                File qualis = Utils.openFile(storageService.load("qualis.csv").toString());
                this.systemCore.importQualisFile(qualis);
                File rules = Utils.openFile(storageService.load("regras.csv").toString());
                this.systemCore.importRuleFile(rules);
            }
            int credentYear = Integer.parseInt(ano);
            this.systemCore.setCredentYear(credentYear);
            this.systemCore.generateReports(this.systemCore.getCredentYear(), storageService.getRootPath().toString().concat("/relatorio-"));
            redirectAttributes.addFlashAttribute("RelatorioMessage", 
                            "Relatorios gerados com sucesso!");
        } catch (Exception e) {
            this.systemCore = null;
            String mensagemErro = "Ocorreu algum erro ao gerar os relatorios."
                                + " Certifique-se de ter feito o upload de todos"
                                + " os .csv necessarios. Erro: " + e.getMessage();
            redirectAttributes.addFlashAttribute("RelatorioMessage", mensagemErro);
            
        }
        return "redirect:/";
    }
}