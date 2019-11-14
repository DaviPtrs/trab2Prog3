package app;

/**
 * Conference
 */
public class Conference extends Post{
    private String location;

    Conference(){}

    Conference(int year, String cod, String title, int initPage, int endPage, int id, String location){
        setYear(year);
        setCod(cod);
        setEndPage(endPage);
        setInitPage(initPage);
        setTitle(title);
        setId(id);
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