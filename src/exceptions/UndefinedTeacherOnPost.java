package exceptions;

/**
 * UndefinedTeacherOnPost
 */
public class UndefinedTeacherOnPost extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UndefinedTeacherOnPost(String title, long id) {
        super("Código de docente não definido usado na publicação \"" 
                            + title + "\": " + id + ".");
    }
}