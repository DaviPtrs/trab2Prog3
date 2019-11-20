package app;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;
import java.util.Map;
import exceptions.*;


/**
 * ScoreRules
 */
public class ScoreRules implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 7317879025475558611L;
    private Date start;
    private Date end;
    private float periodicMulti;
    private int yearsCnt;
    private int minScore;
    private Map<String, Integer> qualis = new TreeMap<String, Integer>(){
        private static final long serialVersionUID = 1L;
        {
            for(String key: Qualify.validQualis){
                this.put(key, -1);
            }
        }
    };

    public ScoreRules(){}

    public ScoreRules(Date start, Date end, float periodicMulti, int yearsCnt, int minScore) {
        this.start = start;
        this.end = end;
        this.periodicMulti = periodicMulti;
        this.yearsCnt = yearsCnt;
        this.minScore = minScore;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Date end) {
        this.end = end;
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

    /**
     * @param qualis the qualis to set
     */
    public void setQualis(Map<String, Integer> qualis) {
        this.qualis = qualis;
    }

    /**
     * @param qualis String vector of qualis
     * @param score String vector of each score
     * @throws Exception Erro de formatação
     */
    public void setQualis(String[] qualis, String[] score) throws Exception{
        if(qualis.length != score.length){
            throw new FormatException();
        }

        for (int i = 0; i < score.length; i++) {
            this.qualis.put(qualis[i].trim().toUpperCase(), Integer.parseInt(score[i].trim()));
        }

        Integer last = -1;
        for(Map.Entry<String, Integer> entry: this.qualis.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(value == -1){
                this.qualis.put(key, last);
            }else if(value != -1){
                last = value;
            }
        }
    }

    @Override
    public String toString() {
        return "ScoreRules [end=" + end + ", minScore=" + minScore + ", periodicMulti=" + periodicMulti + ", qualis="
                + qualis + ", start=" + start + ", yearsCnt=" + yearsCnt + "]";
    }

    /**
     * @return the qualis
     */
    public Map<String, Integer> getQualis() {
        return qualis;
    }
}