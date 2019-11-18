package app;

import java.util.ArrayList;

/**
 * Vehicle
 */
public class Vehicle{
    private String cod;
    private String name;
    private char type;
    private float influenceFactor;
    private String issn;
    private Qualify quality;
    private ArrayList<Post> posts = new ArrayList<Post>();

    public Vehicle(String cod) {
        this.cod = cod;
    }

    public Vehicle(String cod, String name, char type, float influenceFactor, String issn) {
        this.cod = cod;
        this.name = name;
        this.type = type;
        this.influenceFactor = influenceFactor;
        this.issn = issn;
    }

    /**
     * @return the cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @return the influenceFactor
     */
    public float getInfluenceFactor() {
        return influenceFactor;
    }

    /**
     * @return the issn
     */
    public String getIssn() {
        return issn;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the type
     */
    public char getType() {
        return type;
    }

    /**
     * @return the quality
     */
    public Qualify getQuality() {
        return quality;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * @param influenceFactor the influenceFactor to set
     */
    public void setInfluenceFactor(float influenceFactor) {
        this.influenceFactor = influenceFactor;
    }

    /**
     * @param issn the issn to set
     */
    public void setIssn(String issn) {
        this.issn = issn;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param type the type to set
     */
    public void setType(char type) {
        this.type = type;
    }

    /**
     * @param quality the quality to set
     */
    public void setQuality(Qualify quality) {
        this.quality = quality;
    }

    /**
     * @return the posts
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void addPost(Post p) {
        posts.add(p);
    }

    public void removePost(Post p) {
        posts.remove(p);
    }

    @Override
    public String toString() {
        return "Vehicle [cod=" + cod + ", influenceFactor=" + influenceFactor + ", issn=" + issn + ", name=" + name
                + ", type=" + type + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof Vehicle) 
                && (((Vehicle)obj).getCod().compareTo(this.cod) == 0));
    }
}