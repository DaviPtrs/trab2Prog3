package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import system.misc.Utils;
import web.storage.StorageFileNotFoundException;
import web.storage.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import system.app.*;
import system.exceptions.*;
import system.misc.*;




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
            int credentYear = Integer.parseInt(ano);
            this.systemCore.setCredentYear(credentYear);
            this.systemCore.generateReports(this.systemCore.getCredentYear(), storageService.getRootPath().toString().concat("/relatorio-"));
            redirectAttributes.addFlashAttribute("RelatorioMessage", 
                            "Relatorios gerados com sucesso!");
            //tem que descobrir como que manda essa lista pra model do destino do redirect
            // redirectAttributes.addAttribute("docentes", this.systemCore.getTeachers());
        } catch (Exception e) {
            String mensagemErro = "Ocorreu algum erro ao gerar os relatorios."
                                + " Certifique-se de ter feito o upload de todos"
                                + " os .csv necessarios. Erro: " + e.getMessage();
            redirectAttributes.addFlashAttribute("RelatorioMessage", mensagemErro);
            
        }
        return "redirect:/";
    }

    // public String loadSystemData(Model model){
    //     try {
            // this.systemCore = new Core();
            // File teachers = Utils.openFile(storageService.load("docentes.csv").toString());
            // this.systemCore.importTeacherFile(teachers);
            // File vehicles = Utils.openFile(storageService.load("veiculos.csv").toString());
            // this.systemCore.importVehicleFile(vehicles);
            // File posts = Utils.openFile(storageService.load("publicacoes.csv").toString());
            // this.systemCore.importPostFile(posts);
            // File qualis = Utils.openFile(storageService.load("qualis.csv").toString());
            // this.systemCore.importQualisFile(qualis);
            // File rules = Utils.openFile(storageService.load("regras.csv").toString());
            // this.systemCore.importRuleFile(rules);

    //         model.addAttribute("docentes", this.systemCore.getTeachers());
    //     } catch (Exception e) {
    //         this.systemCore = null;
    //     }
    //     return "index";
    // }
}