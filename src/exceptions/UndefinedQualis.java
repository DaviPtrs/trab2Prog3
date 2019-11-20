package exceptions;

import java.util.Date;
import misc.Utils;

/**
 * UndefinedQualis
 */
public class UndefinedQualis extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Undefined qualis to qualify a vehicle
     * @param vehCod
     * @param year
     */
    public UndefinedQualis(String qualis, String vehCod, int year) {
        super("Qualis desconhecido para qualificação do veículo "
        + vehCod + " no ano " + year + ": " + qualis + ".");
    }

    /**
     * Undefined qualis in a score rule
     * @param vehCod
     * @param year
     */
    public UndefinedQualis(String qualis, Date date) {
        super("Qualis desconhecido para regras de "
        + Utils.dateToString(date) + ": " + qualis + ".");
    }
}