package exceptions;

/**
 * UndefinedVehicleOnPost
 */
public class UndefinedVehicleOnPost extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UndefinedVehicleOnPost(String title, String cod) {
        super("Sigla de veículo não definido usada na publicação \"" 
                            + title + "\": " + cod + ".");
    }
}