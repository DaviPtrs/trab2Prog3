package app;

/**
 * Periodic
 */
public class Periodic extends Post{
    private int volume;

    Periodic(){}

    Periodic(int year, String cod, String title, int initPage, int endPage, int id, int volume){
        setYear(year);
        setCod(cod);
        setEndPage(endPage);
        setInitPage(initPage);
        setTitle(title);
        setId(id);
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