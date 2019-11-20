package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Calendar;
import java.util.TimeZone;
import misc.*;

/**
 * Teacher
 */
public class Teacher implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 2992537304035985985L;
	private long id;
    private String name;
    private Date birthDate;
    private Date entryDate;
    private boolean isMajor;
    private float score;
    private ArrayList<Post> posts = new ArrayList<Post>();

    public Teacher(long id) {
        this.id = id;
        this.score = 0;
    }

    public Teacher(long id, String name, Date birthDate, Date entryDate, Boolean isMajor) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.entryDate = entryDate;
        this.isMajor = isMajor;
        this.score = 0;
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
    public int getAge(int year) {
        Calendar atual = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
        atual.set(year - 1970, 0, 1);
        // atual.set(Calendar.YEAR, 2019);
        long dif = atual.getTimeInMillis() - this.birthDate.getTime();
        Calendar difDate = Calendar.getInstance();
        difDate.setTimeInMillis(dif);
        return (difDate.get(Calendar.YEAR));
    }

    public int getTeachingTime(int year){
        Calendar atual = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
        atual.set(year - 1970, 0, 1);
        // atual.set(Calendar.YEAR, 2019);
        long dif = atual.getTimeInMillis() - this.entryDate.getTime();
        Calendar difDate = Calendar.getInstance();
        difDate.setTimeInMillis(dif);
        return (difDate.get(Calendar.YEAR));
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
    public long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public boolean isMajor() {
        return this.isMajor;
    }

    /**
     * @return the posts
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void addPost(Post p) {
        posts.add(p);
    }

    public void removePost(Post p) {
        posts.remove(p);
    }

    @Override
    public String toString() {
        return "Teacher [birthDate=" + Utils.dateToString(birthDate) + ", entryDate=" + Utils.dateToString(entryDate) 
                + ", id=" + id + ", isMajor=" + isMajor + ", name=" + name + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof Teacher) 
                && (((Teacher)obj).getId() == this.id));
    }

    public static String specialCredentialCase(Teacher t, int year){
        String credentialCase = "";
        if(t.isMajor()){
            credentialCase = "Coordenador";
        }else if(t.getTeachingTime(year) < 3){
            credentialCase = "PPJ";
        }else if(t.getAge(year) > 60){
            credentialCase = "PPS";
        }
        return credentialCase;
    }

    public float getScore() {
        return score;
    }
    
    //isso aqui nao faz sentido
    public void calcScore(ScoreRules rule, int year){
        float score = 0;
        float factor = rule.getPeriodicMulti();
        Map<String, Integer> qualisDict = rule.getQualis();
        for(Post post: this.posts){
            Vehicle veh = post.getVehicle();
            Qualify quali = veh.getAppliedQualis(post.getYear());
            int scoreAcc = qualisDict.get(quali.getQualis());
            if(post instanceof Periodic){
                scoreAcc *= factor;
            }
            score += scoreAcc;
        }
        this.score = score;
    }

}