package exceptions;

/**
 * UndefinedVehicleOnQuali
 */
public class UndefinedVehicleOnQualis extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UndefinedVehicleOnQualis(int qualiYear, String vehCod) {
        super("Sigla de veículo não definida usada na qualificação do ano " 
        + qualiYear + ": " + vehCod + ".");
    }
}