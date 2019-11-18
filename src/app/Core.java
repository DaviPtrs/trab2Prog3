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

    private enum DTeacher{
        ID, NAME, BIRTH, ENTRY, MAJOR
    }

    private enum DVehicle{
        COD, NAME, TYPE, IMP, ISSN
    }

    //Add functions
    public void addRule(ScoreRules r){
        rules.add(r);
    }

    public void addVehicle(Vehicle v){
        vehs.add(v);
    }
    
    public void addQualify(Qualify q){
        qualifies.add(q);
    }
    
    public void addPost(Post p){
        posts.add(p);
    }
    
    public void addTeacher(Teacher t){
        teachers.add(t);
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
                throw new Exception("Inconsistencia na entrada");
            }else{
                long id = Long.parseLong(fields[DTeacher.ID.ordinal()]);
                String name = fields[DTeacher.NAME.ordinal()];
                Date birth = Utils.convertDate(fields[DTeacher.BIRTH.ordinal()]);
                Date entry = Utils.convertDate(fields[DTeacher.ENTRY.ordinal()]);
                boolean isMajor = false;
                if(fields.length == 5){
                    if(fields[DTeacher.MAJOR.ordinal()].compareToIgnoreCase("X") == 0){
                        isMajor = true;      
                    }
                }
                Teacher t = new Teacher(id, name, birth, entry, isMajor);
                this.teachers.add(t);
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
                throw new Exception("Inconsistencia na entrada");
            }else{
                String cod = fields[DVehicle.COD.ordinal()];
                String name = fields[DVehicle.NAME.ordinal()];
                char type = fields[DVehicle.TYPE.ordinal()].charAt(0);
                float imp = Utils.commaFloatFromString(fields[DVehicle.IMP.ordinal()]);
                String issn = "None";
                if(fields.length == 5){
                    issn = fields[DVehicle.ISSN.ordinal()];
                }
                Vehicle obj = new Vehicle(cod, name, type, imp, issn);
                this.vehs.add(obj);
            }
        }
        input.close();
    }

    //print functions (just for debugging proposes)
    public void printTeachers(){
        for(Teacher t: this.teachers){
            System.out.println(t.toString());
        }
    }

    public void printVehicles(){
        for(Vehicle v: this.vehs){
            System.out.println(v.toString());
        }
    }

}
