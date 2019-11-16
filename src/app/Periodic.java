package app;

/**
 * Periodic
 */
public class Periodic extends Post{
    private int volume;

    public Periodic() {}

    public Periodic(int id, int year, String cod, String title, int initPage, int endPage, int volume) {
        super(id, year, cod, title, initPage, endPage);
        this.volume = volume;
    }
    
    /**
     * @param volume the volume to set
     */
    public void setvolume(int volume) {
        this.volume = volume;
    }

    /**
     * @return the volume
     */
    public int getvolume() {
        return volume;
    }

}