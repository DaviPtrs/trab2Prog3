package app;

import java.io.Serializable;

/**
 * Um local no qual um "Post" pode ser feito.
 * {@inheritDoc}
 * @author Javi
 */
public class Conference extends Post implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 3573183689662079484L;
    private String location;

    public Conference(){}

    /**
     * O construtor da classe, que chama o construtor da super classe, com a adição do parâmetro location, que é exclusivo de Conference.
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
     * @return string O valor atual de "location".
     */
    public String getLocation() {
        return location;
    }

}