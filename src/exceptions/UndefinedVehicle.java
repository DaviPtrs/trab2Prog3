package exceptions;

/**
 * UndefinedVehicle
 */
public class UndefinedVehicle extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Undefined Vehicle on Post
     * @param title
     * @param cod
     */
    public UndefinedVehicle(String title, String cod) {
        super("Sigla de veículo não definido usada na publicação \"" 
                + title + "\": " + cod + ".");
    }

    /**
     * Undefined Vehicle on Qualis
     * @param qualiYear
     * @param vehCod
     */
    public UndefinedVehicle(int qualiYear, String vehCod) {
        super("Sigla de veículo não definida usada na qualificação do ano " 
               + qualiYear + ": " + vehCod + ".");
    }

    /**
     * Undefined Vehicle Type
     * @param cod
     * @param type
     */
    public UndefinedVehicle(String cod, char type) {
        super("Tipo  de  veículo  desconhecido  para veículo " + cod + ": " + type + ".");
    }
}