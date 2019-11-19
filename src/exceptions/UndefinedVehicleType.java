package exceptions;

/**
 * UndefinedVehicleType
 */
public class UndefinedVehicleType extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UndefinedVehicleType(String cod, char type) {
        super("Tipo  de  veículo  desconhecido  para veículo " + cod + ": " + type + ".");
    }
}