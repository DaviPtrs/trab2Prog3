package app;

import java.io.Serializable;

/**
 * Um periódico é um local onde posts são publicados periodicamente.
 * {@inheritDoc}
 * @author Javi
 */
public class Periodic extends Post implements Serializable{
    
    private static final long serialVersionUID = -2975112673947283296L;
    private int volume;

    public Periodic() {}

    /**
     * Chama o construtor da superclasse, com a adição do parâmetro volume, utilizado para inicializar o atributo volume da instância da classe.
     * @param year Um inteiro que será usado para inicializar o atributo "year" da classe.
     * @param num Um inteiro que será usado para inicializar o atributo "num" da classe.
     * @param title Uma string que será usada para inicializar o atributo "title" da classe.
     * @param initPage Um inteiro que será usado para inicializar o atributo "initPage" da classe.
     * @param endPage Um inteiro que será usado para inicializar o atributo "endPage" da classe.
     * @param volume Um inteiro que será usado para inicializar o atributo "volume" da classe.
     */
    public Periodic(int year, int num, String title, int initPage, int endPage, int volume) {
        super(year, num, title, initPage, endPage);
        this.volume = volume;
    }
    
    /**
     * Atribui o parâmetro de entrara ao atributo "volume" da instância da classe.
     * @param volume Um inteiro que será usado para inicializar o "volume".
     */
    public void setvolume(int volume) {
        this.volume = volume;
    }

    /**
     * Retorna o valor atual salvo no atributo "volume" da instância da classe.
     * @return O valor atual do atributo "volume".
     */
    public int getvolume() {
        return volume;
    }

}