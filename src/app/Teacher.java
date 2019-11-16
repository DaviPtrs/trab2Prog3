package app;

import java.util.ArrayList;
import java.util.Date;


/**
 * Teacher
 */
public class Teacher {
    private int id;
    private String name;
    private Date birthDate;
    private Date entryDate;
    private boolean isMajor;
    private ArrayList<Post> posts;

    public Teacher(){}

    public Teacher(int id, String name, Date birthDate, Date entryDate, Boolean isMajor){
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.entryDate = entryDate;
        this.isMajor = isMajor;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    /**
     * @param entryDate the entryDate to set
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * @param isMajor the isMajor to set
     */
    public void setMajor(boolean isMajor) {
        this.isMajor = isMajor;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        //calcular a idade e retornar aqui
        return 1;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @return the entryDate
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public boolean isMajor(){
        return this.isMajor;
    }

    /**
     * @return the posts
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void addPost(Post p){
        posts.add(p);
    }
    public void removePost(Post p){
        posts.remove(p);
    }

}