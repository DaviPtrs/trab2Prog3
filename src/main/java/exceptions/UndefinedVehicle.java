package exceptions;

/**
 * UndefinedVehicle
 */
public class UndefinedVehicle extends Exception{
    
    private static final long serialVersionUID = 1L;

    /**
     * Undefined Vehicle on Post
     * @param title Post title
     * @param cod Vehicle code
     */
    public UndefinedVehicle(String title, String cod) {
        super("Sigla de veículo não definido usada na publicação \"" 
                + title + "\": " + cod + ".");
    }

    /**
     * Undefined Vehicle on Qualis
     * @param qualiYear Year of a qualis
     * @param cod Vehicle code
     */
    public UndefinedVehicle(int qualiYear, String cod) {
        super("Sigla de veículo não definida usada na qualificação do ano " 
               + qualiYear + ": " + cod + ".");
    }

    /**
     * Undefined Vehicle Type
     * @param cod Vehicle code
     * @param type Vehicle type
     */
    public UndefinedVehicle(String cod, char type) {
        super("Tipo  de  veículo  desconhecido  para veículo " + cod + ": " + type + ".");
    }
}