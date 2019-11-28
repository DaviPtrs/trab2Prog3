package web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

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

import system.app.Core;
import system.app.Teacher;
import system.misc.Utils;
import web.storage.StorageFileNotFoundException;
import web.storage.StorageService;


@Controller
public class FileUploadController {

    private final StorageService storageService;

    private ArrayList<Teacher> getDocentes(){
        Core aux = new Core();
        ArrayList<Teacher> result = null;
        try {
            File teachers = Utils.openFile(storageService.load("docentes.csv").toString());
            aux.importTeacherFile(teachers);
            result = aux.getTeachers();
            aux = null;
        } catch (Exception e) {}
        return result;
    }

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(HttpServletRequest request, Model model) throws IOException {

        List<String> fileUrlList = storageService.loadAll().map(
            path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
            "serveFile", path.getFileName().toString()).build().toString())
            .collect(Collectors.toList());
               
           
        Map<String, String> infileMap = new HashMap<String, String>();
        Map<String, String> outfileMap = new HashMap<String, String>();
        for(String url: fileUrlList){
            if(!url.contains("relatorio-")){
                infileMap.put(url.replaceFirst("http://localhost:8080/files/", ""), url);
            }else{
                outfileMap.put(url.replaceFirst("http://localhost:8080/files/relatorio-", ""), url);
            }
        }
        
        model.addAttribute("docentes", this.getDocentes());
        model.addAttribute("infiles", infileMap);
        model.addAttribute("outfiles", outfileMap);

        return "index";
    }

    @PostMapping("/clnrelat")
    public String cleanRelatorios(){
        List<String> fileUrlList = storageService.loadAll().map(
            path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
            "serveFile", path.getFileName().toString()).build().toString())
            .collect(Collectors.toList());
        for(String url: fileUrlList){
            if(url.contains("relatorio-")){
                String filename = url.replace("http://localhost:8080/files", "");
                String filePath = this.storageService.getRootPath().toAbsolutePath().toString().concat(filename);
                try {
                    Files.delete(Paths.get(filePath));
                } catch (Exception e) {}
            }
        }
        return "redirect:/";
    }

    @PostMapping("/clnentradas")
    public String cleanEntradas(){
        List<String> fileUrlList = storageService.loadAll().map(
            path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
            "serveFile", path.getFileName().toString()).build().toString())
            .collect(Collectors.toList());
        for(String url: fileUrlList){
            if(!url.contains("relatorio-")){
                String filename = url.replace("http://localhost:8080/files", "");
                String filePath = this.storageService.getRootPath().toAbsolutePath().toString().concat(filename);
                try {
                    Files.delete(Paths.get(filePath));
                } catch (Exception e) {}
            }
        }
        return "redirect:/";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("infile") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        if(!file.isEmpty()){
            storageService.store(file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
        }

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}