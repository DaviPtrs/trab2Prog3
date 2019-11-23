package app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que contém as informações à respeito de um post feito.
 * @author Javi
 * 
 * 
 */
public class Post implements Serializable, Comparable<Post>{
    private static final long serialVersionUID = -1285693772944744974L;
    private int year;
    private int num;
    private String title;
    private int initPage;
    private int endPage;
    private Vehicle vehicle;
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    private String qualis;
    
    
    /** 
     * @return 
     */
    public Post() {
    }
    
    /**
     * Esse método é construtor da classe, chamado para inicializá-la com os valores passados como entrada.
     * @param year Um inteiro que será usado para inicializar o atributo "year" da classe.
     * @param num Um inteiro que será usado para inicializar o atributo "num" da classe.
     * @param title Uma string que será usada para inicializar o atributo "title" da classe.
     * @param initPage Um inteiro que será usado para inicializar o atributo "initPage" da classe.
     * @param endPage Um inteiro que será usado para inicializar o atributo "endPage" da classe.
     * 
     */

    public Post(int year, int num, String title, int initPage, int endPage) {
        this.year = year;
        this.num = num;
        this.title = title;
        this.initPage = initPage;
        this.endPage = endPage;
    }

    /**
     * Esse método atribui o valor de entrada ao atributo "endPage" da classe.
     * @param endPage Um inteiro que será atribuido à "endPage".
     */
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    /**
     * Esse método atribui o parametro de entrada ao atributo "initPage" da classe.
     * @param initPage Um inteiro que será atribuido à "initPage".
     */
    public void setInitPage(int initPage) {
        this.initPage = initPage;
    }

    /**
     * Esse método atribui o parametro de entrada ao atributo "title" da classe.
     * @param title Uma string que será atribuida à "title".
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * Esse método atribui o parametro de entrada ao atributo "year" da classe.
     *  @param year Um inteiro que será atribuido à "year".
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Esse método atribui o parâmetro de entrada ao atributo "num" da classe.
     * @param num Um inteiro que será atribuido à "num".
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * Retorna o valor atual armazenado em "endPage".
     * @return int O valor atual de "endPage".
     */
    public int getEndPage() {
        return endPage;
    }

    /**
     * Retorna o valor atual armazenado em "initPage".
     * @return int O valor atual de "initPage".
     */
    public int getInitPage() {
        return initPage;
    }

    /**
     * Retorna o valor atual armazenado em "title".
     * @return string O conteúdo atual de "title".
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retorna o valor atual armazenado em "num".
     * @return int O conteúdo atual de "num".
     */
    public int getNum() {
        return num;
    }

    /**
     * Retorna o valor atual armazenado em "year".
     * @return int O conteúdo atual de "year".
     */
    public int getYear() {
        return year;
    }

    /**
     * Esse método adiciona um "Teacher" ao array de professores da classe.
     * @param Teacher O professor a ser adicionado. 
     */
    public void addTeacher(Teacher t) {
        teachers.add(t);
    }

    /**
     * Retorna o objeto "Vehicle" armazenado na instância da classe.
     * @return Vehicle Um objeto da classe "Vehicle".
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "vehicle" da instâcnia da classe.
     * @param Vehicle O veículo a ser atribuído.
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    
    /** 
     * Formata todos os dados salvos na instância da classe me uma string e a retorna.
     * @return string A string contendo todos os valores salvos na instância da classe
     */
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

    
    /** 
     * @return String
     */
    public String getQualis() {
        return qualis;
    }

    
    /** 
     * @param qualis
     */
    public void setQualis(String qualis) {
        this.qualis = qualis;
    }

    
    /** 
     * @param o
     * @return int
     */
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

    
    /** 
     * @return ArrayList<Teacher>
     */
    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

}