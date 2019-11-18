package app;

/**
 * Conference
 */
public class Conference extends Post{
    private String location;

    public Conference(){}

    public Conference(int year, int num, String title, int initPage, int endPage, String location) {
        super(year, num, title, initPage, endPage);
        this.location = location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

}