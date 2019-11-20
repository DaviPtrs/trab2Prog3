package exceptions;

/**
 * UndefinedTeacher
 */
public class UndefinedTeacher extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Undefined Teacher on a post
     * @param id teacher id
     * @param title Post title
     */
    public UndefinedTeacher(String title, long id) {
        super("Código de docente não definido usado na publicação \"" 
                            + title + "\": " + id + ".");
    }
}