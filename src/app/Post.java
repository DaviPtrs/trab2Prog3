package app;

import java.util.ArrayList;

/**
 * Post
 */
public class Post {
    private int year;
    private int num;
    private String title;
    private int initPage;
    private int endPage;
    private Vehicle vehicle;
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();

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

    public void removeVehicle(Teacher t) {
        teachers.remove(t);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Post [endPage=" + endPage + ", initPage=" + initPage + ", num=" + num + ", teachers=" + teachers
                + ", title=" + title + ", vehicle=" + vehicle + ", year=" + year + "]";
    }

}