package app;

/**
 * Qualify
 */
public class Qualify {
    int year;
    int score;
    String vehCod;
    String qualis;

    public Qualify(){}

    public Qualify(int year, String qualis){
        this.year = year;
        this.qualis = qualis;
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
}