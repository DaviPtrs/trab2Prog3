package app;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;
import java.util.Map;
import exceptions.*;


/**
 * Contém as regras pelas quais os professores serão pontuados e recadastrados no sistema.
 */
public class ScoreRules implements Serializable{
    
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

    /**
     * Construtor da classe que atribui os parâmetros de entrada aos atributos "start", "end", "periodicMulti", "yearsCnt", "minScore".
     * @param start Uma data que será usado para inicializar o atributo "start" da classe.
     * @param end Uma data que será usado para inicializar o atributo "end" da classe.
     * @param periodicMulti Um flutuante que será usada para inicializar o atributo "periodicMulti" da classe.
     * @param yearsCnt Um inteiro que será usado para inicializar o atributo "yearsCnt" da classe.
     * @param minScore Um inteiro que será usado para inicializar o atributo "minScore" da classe.
     */
    public ScoreRules(Date start, Date end, float periodicMulti, int yearsCnt, int minScore) {
        this.start = start;
        this.end = end;
        this.periodicMulti = periodicMulti;
        this.yearsCnt = yearsCnt;
        this.minScore = minScore;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "end" da instância da classe.
     * @param end A data a ser atribuída ao atributo "end".
     */
    public void setEnd(Date end) {
        this.end = end;
    }
    

    /**
     * Atribui o parâmetro de entrada ao atributo "minScore" da instância da classe.
     * @param minScore O inteiro a ser atribuído ao atributo "minScore".
     */
    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "periodicMulti" da instância da classe.
     * @param periodicMulti O float a ser atribuído ao atributo "periodicMulti".
     */
    public void setPeriodicMulti(float periodicMulti) {
        this.periodicMulti = periodicMulti;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "start" da instância da classe.
     * @param start A data a ser atribuída ao atributo "start".
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "end" da instância da classe.
     * @param yearsCnt O inteiro a ser atribuído ao atributo "yearsCnt"
     */
    public void setYearsCnt(int yearsCnt) {
        this.yearsCnt = yearsCnt;
    }

    /**
     * Retorna o valor atual do atributo "end" da instância da classe.
     * @return a data atual no atributo "end".
     */
    public Date getEnd() {
        return end;
    }

    /**
     * Retorna o valor atual do atributo "minScore" da instância da classe.
     * @return Inteiro atual no atributo "minScore".
     */
    public int getMinScore() {
        return minScore;
    }

    /**
     * Retorna o valor atual do atributo "periodicMulti" da instância da classe.
     * @return Flutuante atual no atributo "periodicMulti".
     */
    public float getPeriodicMulti() {
        return periodicMulti;
    }

    /**
     * Retorna o valor atual do atributo "start" da instância da classe.
     * @return A data atual no atributo "start".
     */
     public Date getStart() {
        return start;
    }
    
    /**
     * Retorna o valor atual do atributo "yearsCnt" da instância da classe.
     * @return Inteiro atual no atributo "yearsCnt".
     */
    public int getYearsCnt() {
        return yearsCnt;
    }

    /**
     * Atribui o mapa de entrada ao atributo "qualis" da instância da classe, mapeando cada qualis ao seu inteiro equivalente.
     * @param qualis O mapa de qualis/inteiros.
     */
    public void setQualis(Map<String, Integer> qualis) {
        this.qualis = qualis;
    }

    /**
     * Mapeia os Qualis-Score com base nas strings passadas de entrada.
     * @param qualis Vetor de strings representando todos os qualis.
     * @param score Vetor de strings representando todos os scores atrelados aos qualis.
     * @throws Exception Erro de formatação (Se o tamanho dos vetores forem diferentes, há um erro de formatação, já que não estão mapeados 1:1)
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

    /**
     * Formata os atributos "end", "minScore", "periodicMult", "qualis", "start" e "yearsCnt" para uma string e a retorna
     * @return A string contendo os valores dos atributos únicos da instância da classe.
     */
    @Override
    public String toString() {
        return "ScoreRules [end=" + end + ", minScore=" + minScore + ", periodicMulti=" + periodicMulti + ", qualis="
                + qualis + ", start=" + start + ", yearsCnt=" + yearsCnt + "]";
    }

    /**
     * Retorna o mapa de qualis-score atual da instância da classe.
     * @return O mapeamento atual no atributo "qualis".
     */
    public Map<String, Integer> getQualis() {
        return qualis;
    }
}