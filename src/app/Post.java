package app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Post
 */
public class Post implements Serializable, Comparable<Post>{
    /**
     *
     */
    private static final long serialVersionUID = -1285693772944744974L;
    private int year;
    private int num;
    private String title;
    private int initPage;
    private int endPage;
    private Vehicle vehicle;
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    private String qualis;

    public Post() {
    }

    public Post(int year, int num, String title, int initPage, int endPage) {
        this.year = year;
        this.num = num;
        this.title = title;
        this.initPage = initPage;
        this.endPage = endPage;
    }

    /**
     * @param endPage the endPage to set
     */
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    /**
     * @param initPage the initPage to set
     */
    public void setInitPage(int initPage) {
        this.initPage = initPage;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return the endPage
     */
    public int getEndPage() {
        return endPage;
    }

    /**
     * @return the initPage
     */
    public int getInitPage() {
        return initPage;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    public void addTeacher(Teacher t) {
        teachers.add(t);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        String influenceFactor = String.format("%.3f", vehicle.getInfluenceFactor()).replace(".", ",");
        sBuilder.append(year + ";" + vehicle.getCod() + ";" + vehicle.getName() + ";" + qualis + ";" 
                        + influenceFactor.replace(".", ",") + ";" + title + ";");
        for(int i = 0; i<teachers.size(); i++){
            sBuilder.append(teachers.get(i).getName());
            if(i != teachers.size() -1){
                sBuilder.append(",");
            }
        }
        return sBuilder.toString();
    }

    public String getQualis() {
        return qualis;
    }

    public void setQualis(String qualis) {
        this.qualis = qualis;
    }

    @Override
    public int compareTo(Post o) {
        if(this.qualis.compareTo(o.getQualis()) != 0){
            return this.qualis.compareTo(o.getQualis());
        }else if(this.year != o.getYear()){
            if(this.year > o.getYear()){
                return -1;
            }else{
                return 1;
            }
        }else if(this.vehicle.getCod().compareTo(o.getVehicle().getCod()) != 0){
            return this.vehicle.getCod().compareTo(o.getVehicle().getCod());
        }else{
            return this.title.compareTo(o.getTitle());
        }
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

}