package app;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import misc.*;
import exceptions.*;

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
            throw new DuplicatedId(v.getCod());
        }else{
            vehs.add(v); 
        }
    }
    
    public void addTeacher(Teacher t) throws Exception{
        if(teachers.contains(t)){
            throw new DuplicatedId(t.getId());
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
                throw new FormatException();
            }else{
                Teacher obj = null;
                try {
                    long id = Long.parseLong(fields[0].trim());
                    String name = fields[1];
                    Date birth = Utils.convertDate(fields[2]);
                    Date entry = Utils.convertDate(fields[3]);
                    boolean isMajor = false;
                    if(fields.length == 5){
                        if(fields[4].compareToIgnoreCase("X") == 0){
                            isMajor = true;      
                        }
                    }
                    obj = new Teacher(id, name, birth, entry, isMajor);
                    this.addTeacher(obj);
                } catch (Exception e){
                    obj = null;
                    input.close();
                    throw e;
                }
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
                throw new FormatException();
            }else{
                Vehicle obj = null;
                try {
                    String cod = fields[0].trim();
                    String name = fields[1];
                    char type = fields[2].charAt(0);
                    if((type != 'C') && (type != 'P')){
                        throw new UndefinedVehicleType(cod, type);
                    }
                    float imp = Utils.commaFloatFromString(fields[3]);
                    String issn = "None";
                    if(fields.length == 5){
                        issn = fields[4];
                    }
                    obj = new Vehicle(cod, name, type, imp, issn);
                    this.addVehicle(obj);
                } catch (Exception e){
                    obj = null;
                    input.close();
                    throw e;
                }
            }
        }
        input.close();
    }

    public void importPostFile(File infile) throws Exception {
        //Convert file into a input scanner
        Scanner input = null;
        input = new Scanner(infile);

        input.nextLine();
        while(input.hasNextLine()){
            String line = input.nextLine();
            String[] fields = line.split(";");

            if(fields.length != 9){
                input.close();
                throw new FormatException();
            }else{
                Post obj = null;
                int flag = -1;
                //Check if "Volume" field is empty
                if(fields[5].isEmpty()){
                    //So It is a Conference
                    flag = 1;
                }else{
                    //Else It is a Periodic
                    flag = 0;
                }
                try {
                    //Getting and setting default fields
                    int year = Integer.parseInt(fields[0].trim());
                    String title = fields[2];
                    int num = Integer.parseInt(fields[4].trim());
                    String veh = fields[1].trim();
                    int initPage = Integer.parseInt(fields[7].trim());
                    int endPage = Integer.parseInt(fields[8].trim());
                    if(flag == -1){
                        throw new FormatException();
                    }else if(flag == 0){
                        int volume = Integer.parseInt(fields[5].trim());
                        obj = new Periodic(year, num, title, initPage, endPage, volume);
                    }else{
                        String location = fields[6];
                        obj = new Conference(year, num, title, initPage, endPage, location);
                    }
                    //Setting teachers
                    ArrayList<Long> ids = Utils.stringToLongArray(fields[3]);
                    for(Long id: ids){
                        Teacher t = getTeacher(id);
                        if(t == null){
                            throw new UndefinedTeacherOnPost(title, id);
                        }else{
                            t.addPost(obj);
                            obj.addTeacher(t);
                        }
                    }
                    //Setting vehicle
                    Vehicle v = getVehicle(veh);
                    if(v == null){
                        throw new UndefinedVehicleOnPost(title, veh);
                    }
                    v.addPost(obj);
                    obj.setVehicle(v);

                    //Adding to core system
                    this.posts.add(obj);
                } catch (Exception e){
                    obj = null;
                    input.close();
                    throw e;
                }
            }
        }
        input.close();
    }

    public void importQualisFile(File infile) throws Exception {
        //Convert file into a input scanner
        Scanner input = null;
        input = new Scanner(infile);

        input.nextLine();
        while(input.hasNextLine()){
            String line = input.nextLine();
            String[] fields = line.split(";");

            if(fields.length != 3){
                input.close();
                throw new FormatException();
            }else{
                Qualify qualify = null;
                try {
                    int year = Integer.parseInt(fields[0].trim());
                    String vehCod = fields[1].trim();
                    String qualis = fields[2].trim().toUpperCase();
                    if(!Qualify.checkQualis(qualis)){
                        throw new UndefinedQualis(qualis, vehCod, year);
                    }
                    Vehicle veh = getVehicle(vehCod);
                    if(veh == null){
                        throw new UndefinedVehicleOnQualis(year, vehCod);
                    }
                    qualify = new Qualify(year, qualis, vehCod);
                    qualify.setVehCod(vehCod);
                    veh.addQualify(qualify);
                    this.qualifies.add(qualify);
                } catch (Exception e){
                    qualify = null;
                    input.close();
                    throw e;
                }
            }
        }
        input.close();
    }

    public void importRuleFile(File infile) throws Exception {
        //Convert file into a input scanner
        Scanner input = null;
        input = new Scanner(infile);

        input.nextLine();
        while(input.hasNextLine()){
            String line = input.nextLine();
            String[] fields = line.split(";");

            if(fields.length != 7){
                input.close();
                throw new FormatException();
            }else{
                ScoreRules obj = null;
                try {
                    Date startsOn = Utils.convertDate(fields[0]);
                    Date endsOn = Utils.convertDate(fields[1]);
                    String[] qualis = fields[2].split(",");
                    for (String quali : qualis) {
                        if(!Qualify.checkQualis(quali)){
                            throw new UndefinedQualis(quali, startsOn);
                        }
                    }
                    String[] score = fields[3].split(",");
                    float multi = Utils.commaFloatFromString(fields[4]);
                    int years = Integer.parseInt(fields[5].trim());
                    int minScore = Integer.parseInt(fields[6].trim());

                    obj = new ScoreRules(startsOn, endsOn, multi, years, minScore);
                    obj.setQualis(qualis, score);
                    this.rules.add(obj);
                } catch (Exception e){
                    obj = null;
                    input.close();
                    throw e;
                }
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

    //SEARCH FUNCTIONS
    public Teacher getTeacher(long id){
        Teacher aux = new Teacher(id);
        int index = teachers.indexOf(aux);
        aux = null;
        if(index == -1){
            return null;
        }
        return teachers.get(index);
    }

    public Vehicle getVehicle(String cod){
        Vehicle aux = new Vehicle(cod);
        int index = vehs.indexOf(aux);
        aux = null;
        if(index == -1){
            return null;
        }
        return vehs.get(index);
    }

}
