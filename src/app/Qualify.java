package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Qualify
 */
public class Qualify implements Serializable{
    
    private static final long serialVersionUID = -7301494499823857577L;
    public static final ArrayList<String> validQualis = new ArrayList<String>(
                                                            Arrays.asList("A1","A2","B1","B2","B3","B4","B5","C"));
    int year;
    String vehCod;
    String qualis;

    public Qualify(){}

    /**
     * O construtor da classe, inicializando seus atributos com os parâmetros de entradas 
     * @param year O parâmetro a ser atribuido à "yeah".
     * @param qualis O parâmetro a ser atribuído à "qualis".
     * @param vehcode O parâmetro a ser atribuído à "vehcode".
     */
    public Qualify(int year, String qualis, String vehcode){
        this.year = year;
        this.qualis = qualis;
        this.vehCod = vehcode;
    }

    /**
     * Retorna o valor atual do atributo "qualis" da instância da classe.
     * @return O qualis atrelado ao veículo.
     */
    public String getQualis() {
        return qualis;
    }

    /**
     * Retorna o valor atual do atributo "vehCod" da instância da classe.
     * @return O código do veículo que recebeu o qualis da instância da classe.
     */
    public String getVehCod() {
        return vehCod;
    }

    /**
     * Retorna o valor atual do atributo "year" da instância da classe.
     * @return O ano no qual o veículo recebeu o qualis da instância da classe.
     */
    public int getYear() {
        return year;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "qualis"
     * @param qualis A string a ser atribuído ao "qualis".
     */
    public void setQualis(String qualis) {
        this.qualis = qualis;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "vehCod" da instância da classe.
     * @param vehCod A string a ser atribuída à "vehCod"
     */
    public void setVehCod(String vehCod) {
        this.vehCod = vehCod;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "year". da instância da classe.
     * @param year O inteiro a ser atribuído à "year".
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Checa se os qualis salvos na instância da classe equivalem à qualis válidos.
     * @param qualis Qualis a ser verificado se está contido na lista de qualis válidos.
     * @return Retorna um boleano que informa se os qualis são válidos.
     */
    public static boolean checkQualis(String qualis){
        return validQualis.contains(qualis.toUpperCase());
    }

    /**
     * Formata os atributos da classe numa string e a retorna.
     * @return Uma string contendo as informações dos atributos da classe.
     */
    @Override
    public String toString() {
        return "Qualify [qualis=" + qualis + ", vehCod=" + vehCod + ", year=" + year + "]";
    }

}