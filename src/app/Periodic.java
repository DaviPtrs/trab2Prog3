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
     * @return int O valor atual do atributo "volume".
     */
    public int getvolume() {
        return volume;
    }

}