package exceptions;

/**
 * DuplicatedId
 */
public class DuplicatedId extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DuplicatedId(String object, int id) {
        super("Código repetido para " + object + ": " + id + ".");
    }

    public DuplicatedId(String object, String id) {
        super("Código repetido para " + object + ": " + id + ".");
    }
    
    public DuplicatedId(long id) {
        super("Código repetido para docente: " + id + ".");
    }

    public DuplicatedId(String id) {
        super("Código repetido para veículo: " + id + ".");
    }
}