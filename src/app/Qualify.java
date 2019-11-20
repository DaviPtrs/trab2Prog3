package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Qualify
 */
public class Qualify implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -7301494499823857577L;
    int year;
    int score;
    String vehCod;
    String qualis;

    public Qualify(){}

    public Qualify(int year, String qualis, String vehcode){
        this.year = year;
        this.qualis = qualis;
        this.vehCod = vehcode;
        this.score = 0;
    }

    /**
     * @return the qualis
     */
    public String getQualis() {
        return qualis;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the vehCod
     */
    public String getVehCod() {
        return vehCod;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param qualis the qualis to set
     */
    public void setQualis(String qualis) {
        this.qualis = qualis;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @param vehCod the vehCod to set
     */
    public void setVehCod(String vehCod) {
        this.vehCod = vehCod;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    public static boolean checkQualis(String qualis){
        ArrayList<String> validQualis = new ArrayList<String>(
            Arrays.asList("A1","A2","B1","B2","B3","B4","B5","C")
            );

        return validQualis.contains(qualis.toUpperCase());
    }

    @Override
    public String toString() {
        return "Qualify [qualis=" + qualis + ", score=" + score + ", vehCod=" + vehCod + ", year=" + year + "]";
    }
}