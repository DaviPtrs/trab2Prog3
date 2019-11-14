package app;

/**
 * Post
 */
public class Post {
    private int id;
    private int year;
    private String cod;
    private String title;
    private int initPage;
    private int endPage;

    Post(){}

    Post(int id, int year, String cod, String title, int initPage, int endPage){
        this.year = year;
        this.cod = cod;
        this.title = title;
        this.initPage = initPage;
        this.endPage = endPage;
        this.id = id;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * @param endPage the endPage to set
     */
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    /**
     * @param initPage the initPage to set
     */
    public void setInitPage(int initPage) {
        this.initPage = initPage;
    }
    
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @return the endPage
     */
    public int getEndPage() {
        return endPage;
    }

    /**
     * @return the initPage
     */
    public int getInitPage() {
        return initPage;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
}