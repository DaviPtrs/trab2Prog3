package app;

import java.io.Serializable;

/**
 * Um local no qual um "Post" pode ser feito.
 * {@inheritDoc}
 * @author Javi
 */
public class Conference extends Post implements Serializable{
    
    private static final long serialVersionUID = 3573183689662079484L;
    private String location;

    public Conference(){}

    
    
    /**
     * O construtor da classe, que chama o construtor da super classe, com a adição do parâmetro location, que é exclusivo de Conference.
     * @param year Um inteiro que será usado para inicializar o atributo "year" da classe.
     * @param num Um inteiro que será usado para inicializar o atributo "num" da classe.
     * @param title Uma string que será usada para inicializar o atributo "title" da classe.
     * @param initPage Um inteiro que será usado para inicializar o atributo "initPage" da classe.
     * @param endPage Um inteiro que será usado para inicializar o atributo "endPage" da classe.
     * @param location Uma string que será usada para inicializar o atributo "location" da classe.
     */
    public Conference(int year, int num, String title, int initPage, int endPage, String location) {
        super(year, num, title, initPage, endPage);
        this.location = location;
    }

    /**
     * Atribui o parâmetro de entrada ao atributo "location" da instância da classe.
     * @param location A string a ser atribuída à "location".
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Retorna o valor atual do atributo "location" da instância da classe.
     * @return O valor atual de "location".
     */
    public String getLocation() {
        return location;
    }

}