package app;

import java.util.ArrayList;

/**
 * Core
 */
public class Core {
    private ArrayList<ScoreRules> rules;
    private ArrayList<Vehicle> vehs;
    private ArrayList<Qualify> qualifies;
    private ArrayList<Post> posts;
    private ArrayList<Teacher> teachers;

    Core(){}

    public void addRule(ScoreRules r){
        rules.add(r);
    }

    public void delRule(ScoreRules r){
        rules.remove(r);
        r = null;
        System.gc();
    }

    public void addVehicle(Vehicle v){
        vehs.add(v);
    }

    public void delVehicle(Vehicle v){
        vehs.remove(v);
        v = null;
        System.gc();
    }

    public void addQualify(Qualify q){
        qualifies.add(q);
    }

    public void delQualify(Qualify q){
        qualifies.remove(q);
        q = null;
        System.gc();
    }

    public void addPost(Post p){
        posts.add(p);
    }

    public void delPost(Post p){
        posts.remove(p);
        p = null;
        System.gc();
    }

    public void addTeacher(Teacher t){
        teachers.add(t);
    }

    public void delTeacher(Teacher t){
        teachers.remove(t);
        t = null;
        System.gc();
    }

        
}