package app;

import java.util.ArrayList;
import java.util.Date;

/**
 * ScoreRules
 */
public class ScoreRules {
    private Date start;
    private Date end;
    private ArrayList<Integer> limits;
    private ArrayList<Integer> score;
    private float periodicMulti;
    private int yearsCnt;
    private int minScore;
    private ArrayList<String> qualis;

    ScoreRules(){}

    /**
     * @param end the end to set
     */
    public void setEnd(Date end) {
        this.end = end;
    }
    
    /**
     * @param limits the limits to set
     */
    public void setLimits(ArrayList<Integer> limits) {
        this.limits = limits;
    }

    /**
     * @param minScore the minScore to set
     */
    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    /**
     * @param periodicMulti the periodicMulti to set
     */
    public void setPeriodicMulti(float periodicMulti) {
        this.periodicMulti = periodicMulti;
    }

    /**
     * @param qualis the qualis to set
     */
    public void setQualis(ArrayList<String> qualis) {
        this.qualis = qualis;
    }

    /**
     * @param score the score to set
     */
    public void setScore(ArrayList<Integer> score) {
        this.score = score;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * @param yearsCnt the yearsCnt to set
     */
    public void setYearsCnt(int yearsCnt) {
        this.yearsCnt = yearsCnt;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @return the limits
     */
    public ArrayList<Integer> getLimits() {
        return limits;
    }

    /**
     * @return the minScore
     */
    public int getMinScore() {
        return minScore;
    }

    /**
     * @return the periodicMulti
     */
    public float getPeriodicMulti() {
        return periodicMulti;
    }

    /**
     * @return the qualis
     */
    public ArrayList<String> getQualis() {
        return qualis;
    }

    /**
     * @return the score
     */
     public ArrayList<Integer> getScore() {
        return score;
    }

    /**
     * @return the start
     */
     public Date getStart() {
        return start;
    }
    
    /**
     * @return the yearsCnt
     */
    public int getYearsCnt() {
        return yearsCnt;
    }
}