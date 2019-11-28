package web.storage;

public class StorageException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 643884088806329178L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
