package app;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import misc.*;

/**
 * Core
 */
public class Core {
    private ArrayList<ScoreRules> rules = new ArrayList<ScoreRules>();
    private ArrayList<Vehicle> vehs = new ArrayList<Vehicle>();
    private ArrayList<Qualify> qualifies = new ArrayList<Qualify>();
    private ArrayList<Post> posts = new ArrayList<Post>();
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();

    public Core(){}

    //Add functions
    public void addRule(ScoreRules r){
        rules.add(r);
    }

    public void addVehicle(Vehicle v) throws Exception{
        if(vehs.contains(v)){
            throw new Exception("Código repetido para Veículo: " + v.getCod());
        }else{
            vehs.add(v); 
        }
    }
    
    public void addTeacher(Teacher t) throws Exception{
        if(teachers.contains(t)){
            throw new Exception("Código repetido para Docente: " + t.getId());
        }else{
            teachers.add(t);//mesma coisa da func de cima
        }
    }

    public void addQualify(Qualify q){
        qualifies.add(q);
    }
    
    public void addPost(Post p){
        posts.add(p);
    }
    

    //Delete functions
    public void delRule(ScoreRules r){
        rules.remove(r);
        r = null;
        System.gc();
    }

    public void delVehicle(Vehicle v){
        vehs.remove(v);
        v = null;
        System.gc();
    }

    public void delQualify(Qualify q){
        qualifies.remove(q);
        q = null;
        System.gc();
    }

    public void delPost(Post p){
        posts.remove(p);
        p = null;
        System.gc();
    }

    public void delTeacher(Teacher t){
        teachers.remove(t);
        t = null;
        System.gc();
    }


    //Import from file functions
    public void importTeacherFile(File infile) throws Exception {
        //Convert file into a input scanner
        Scanner input = null;
        input = new Scanner(infile);

        input.nextLine();
        while(input.hasNextLine()){
            String line = input.nextLine();
            String[] fields = line.split(";");

            if((fields.length < 4) || (fields.length > 5)){
                input.close();
                throw new Exception("Erro de formatacao");
            }else{
                long id = Long.parseLong(fields[0]);
                String name = fields[1];
                Date birth = Utils.convertDate(fields[2]);
                Date entry = Utils.convertDate(fields[3]);
                boolean isMajor = false;
                if(fields.length == 5){
                    if(fields[4].compareToIgnoreCase("X") == 0){
                        isMajor = true;      
                    }
                }
                Teacher obj = new Teacher(id, name, birth, entry, isMajor);

                this.addTeacher(obj);
            }
        }
        input.close();
    }

    public void importVehicleFile(File infile) throws Exception {
        //Convert file into a input scanner
        Scanner input = null;
        input = new Scanner(infile);

        input.nextLine();
        while(input.hasNextLine()){
            String line = input.nextLine();
            String[] fields = line.split(";");

            if((fields.length < 4) || (fields.length > 5)){
                input.close();
                throw new Exception("Erro de formatacao");
            }else{
                String cod = fields[0];
                String name = fields[1];
                char type = fields[2].charAt(0);
                if((type != 'C') && (type != 'P')){
                    input.close();
                    throw new Exception("Tipo  de  veículo  desconhecido  para veículo " + cod + ": " + type + ".");
                }
                float imp = Utils.commaFloatFromString(fields[3]);
                String issn = "None";
                if(fields.length == 5){
                    issn = fields[4];
                }
                Vehicle obj = new Vehicle(cod, name, type, imp, issn);
                this.addVehicle(obj);
            }
        }
        input.close();
    }


    //GETTERS AND SETTERS
    public ArrayList<ScoreRules> getRules() {
        return rules;
    }

    public void setRules(ArrayList<ScoreRules> rules) {
        this.rules = rules;
    }

    public ArrayList<Vehicle> getVehs() {
        return vehs;
    }

    public void setVehs(ArrayList<Vehicle> vehs) {
        this.vehs = vehs;
    }

    public ArrayList<Qualify> getQualifies() {
        return qualifies;
    }

    public void setQualifies(ArrayList<Qualify> qualifies) {
        this.qualifies = qualifies;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

}
