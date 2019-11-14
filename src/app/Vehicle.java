package app;

/**
 * Vehicle
 */
public class Vehicle {
    private String cod;
    private String name;
    private char type;
    private float influenceFactor;
    private String issn;

    Vehicle(){}

    Vehicle(String cod, String name, char type, float influenceFactor, String issn){
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
}