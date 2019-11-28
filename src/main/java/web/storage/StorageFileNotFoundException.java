package web.storage;

public class StorageFileNotFoundException extends StorageException {

    /**
     *
     */
    private static final long serialVersionUID = -6051922588648408967L;

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}